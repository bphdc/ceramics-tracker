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
import java.sql.Timestamp;
import java.time.Instant;

/**
 * A servlet to add the glaze.
 * @author pete
 */

@WebServlet("/addGlaze")
public class AddGlaze extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(AddGlaze.class);
    private static GenericDao<Glaze> glazeDao = new GenericDao<>(Glaze.class);
    private static Glaze glaze = new Glaze();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addGlaze.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ServletHelper.getLoggedInUser(request);

        if (ServletHelper.isAdmin(user)) {

            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String type = request.getParameter("glazeType");

            glaze.setName(name);
            glaze.setDescription(description);
            glaze.setType(type);
            glaze.setCreatedAt(Timestamp.from(Instant.now()));
            glazeDao.insert(glaze);
            response.sendRedirect("glazeLibrary");
        }
        else {
            ServletHelper.sendToErrorPageWithMessage(request, response, "Permission denied - reach out to site administrator");
        }

    }
}
