package controller;

import entity.Project;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;
import util.ServletHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * A servlet to add the prj.
 * @author pete
 */

@WebServlet("/addProject")
public class AddProject extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(AddProject.class);
    private GenericDao<Project> projectDao;
    private GenericDao<User> userDao;

    @Override
    public void init() {
        projectDao = new GenericDao<>(Project.class);
        userDao = new GenericDao<>(User.class);
    }

    /**
     * Handles the HTTP GET request.
     *
     * @param req  the HttpServletRequest object that contains the request the client made
     * @param resp the HttpServletResponse object that contains the response the servlet returns
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/addProject.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Handles the HTTP POST request.
     *
     * @param req     the HttpServletRequest object that contains the request the client made
     * @param resp the HttpServletResponse object that contains the response the servlet returns
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("userName");

        if (userName == null) {
            log.info("userName is null - User not logged in?");
            ServletHelper.sendToErrorPageWithMessage(req, resp, "Please log in first");
            return;
        }

        User user = userDao.getByPropertyEqual("username", userName).get(0);
        if (user == null) {
            log.info("User not in db");
            ServletHelper.sendToErrorPageWithMessage(req, resp, "Please log in first");
            return;
        }

        String projectName = req.getParameter("name");
        String projectDescription = req.getParameter("description");

        Project project = new Project();
        project.setUser(user);
        project.setName(projectName);
        project.setDescription(projectDescription);
        project.setCreatedAt(Timestamp.from(Instant.now()));

        int projectId = projectDao.insert(project);

        req.setAttribute("projectId", projectId);
        req.getRequestDispatcher("/addProject.jsp").forward(req, resp);
    }
}
