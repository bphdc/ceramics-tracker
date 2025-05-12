package controller;

import entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;
import util.ServletHelper;

import javax.servlet.RequestDispatcher;
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
import java.util.ArrayList;
import java.util.List;

/**
 * A servlet to edit the prj.
 * @author pete
 */

@WebServlet("/editProject")
public class EditProject extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(EditProject.class);
    private GenericDao<Project> projectDao;
    private GenericDao<User> userDao;
    private GenericDao<Glaze> glazeDao;
    private GenericDao<Tag> tagDao;

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
        projectDao = new GenericDao<>(Project.class);
        userDao = new GenericDao<>(User.class);
        glazeDao = new GenericDao<>(Glaze.class);
        tagDao = new GenericDao<>(Tag.class);
        HttpSession session = req.getSession();
        Integer projectUserId = -1;


        String projectIdStr = req.getParameter("projectId");
        if (projectIdStr != null) {
            int projectId = Integer.parseInt(projectIdStr);
            Project project = projectDao.getById(projectId);
            projectUserId = project.getUser().getId();
            List<Image> images = project.getImages();

            //if the user isn't the project owner then bail
            if (!ServletHelper.isLoggedInUserProjectOwner(req,resp,projectId)) {
                log.info("user not project owner");
                ServletHelper.sendToErrorPageWithMessage(req, resp, "user not project owner");
                return;
            }

            req.setAttribute("project", project);
            req.setAttribute("images", images);
            //pass all glazes and tags to the for selection
            req.setAttribute("availableGlazes", glazeDao.getAll());
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

    /**
     * Handles the HTTP POST request.
     *
     * @param req  the HttpServletRequest object that contains the request the client made
     * @param resp the HttpServletResponse object that contains the response the servlet returns
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();



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
        List<ProjectGlaze> projectGlazeList = new ArrayList<>();
        List<ProjectTag> projectTagList = new ArrayList<>();
        if(selectedGlazes != null) {
            log.info("selected glazes: " + selectedGlazes.length);
            for (String selectedGlaze : selectedGlazes) {
                int glazeId = Integer.parseInt(selectedGlaze);
                Glaze glaze = glazeDao.getById(glazeId);
                log.info(glaze.toString());
                ProjectGlaze projectGlaze = new ProjectGlaze();
                projectGlaze.setGlaze(glaze);
                projectGlaze.setProject(project);
                projectGlazeList.add(projectGlaze);
            }
        }
        if (selectedTags != null) {
            for (String selectedTag : selectedTags) {
                int tagId = Integer.parseInt(selectedTag);
                Tag tag = tagDao.getById(tagId);
                ProjectTag projectTag = new ProjectTag();
                projectTag.setTag(tag);
                projectTag.setProject(project);
                projectTagList.add(projectTag);
            }
        }
        if (projectGlazeList != null) {
            project.setGlazes(projectGlazeList);
        }
        if (projectTagList != null) {
            project.setTags(projectTagList);
        }


        projectDao.saveOrUpdate(project);


        req.setAttribute("project", project);
        resp.sendRedirect("viewProject?projectId=" + projectId);
    }
}