package controller;

import util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ServletHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(
        urlPatterns = {"/logIn"}
)

/** Begins the authentication process using AWS Cognito
 *
 */
public class LogIn extends HttpServlet implements PropertiesLoader {
    Properties properties;
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static String CLIENT_ID;
    public static String LOGIN_URL;
    public static String REDIRECT_URL;



    /**
     * Handles the HTTP GET request.
     *
     * @param req     the HttpServletRequest object that contains the request the client made
     * @param resp the HttpServletResponse object that contains the response the servlet returns
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        CLIENT_ID = (String) context.getAttribute("CLIENT_ID");
        LOGIN_URL = (String) context.getAttribute("LOGIN_URL");
        REDIRECT_URL = (String) context.getAttribute("REDIRECT_URL");

        if (CLIENT_ID == null || CLIENT_ID.isEmpty() || LOGIN_URL == null || LOGIN_URL.isEmpty() || REDIRECT_URL == null || REDIRECT_URL.isEmpty()) {
            ServletHelper.sendToErrorPageWithMessage(req, resp, "Something went wrong - if problem persists, reach out to site administrator");
        }

        String url = LOGIN_URL + "?response_type=code&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL;
        resp.sendRedirect(url);
    }
}
