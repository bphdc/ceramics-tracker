package controller;

import entity.Glaze;
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
import java.sql.Timestamp;
import java.time.Instant;

/**
 * A servlet to edit the glaze.
 * @author pete
 */

@WebServlet("/editGlaze")
public class EditGlaze extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(EditGlaze.class);
    private static GenericDao<Glaze> glazeDao = new GenericDao<>(Glaze.class);
    private static Glaze glaze = new Glaze();

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
        String glazeIdParam = request.getParameter("glazeId");
        glaze = glazeDao.getById(Integer.parseInt(glazeIdParam));
        request.setAttribute("glaze", glaze);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editGlaze.jsp");
        dispatcher.forward(request, response);
    }

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
        User user = ServletHelper.getLoggedInUser(request, response);

        if (ServletHelper.isAdmin(user)) {

            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String type = request.getParameter("glazeType");

            glaze.setName(name);
            glaze.setDescription(description);
            glaze.setType(type);

            glazeDao.saveOrUpdate(glaze);
            response.sendRedirect("glazeLibrary");
        }
        else {
            ServletHelper.sendToErrorPageWithMessage(request, response, "Permission denied - reach out to site administrator");
        }

    }
}
