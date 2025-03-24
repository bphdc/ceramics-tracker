package controller;

import entity.Project;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;
import util.PropertiesLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Properties;

/**
 * A servlet to add the prj.
 * @author pete
 */

@WebServlet(name = "AppStartup", loadOnStartup = 1)
public class AppStartup extends HttpServlet implements PropertiesLoader {
    private final Logger logger = LoggerFactory.getLogger(AppStartup.class);
    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = config.getServletContext();
        try {
            Properties cognitoProps = loadProperties("/cognito.properties");
            Properties awsProps = loadProperties("/aws.properties");
            Properties hiveProperties = loadProperties("/hiveai.properties");
            context.setAttribute("cognitoProperties", cognitoProps);

            String clientId = cognitoProps.getProperty("client.id");
            String clientSecret = cognitoProps.getProperty("client.secret");
            String oauthUrl = cognitoProps.getProperty("oauthURL");
            String loginUrl = cognitoProps.getProperty("loginURL");
            String region = cognitoProps.getProperty("region");
            String poolId = cognitoProps.getProperty("poolId");

            String accessKey = awsProps.getProperty("access.key");
            String secretKey = awsProps.getProperty("secret.key");

            String hiveApiKey = hiveProperties.getProperty("apikey");

            String environment = System.getenv("ELASTIC_BEANSTALK_ENVIRONMENT_NAME");

            String redirectUrl = (environment != null)
                    ? cognitoProps.getProperty("redirectURL.aws")
                    : cognitoProps.getProperty("redirectURL.local");

            context.setAttribute("CLIENT_ID", clientId);
            context.setAttribute("CLIENT_SECRET", clientSecret);
            context.setAttribute("OAUTH_URL", oauthUrl);
            context.setAttribute("LOGIN_URL", loginUrl);
            context.setAttribute("REGION", region);
            context.setAttribute("POOL_ID", poolId);
            context.setAttribute("REDIRECT_URL", redirectUrl);

            context.setAttribute("ACCESS_KEY", accessKey);
            context.setAttribute("SECRET_KEY", secretKey);

            context.setAttribute("HIVE_API_KEY", hiveApiKey);

            logger.info("properties successfully loaded at startup.");

        } catch (IOException ioException) {
            logger.error("Cannot load properties...{}", ioException.getMessage(), ioException);
        } catch (Exception e) {
            logger.error("Error loading properties{}", e.getMessage(), e);
        }
    }
}
