package controller;

import entity.Project;
import entity.ProjectEntry;
import entity.Tag;
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
import java.io.IOException;

/**
 * A servlet to edit entry.
 * @author pete
 */

@WebServlet("/editEntry")
public class EditEntry extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(EditEntry.class);
    private static GenericDao<ProjectEntry> entryDao = new GenericDao<>(ProjectEntry.class);
    private static GenericDao<Project> projectDao = new GenericDao<>(Project.class);
    private static ProjectEntry entry = new ProjectEntry();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entryIdParam = request.getParameter("id");
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        Project project = projectDao.getById(projectId);
        entry = entryDao.getById(Integer.parseInt(entryIdParam));
        request.setAttribute("entry", entry);
        request.setAttribute("project", project);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editEntry.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        Project project = projectDao.getById(projectId);


        if (ServletHelper.isLoggedInUserProjectOwner(request, response, project.getProjectId()) ) {

            String entryText = request.getParameter("entryText");
            entry.setEntryText(entryText);

            entryDao.saveOrUpdate(entry);
            response.sendRedirect("viewProject?projectId=" + projectId);
        }
        else {
            log.info("the project is {}", project);
            ServletHelper.sendToErrorPageWithMessage(request, response, "Permission denied - reach out to site administrator");
        }

    }
}
