package controller;

import entity.Project;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * A servlet to add the tag.
 * @author pete
 */

@WebServlet("/addTag")
public class AddTag extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(AddTag.class);
    private static GenericDao<Tag> tagDao = new GenericDao<>(Tag.class);
    private static Tag tag = new Tag();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addTag.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ServletHelper.getLoggedInUser(request);

        if (ServletHelper.isAdmin(user)) {
            String name = request.getParameter("name");
            tag.setName(name);
            tag.setCreatedAt(Timestamp.from(Instant.now()));
            tagDao.insert(tag);
            response.sendRedirect("tagLibrary");
        }
        else {
            ServletHelper.sendToErrorPageWithMessage(request, response, "Permission denied - reach out to site administrator");
        }
    }
}
