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
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A servlet to add the prj.
 * @author pete
 */

@WebServlet("/searchProjects")
public class SearchProjects extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(SearchProjects.class);
    private GenericDao<Project> projectDao;
    private GenericDao<User> userDao;
    User loggedInUser;

    @Override
    public void init() {
        projectDao = new GenericDao<>(Project.class);
        userDao = new GenericDao<>(User.class);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchType = request.getParameter("searchType");
        List<Project> projects = new ArrayList<>();
        int userId = (int) request.getSession().getAttribute("userId");
        loggedInUser = userDao.getById(userId);


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
                String tag = request.getParameter("tag");
                projects = projectDao.getByPropertyEqual("tag", tag);
                break;
            case "byGlaze":
                String glaze = request.getParameter("glaze");
                projects = projectDao.getByPropertyEqual("glaze", glaze);
                break;
            case "byKeyword":
                String keyword = request.getParameter("keyword");
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
        request.getRequestDispatcher("resultsProjects.jsp").forward(request, response);
    }
}

