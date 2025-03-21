package controller;

import entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A servlet to add the prj.
 * @author pete
 */

@WebServlet("/searchProjects")
public class SearchProjects extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(AddProject.class);
    private GenericDao<Project> projectDao;

    @Override
    public void init() {
        projectDao = new GenericDao<>(Project.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchType = request.getParameter("searchType");
        List<Project> projects = new ArrayList<>();
        String userId = (String) request.getSession().getAttribute("userId"); // Assuming session holds user info

        switch (searchType) {
            case "myProjects":
                projects = projectDao.getByPropertyEqual("user_id", userId);
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
                projects = (projectDao.getByPropertyLike("name", keyword));
                projects.addAll(projectDao.getByPropertyLike("description", keyword));
                break;
        }

        request.setAttribute("projects", projects);
        request.getRequestDispatcher("searchResults.jsp").forward(request, response);
    }
}

