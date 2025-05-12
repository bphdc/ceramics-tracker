package controller;

import entity.*;
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
 * A servlet to view a project
 * @author pete
 */
@WebServlet("/viewProject")
public class ViewProject extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ViewProfile.class);
    private static GenericDao<User> userDao;
    private static GenericDao<Project> projectDao;
    private static GenericDao<Glaze> glazeDao;
    private static GenericDao<Tag> tagDao;

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
        String projectIdParam = request.getParameter("projectId");
        Integer projectId = null;
        int userId = (int) session.getAttribute("userId");

        userDao = new GenericDao<>(User.class);
        projectDao = new GenericDao<>(Project.class);
        glazeDao = new GenericDao<>(Glaze.class);
        tagDao = new GenericDao<>(Tag.class);
        log.info("view project class doing stuff...");

        if (projectIdParam != null) {
            try {
                projectId = Integer.parseInt(projectIdParam); //view any user's profile
                log.info("project id is " + projectId);
            } catch (NumberFormatException e) {
                log.error("Invalid userId parameter: " + projectIdParam);
                response.sendRedirect("error.jsp"); //need to make this still I think TODO
                return;
            }
        } else {
            projectId = (Integer) session.getAttribute("projectId"); //default to logged-in user
            if (projectId == null) {
                response.sendRedirect("login.jsp");
                log.info("project id is null");
                return;
            }
        }

        Project project = projectDao.getById(projectId);
        log.info("project is " + project);
        List<ProjectEntry> projectEntries = project.getProjectEntries();
        List<Image> projectImages = project.getImages();
        List<ProjectGlaze> projectGlazes = project.getGlazes();
        List<ProjectTag> projectTags = project.getTags();
        log.info("projectTags is " + projectTags);
        User loggedInUser = ServletHelper.getLoggedInUser(request, response);
        User projectUser = project.getUser();

        List<Glaze> glazes = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        if (!projectGlazes.isEmpty()) {
            for (ProjectGlaze projectGlaze : projectGlazes) {
                Glaze glaze = (projectGlaze.getGlaze());
                glazes.add(glaze);
            }
        }
        if(!projectTags.isEmpty()) {
            for (ProjectTag projectTag : projectTags) {
                Tag tag = (projectTag.getTag());
                tags.add(tag);
            }
            log.info("tags is " + tags);
        }


        if (loggedInUser != null) {
            request.setAttribute("loggedInUser", loggedInUser);
            request.setAttribute("projectUser", projectUser);
            request.setAttribute("project", project);
            request.setAttribute("projectEntries", projectEntries);
            request.setAttribute("projectImages", projectImages);
            request.setAttribute("glazes", glazes);
            request.setAttribute("tags", tags);
            request.getRequestDispatcher("/viewProject.jsp").forward(request, response);
        } else {
            log.error("User not found with ID: " + userId);
            response.sendRedirect("error.jsp");
        }
    }
}

