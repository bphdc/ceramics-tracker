package util;

import com.amazonaws.services.neptunedata.model.GremlinQueryStatus;
import entity.Project;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletHelper {

    private static GenericDao<User> userDao = new GenericDao<>(User.class);
    private static GenericDao<Project> projectDao = new GenericDao<>(Project.class);
    private static Logger logger = LogManager.getLogger(ServletHelper.class);

    /**
     * is user admin?
     * @param user the user
     * @return boolean t or f
     */
    public static Boolean isAdmin(User user) {
        boolean isAdmin = false;
        if (user.getRole().equals("admin")) {
            isAdmin = true;
        }
        return isAdmin;
    }



    /**
     * is user logged in?
     * @param request the req
     * @param projectId project id
     * @return boolean t or f
     */
    public static Boolean isLoggedInUserProjectOwner(HttpServletRequest request, HttpServletResponse response ,int projectId) throws ServletException, IOException {
        User user = getLoggedInUser(request, response);
        Project project = projectDao.getById(projectId);
        User projectUser = project.getUser();
        if (user.getId() == projectUser.getId()) {
            return true;
        }
        else {
            logger.error("user is not owner of project: {}", projectId);
            return false;
        }
    }



    /**
     * gets logged in user object via session
     * @param request
     * @return
     */
    public static User getLoggedInUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        User user = (User) session.getAttribute("loggedInUser");
        if (userId == null) {sendToErrorPageWithMessage(request, response, "Please log in to continue");}
        return user;
    }

    /**
     * sends user to error page with a note
     * @param request
     * @return
     */
    public static void sendToErrorPageWithMessage (HttpServletRequest request,  HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    /**
     * Checks if string param is valid
     *
     * @param request
     * @param paramName
     * @param response
     * @return
     * @throws IOException
     */
    public static String getStringParam(HttpServletRequest request,  HttpServletResponse response, String paramName) throws ServletException, IOException {
        String value = request.getParameter(paramName);
        if (value == null || value.isBlank()) {
            sendToErrorPageWithMessage(request, response, "Sorry something went wrong. If the problem persists, reach out to the site administrator.");
            return null;
        }
        return value;
    }

    /**
     * Checks if int param is valid
     * @param request
     * @param paramName
     * @param response
     * @return
     * @throws IOException
     */
    public static Integer getIntParam(HttpServletRequest request,  HttpServletResponse response, String paramName) throws IOException, ServletException {
        String value = getStringParam(request,  response, paramName);
        if (value == null) return null;

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            sendToErrorPageWithMessage(request, response, "Sorry something went wrong. If the problem persists, reach out to the site administrator.");
            return null;
        }
    }
}


