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

    /**
     * Handles the HTTP POST request.
     *
     * @param request  the HttpServletRequest object that contains the request the client made
     * @param response the HttpServletResponse object that contains the response the servlet returns
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectId = ServletHelper.getIntParam(request,response,"projectId");
        Project project = projectDao.getById(projectId);


        if (ServletHelper.isLoggedInUserProjectOwner(request, response, project.getProjectId()) ) {
            int entryId = ServletHelper.getIntParam(request,response,"entryId");
            entry = entryDao.getById(entryId);
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
