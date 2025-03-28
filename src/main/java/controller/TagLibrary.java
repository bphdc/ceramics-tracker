package controller;

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
 * A servlet for tags.
 * @author pete
 */

@WebServlet("/tagLibrary")
public class TagLibrary extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(TagLibrary.class);
    private static GenericDao<Tag> tagDao = new GenericDao<>(Tag.class);
    private static ServletHelper servletHelper = new ServletHelper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ServletHelper.getLoggedInUser(request);

        if (user.getRole().equals("admin")) {
            List<Tag> tags = tagDao.getAll();
            request.setAttribute("tags", tags);
            request.getRequestDispatcher("tagLibrary.jsp").forward(request, response);
        }
        else {
            servletHelper.sendToErrorPageWithMessage(request, response, "Permission denied - reach out to site administrator");
        }

    }
}

