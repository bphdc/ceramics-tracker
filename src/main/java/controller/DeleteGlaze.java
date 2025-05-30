package controller;

import entity.Glaze;
import entity.Project;
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


/**
 * A servlet to delete a glaze
 * @author pete
 */
@WebServlet("/deleteGlaze")
public class DeleteGlaze extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DeleteGlaze.class);
    private static GenericDao<Glaze> glazeDao = new GenericDao<>(Glaze.class);
    private static Glaze glaze = new Glaze();

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
            int glazeIdParam = ServletHelper.getIntParam(request, response, "glazeId"); //request.getParameter("glazeId");
            glaze = glazeDao.getById(glazeIdParam);
            glazeDao.delete(glaze);
            response.sendRedirect("glazeLibrary");
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
        int glazeIdParam = ServletHelper.getIntParam(request, response, "glazeId"); //request.getParameter("glazeId");
        glaze = glazeDao.getById((glazeIdParam));
        request.setAttribute("glaze", glaze);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteGlaze.jsp");
        dispatcher.forward(request, response);
    }
}
