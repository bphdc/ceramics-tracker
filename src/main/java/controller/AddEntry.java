package controller;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * A servlet to add the prj.
 * @author pete
 */

@WebServlet("/addEntry")
public class AddEntry extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(AddEntry.class);
    private GenericDao<Project> projectDao = new GenericDao<>(Project.class);
    private GenericDao<ProjectEntry> entryDao = new GenericDao<>(ProjectEntry.class);
    private GenericDao<User> userDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        Project project = projectDao.getById(projectId);


        if (ServletHelper.isLoggedInUserProjectOwner(request, project.getProjectId()) ) {

            String entryText = request.getParameter("entryText");
            if (entryText != null && !entryText.trim().isEmpty()) {
                ProjectEntry entry = new ProjectEntry();
                entry.setProject(project);
                entry.setEntryText(entryText);
                entry.setCreatedAt(Timestamp.from(Instant.now()));
                entryDao.saveOrUpdate(entry);
                response.sendRedirect("viewProject?projectId=" + project.getProjectId());
            }
            else {
                ServletHelper.sendToErrorPageWithMessage(request, response, "Error - entry text is cannot be blank");
            }

        }
        else {
            ServletHelper.sendToErrorPageWithMessage(request, response, "Error - if problem persists reach out to site admin for help");
        }

    }
}
