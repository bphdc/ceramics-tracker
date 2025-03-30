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


@WebServlet("/deleteGlaze")
public class DeleteGlaze extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DeleteGlaze.class);
    private static GenericDao<Glaze> glazeDao = new GenericDao<>(Glaze.class);
    private static Glaze glaze = new Glaze();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ServletHelper.getLoggedInUser(request);

        if (ServletHelper.isAdmin(user)) {
            String glazeIdParam = request.getParameter("glazeId");
            glaze = glazeDao.getById(Integer.parseInt(glazeIdParam));
            glazeDao.delete(glaze);
            response.sendRedirect("glazeLibrary");
        }
        else {
            ServletHelper.sendToErrorPageWithMessage(request, response, "Permission denied - reach out to site administrator");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String glazeIdParam = request.getParameter("glazeId");
        glaze = glazeDao.getById(Integer.parseInt(glazeIdParam));
        request.setAttribute("glaze", glaze);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteGlaze.jsp");
        dispatcher.forward(request, response);
    }
}
