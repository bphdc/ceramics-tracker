package controller;

import entity.Image;
import entity.ProjectEntry;
import entity.User;
import entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;
import util.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A servlet to view a single profile
 * @author pete
 */
@WebServlet("/viewProfile")
public class ViewProfile extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ViewProfile.class);
    private static GenericDao<User> userDao;
    private static GenericDao<Project> projectDao;

    /**
     * Handles the HTTP GET request.
     *
     * @param request  the HttpServletRequest object that contains the request the client made
     * @param response the HttpServletResponse object that contains the response the servlet returns
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userIdParam = request.getParameter("userId");
        Integer userId = null;

        userDao = new GenericDao<>(User.class);
        projectDao = new GenericDao<>(Project.class);

        if (userIdParam != null) {
            try {
                userId = Integer.parseInt(userIdParam); //view any user's profile
            } catch (NumberFormatException e) {
                log.error("Invalid userId parameter: " + userIdParam);
                ServletHelper.sendToErrorPageWithMessage(request, response, "Sorry there was a problem. try again");
                return;
            }
        } else {
            userId = (Integer) session.getAttribute("userId"); //default to logged-in user
            if (userId == null) {
                ServletHelper.sendToErrorPageWithMessage(request, response, "Sorry there was a problem try again");
                return;
            }
        }

        User user = userDao.getById(userId); //this could be logged in user OR another so just make the db request
        if (user != null) {
            List<Project> projects = projectDao.getByPropertyEqual("user", user);
            request.setAttribute("user", user);
            request.setAttribute("projects", projects);
            request.getRequestDispatcher("/viewProfile.jsp").forward(request, response);
        } else {
            log.error("User not found with ID: " + userId);
            ServletHelper.sendToErrorPageWithMessage(request, response, "Please try again and if problem persists reach out to site admin");
        }
    }
}

