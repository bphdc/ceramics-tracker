package controller;

import entity.Glaze;
import entity.Tag;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;
import util.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * A servlet for glazes.
 * @author pete
 */

@WebServlet("/glazeLibrary")
public class GlazeLibrary extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(GlazeLibrary.class);
    private static GenericDao<Glaze> glazeDao = new GenericDao<>(Glaze.class);

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
       User user = ServletHelper.getLoggedInUser(request, response);

        if (user.getRole().equals("admin")) {
            List<Glaze> glazes = glazeDao.getAll();
            request.setAttribute("glazes", glazes);
            request.getRequestDispatcher("glazeLibrary.jsp").forward(request, response);
        }
        else {
            ServletHelper.sendToErrorPageWithMessage(request, response, "Permission denied - reach out to site administrator");
        }

    }
}

