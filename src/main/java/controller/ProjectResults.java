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
 * A servlet to view projects
 * @author pete
 */

@WebServlet("/projectResults")
public class ProjectResults extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(ProjectResults.class);

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
        List<Project> projects = (List<Project>) request.getAttribute("projects");
        request.setAttribute("projects", projects);
        request.getRequestDispatcher("viewProjectResults.jsp").forward(request, response);
    }
}

