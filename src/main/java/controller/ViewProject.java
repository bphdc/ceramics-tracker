package controller;

import entity.Image;
import entity.ProjectEntry;
import entity.User;
import entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/viewProject")
public class ViewProject extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ViewProfile.class);
    private static GenericDao<User> userDao;
    private static GenericDao<Project> projectDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String projectIdParam = request.getParameter("projectId");
        Integer projectId = null;
        int userId = (int) session.getAttribute("userId");

        userDao = new GenericDao<>(User.class);
        projectDao = new GenericDao<>(Project.class);
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
        User loggedInUser = userDao.getById(userId);
        User projectUser = project.getUser();

        if (loggedInUser != null) {
            request.setAttribute("loggedInUser", loggedInUser);
            request.setAttribute("projectUser", projectUser);
            request.setAttribute("project", project);
            request.setAttribute("projectEntries", projectEntries);
            request.setAttribute("projectImages", projectImages);
            request.getRequestDispatcher("/viewProject.jsp").forward(request, response);
        } else {
            log.error("User not found with ID: " + userId);
            response.sendRedirect("error.jsp");
        }
    }
}

