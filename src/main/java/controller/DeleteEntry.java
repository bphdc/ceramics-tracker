package controller;

import entity.Glaze;
import entity.Project;
import entity.ProjectEntry;
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
 * A servlet to delete a blog entry
 * @author pete
 */
@WebServlet("/deleteEntry")
public class DeleteEntry extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DeleteEntry.class);
    private static GenericDao<Project> projectDao = new GenericDao<>(Project.class);
    private static GenericDao<ProjectEntry> entryDao = new GenericDao<>(ProjectEntry.class);
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
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        Project project = projectDao.getById(projectId);

        if (ServletHelper.isLoggedInUserProjectOwner(request, response , project.getProjectId()) ) {
            String entryIdParam = ServletHelper.getStringParam(request,response,"entryId");
            entry = entryDao.getById(Integer.parseInt(entryIdParam));
            entryDao.delete(entry);
            response.sendRedirect("viewProject?projectId=" + projectId);
        }
        else {
            ServletHelper.sendToErrorPageWithMessage(request, response, "Permission denied - reach out to site administrator");
        }
    }

    /**
     * Handles the HTTP GET request.
     *
     * @param request  the HttpServletRequest object that contains the request the client made
     * @param response the HttpServletResponse object that contains the response the servlet returns
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int entryIdParam = ServletHelper.getIntParam(request,response,"entryId");
        entry = entryDao.getById((entryIdParam));
        int projectId = ServletHelper.getIntParam(request,response,"projectId");
        Project project = projectDao.getById(projectId);
        request.setAttribute("entry", entry);
        request.setAttribute("project", project);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteEntry.jsp");
        dispatcher.forward(request, response);
    }
}
