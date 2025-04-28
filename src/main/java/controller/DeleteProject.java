package controller;

import entity.Project;
import entity.User;
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
import java.util.List;


@WebServlet("/deleteProject")
public class DeleteProject extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DeleteProject.class);
    private static GenericDao<Project> projectDao = new GenericDao<>(Project.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Integer loggedInUserId = (Integer) session.getAttribute("userId");
        String projectId = request.getParameter("projectId");
        Project project = projectDao.getById(Integer.parseInt(projectId));
        Integer projectUserId = (project.getUser().getId());

        try {
            if (!loggedInUserId.equals(projectUserId)) {
                log.info("logged in user doesn't match project user id"); //TODO create some error pages ?
                response.sendRedirect("error.jsp");
                return;
            }
            projectDao.delete(project);
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectIdParam = request.getParameter("projectId");
        Integer projectId = null;
        projectId = Integer.parseInt(projectIdParam);
        log.info("project id is {}", projectId);
        Project project = projectDao.getById(projectId);
        request.setAttribute("project", project);
        request.getRequestDispatcher("/deleteProject.jsp").forward(request, response);
    }
}
