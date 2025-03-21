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
        Properties prop = new Properties();
        try {
            Properties properties = loadProperties("/cognito.properties");
            context.setAttribute("cognitoProperties", properties);

            String clientId = properties.getProperty("client.id");
            String clientSecret = properties.getProperty("client.secret");
            String oauthUrl = properties.getProperty("oauthURL");
            String loginUrl = properties.getProperty("loginURL");
            String region = properties.getProperty("region");
            String poolId = properties.getProperty("poolId");

            // Determine redirect URL based on environment
            String environment = System.getenv("AWS_EXECUTION_ENV");
            String redirectUrl = (environment != null && environment.contains("AWS"))
                    ? properties.getProperty("redirectURL.aws")
                    : properties.getProperty("redirectURL.local");

            // Store values in ServletContext
            context.setAttribute("CLIENT_ID", clientId);
            context.setAttribute("CLIENT_SECRET", clientSecret);
            context.setAttribute("OAUTH_URL", oauthUrl);
            context.setAttribute("LOGIN_URL", loginUrl);
            context.setAttribute("REGION", region);
            context.setAttribute("POOL_ID", poolId);
            context.setAttribute("REDIRECT_URL", redirectUrl);

            logger.info("Cognito properties successfully loaded at startup.");

        } catch (IOException ioException) {
            logger.error("Cannot load properties...{}", ioException.getMessage(), ioException);
        } catch (Exception e) {
            logger.error("Error loading properties{}", e.getMessage(), e);
        }
    }
}
