package controller;

import entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;

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
import java.util.List;

/**
 * A servlet to add the prj.
 * @author pete
 */

@WebServlet("/editProject")
public class EditProject extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(EditProject.class);
    private GenericDao<Project> projectDao;
    private GenericDao<User> userDao;
    private GenericDao<Glaze> glazeDao;
    private GenericDao<Tag> tagDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        projectDao = new GenericDao<>(Project.class);
        userDao = new GenericDao<>(User.class);
        glazeDao = new GenericDao<>(Glaze.class);
        tagDao = new GenericDao<>(Tag.class);
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userDao.getById(userId);
        Integer projectUserId = -1;


        String projectIdStr = req.getParameter("projectId");
        if (projectIdStr != null) {
            int projectId = Integer.parseInt(projectIdStr);
            Project project = projectDao.getById(projectId);
            projectUserId = project.getUser().getId();
            List<Image> images = project.getImages();

            //if the user isn't the project owner then bail
            if (!userId.equals(projectUserId)) {
                log.info("user not project owner");
                resp.sendRedirect("index.jsp"); //TODO maybe generic error page
                return;
            }

            req.setAttribute("project", project);
            req.setAttribute("images", images);
            //pass all glazes and tags to the for selection
            req.setAttribute("availableGlazes", glazeDao.getAll()); //todo im unsure if igot this right. probably add in glaze/tag crud before coming back to this
            req.setAttribute("availableTags", tagDao.getAll());

            //also pass currently selected glazes and tags
            req.setAttribute("selectedGlazes", project.getGlazes());
            req.setAttribute("selectedTags", project.getTags());

            //and entries
            req.setAttribute("entries", project.getProjectEntries());

        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editProject.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        User user = userDao.getById(userId);

        //get the project details from the form
        String projectName = req.getParameter("name");
        String projectDescription = req.getParameter("description");
        String[] selectedGlazes = req.getParameterValues("glazes");
        String[] selectedTags = req.getParameterValues("tags");

        //get the project id/project
        String projectIdStr = req.getParameter("projectId");
        int projectId = Integer.parseInt(projectIdStr);
        Project project = projectDao.getById(projectId);

        // Update the project fields
        project.setName(projectName);
        project.setDescription(projectDescription);


        //TODO logic to associate selected glazes and tags


        projectDao.saveOrUpdate(project);


        req.setAttribute("project", project);
        resp.sendRedirect("viewProject?projectId=" + projectId);
    }
}