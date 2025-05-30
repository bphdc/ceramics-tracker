package controller;

import entity.Glaze;
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
import java.io.IOException;


/**
 * A servlet to delete a tag
 * @author pete
 */
@WebServlet("/deleteTag")
public class DeleteTag extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DeleteTag.class);
    private static GenericDao<Tag> tagDao = new GenericDao<>(Tag.class);
    private static Tag tag = new Tag();

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
            String tagIdParam = request.getParameter("tagId");
            tag = tagDao.getById(Integer.parseInt(tagIdParam));
            tagDao.delete(tag);
            response.sendRedirect("tagLibrary");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tagIdParam = request.getParameter("tagId");
        tag = tagDao.getById(Integer.parseInt(tagIdParam));
        request.setAttribute("tag", tag);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteTag.jsp");
        dispatcher.forward(request, response);
    }
}
