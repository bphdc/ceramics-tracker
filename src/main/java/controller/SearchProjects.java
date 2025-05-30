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
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A servlet to search for prj.
 * @author pete
 */

@WebServlet("/searchProjects")
public class SearchProjects extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(SearchProjects.class);
    private GenericDao<Project> projectDao;
    private GenericDao<User> userDao;
    private GenericDao<Glaze> glazeDao;
    private GenericDao<Tag> tagDao;
    private GenericDao<ProjectTag> projectTagDao;
    private GenericDao<ProjectGlaze> projectGlazeDao;
    User loggedInUser;

    @Override
    public void init() {
        projectDao = new GenericDao<>(Project.class);
        userDao = new GenericDao<>(User.class);
        glazeDao = new GenericDao<>(Glaze.class);
        tagDao = new GenericDao<>(Tag.class);
        projectTagDao = new GenericDao<>(ProjectTag.class);
        projectGlazeDao = new GenericDao<>(ProjectGlaze.class);

    }

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
        String searchType = request.getParameter("searchType"); //don't use helper here - need to know if search type is null to direct ppl to search page landing
        List<Project> projects = new ArrayList<>();
        loggedInUser = ServletHelper.getLoggedInUser(request, response);
        request.setAttribute("availableGlazes", glazeDao.getAll());
        request.setAttribute("availableTags", tagDao.getAll());


        //go to search projects page if a search request isn't made yet - otherwise go thru switch statement and send user to results page
        if (searchType == null || searchType.isEmpty()) {
            request.getRequestDispatcher("searchProjects.jsp").forward(request, response);
            return;
        }

        switch (searchType) {
            case "myProjects":
                projects = projectDao.getByPropertyEqual("user", loggedInUser);
                break;
            case "allProjects":
                projects = projectDao.getAll();
                break;
            case "byTag":
                int tagId = ServletHelper.getIntParam(request,response,"tag");
                Tag tag = tagDao.getById(tagId);
                List<ProjectTag> projectTags = projectTagDao.getByPropertyEqual("tag", tag);
                for (ProjectTag projectTag : projectTags) {
                    Project project = projectTag.getProject();
                    projects.add(project);
                }
                break;
            case "byGlaze":
                int glazeId =ServletHelper.getIntParam(request,response,"glaze");
                Glaze glaze = glazeDao.getById(glazeId);
                List<ProjectGlaze> projectGlazes = projectGlazeDao.getByPropertyEqual("glaze", glaze);
                for (ProjectGlaze projectGlaze : projectGlazes) {
                    Project project = projectGlaze.getProject();
                    projects.add(project);
                }
                break;
            case "byKeyword":
                String keyword = ServletHelper.getStringParam(request,response,"keyword");
                List<Project> initialProjects;
                initialProjects = (projectDao.getByPropertyLike("name", keyword));
                initialProjects.addAll(projectDao.getByPropertyLike("description", keyword));
                //remove dups
                Set<Integer> finalProjects = new HashSet<>();
                for (Project project : initialProjects) {
                    if (finalProjects.add(project.getProjectId())) {
                        projects.add(project);
                    }
                }
                break;
        }

        request.setAttribute("projects", projects);
        request.getRequestDispatcher("viewProjectResults.jsp").forward(request, response);
    }
}

