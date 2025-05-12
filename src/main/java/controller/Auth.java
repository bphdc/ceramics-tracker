package controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import auth.*;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.*;
import org.hibernate.SessionFactory;
import persistence.GenericDao;
import persistence.SessionFactoryProvider;
import util.PropertiesLoader;
import util.ServletHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@WebServlet(
        urlPatterns = {"/auth"}
)

/**
 * Inspired by: https://stackoverflow.com/questions/52144721/how-to-get-access-token-using-client-credentials-using-java-code
 */

public class Auth extends HttpServlet implements PropertiesLoader {
    Properties properties;
    String CLIENT_ID;
    String CLIENT_SECRET;
    String OAUTH_URL;
    String LOGIN_URL;
    String REDIRECT_URL;
    String REGION;
    String POOL_ID;
    Keys jwks;

    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<User> userDao;
    private SessionFactory sessionFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        loadProperties();
        loadKey();
        userDao = new GenericDao<>(User.class);
        sessionFactory = SessionFactoryProvider.getSessionFactory();
    }

    /**
     * Gets the auth code from the request and exchanges it for a token containing user info.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authCode = req.getParameter("code");
        String userName = null;
        String userRole = "user";
        int userId = -1;
        String email = null;

        if (authCode == null) {
            logger.error("Auth code is null");
            ServletHelper.sendToErrorPageWithMessage(req, resp, "There was a problem logging in. Please try again and reach out to site admin if problem persists.");
        } else {
            HttpRequest authRequest = buildAuthRequest(authCode);
            try {
                TokenResponse tokenResponse = getToken(authRequest);
                Map userMap = validate(tokenResponse);
                userName = userMap.get("userName").toString();
                userRole = userMap.get("userRole").toString();
                userId = Integer.parseInt(userMap.get("userId").toString());
                User user = (User )userMap.get("user");
                req.setAttribute("userName", userName);
                HttpSession session = req.getSession();
                session.setAttribute("userRole", userRole);
                session.setAttribute("userName", userName);
                session.setAttribute("userId", userId);
                session.setAttribute("loggedInUser", user);
            } catch (IOException e) {
                logger.error("Error getting or validating the token: {}", e.getMessage(), e);
                ServletHelper.sendToErrorPageWithMessage(req, resp, "There was a problem logging in. Please try again and reach out to site admin if problem persists.");
            } catch (InterruptedException e) {
                logger.error("Error getting token from Cognito oauth url {}", e.getMessage(), e);
                ServletHelper.sendToErrorPageWithMessage(req, resp, "There was a problem logging in. Please try again and reach out to site admin if problem persists.");
            }
            catch (Exception e) {
                logger.error("Exception was found {}", e.getMessage(), e);
                ServletHelper.sendToErrorPageWithMessage(req, resp, "There was a problem logging in. Please try again and reach out to site admin if problem persists.");
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);

    }

    /**
     * Sends the request for a token to Cognito and maps the response
     * @param authRequest the request to the oauth2/token url in cognito
     * @return response from the oauth2/token endpoint which should include id token, access token and refresh token
     * @throws IOException
     * @throws InterruptedException
     */
    private TokenResponse getToken(HttpRequest authRequest) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<?> response = null;

        response = client.send(authRequest, HttpResponse.BodyHandlers.ofString());


        logger.debug("Response headers: {}", response.headers().toString());
        logger.debug("Response body: {}", response.body().toString());

        ObjectMapper mapper = new ObjectMapper();
        TokenResponse tokenResponse = mapper.readValue(response.body().toString(), TokenResponse.class);
        logger.debug("Id token: {}", tokenResponse.getIdToken());

        return tokenResponse;

    }

    /**
     * Get values out of the header to verify the token is legit. If it is legit, get the claims from it, such
     * as username.
     * @param tokenResponse
     * @return
     * @throws IOException
     */
    private Map<String, Object> validate(TokenResponse tokenResponse) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CognitoTokenHeader tokenHeader = mapper.readValue(CognitoJWTParser.getHeader(tokenResponse.getIdToken()).toString(), CognitoTokenHeader.class);

        // Header should have kid and alg- https://docs.aws.amazon.com/cognito/latest/developerguide/amazon-cognito-user-pools-using-the-id-token.html
        String keyId = tokenHeader.getKid();
        logger.debug("KeyId is: {}", keyId);
        String alg = tokenHeader.getAlg();
        logger.debug("alg is: {}", alg);

        logger.debug("first key is: {}", jwks.getKeys().get(0) );
        logger.debug("second key is: {}", jwks.getKeys().get(1) );

        KeysItem matchingKey = jwks.getKeys().stream()
                .filter(key -> keyId.equals(key.getKid()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No matching key found for kid: " + keyId));


        // Use Key's N and E
        BigInteger modulus = new BigInteger(1, org.apache.commons.codec.binary.Base64.decodeBase64(matchingKey.getN()));
        BigInteger exponent = new BigInteger(1, org.apache.commons.codec.binary.Base64.decodeBase64(matchingKey.getE()));

        // Create a public key
        PublicKey publicKey = null;
        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, exponent));
        } catch (InvalidKeySpecException e) {
            logger.error("Invalid Key Error {}", e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Algorithm Error {}", e.getMessage(), e);
        }

        // get an algorithm instance
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);

        // Verify ISS field of the token to make sure it's from the Cognito source
        String iss = String.format("https://cognito-idp.%s.amazonaws.com/%s", REGION, POOL_ID);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(iss)
                .withClaim("token_use", "id") // make sure you're verifying id token
                .build();

        // Verify the token
        DecodedJWT jwt = verifier.verify(tokenResponse.getIdToken());
        String userName = jwt.getClaim("cognito:username").asString();
        String email = jwt.getClaim("email").asString();

        logger.debug("here's the username: {}", userName);
        logger.debug("here's the email: {}", email);
        logger.debug("here are all the available claims: {}", jwt.getClaims());


        int userId = addUserToDB(userName, email);
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(userId);
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userId", userId);
        userMap.put("userName", userName);
        userMap.put("userRole", user.getRole());
        userMap.put("user", user);
        logger.debug("here's the user: {}", user);

        return userMap;
    }

    /**
     * checks if user exists and adds to db if not
     * @param userName userName to add and to check if already exists
     * @param email email to add
     * @return the user id of new or existing user
     *
     */
    private int addUserToDB (String userName, String email) {

        //determine if user exists already
        List<User> users = userDao.getByPropertyEqual("username", userName);
        if (users.isEmpty()) {
            User user = new User();
            user.setUsername(userName);
            //user.setName(name);
            user.setEmail(email);
            user.setRole("USER"); // default in a new user as a user role - site manager can change to admin later if needed
            user.setCreatedAt(Timestamp.from(Instant.now()));
            int userId = userDao.insert(user);
            return userId;
        }
        else {
           return users.get(0).getId();
        }
    }

    /** Create the auth url and use it to build the request.
     *
     * @param authCode auth code received from Cognito as part of the login process
     * @return the constructed oauth request
     */
    private HttpRequest buildAuthRequest(String authCode) {
        String keys = CLIENT_ID + ":" + CLIENT_SECRET;

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "authorization_code");
        parameters.put("client-secret", CLIENT_SECRET);
        parameters.put("client_id", CLIENT_ID);
        parameters.put("code", authCode);
        parameters.put("redirect_uri", REDIRECT_URL);

        String form = parameters.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        String encoding = Base64.getEncoder().encodeToString(keys.getBytes());

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(OAUTH_URL))
                .headers("Content-Type", "application/x-www-form-urlencoded", "Authorization", "Basic " + encoding)
                .POST(HttpRequest.BodyPublishers.ofString(form)).build();
        return request;
    }

    /**
     * Gets the JSON Web Key Set (JWKS) for the user pool from cognito and loads it
     * into objects for easier use.
     *
     * JSON Web Key Set (JWKS) location: https://cognito-idp.{region}.amazonaws.com/{userPoolId}/.well-known/jwks.json
     * Demo url: https://cognito-idp.us-east-2.amazonaws.com/us-east-2_XaRYHsmKB/.well-known/jwks.json
     *
     * @see Keys
     * @see KeysItem
     */
    private void loadKey() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL jwksURL = new URL(String.format("https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json", REGION, POOL_ID));
            File jwksFile = new File("jwks.json");
            FileUtils.copyURLToFile(jwksURL, jwksFile);
            jwks = mapper.readValue(jwksFile, Keys.class);
            logger.debug("Keys are loaded. Here's e: " + jwks.getKeys().get(0).getE());
        } catch (IOException ioException) {
            logger.error("Cannot load json..." + ioException.getMessage(), ioException);
        } catch (Exception e) {
            logger.error("Error loading json" + e.getMessage(), e);
        }
    }

    /**
     * Read in the cognito props stored in context
     */

    private void loadProperties() {
        ServletContext context = getServletContext();
        CLIENT_ID = context.getAttribute("CLIENT_ID").toString();
        CLIENT_SECRET = context.getAttribute("CLIENT_SECRET").toString();
        OAUTH_URL = context.getAttribute("OAUTH_URL").toString();
        LOGIN_URL = context.getAttribute("LOGIN_URL").toString();
        REDIRECT_URL = context.getAttribute("REDIRECT_URL").toString();
        REGION = context.getAttribute("REGION").toString();
        POOL_ID = context.getAttribute("POOL_ID").toString();
    }
}

