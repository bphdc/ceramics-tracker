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


@WebServlet("/deleteTag")
public class DeleteTag extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DeleteTag.class);
    private static GenericDao<Tag> tagDao = new GenericDao<>(Tag.class);
    private static Tag tag = new Tag();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ServletHelper.getLoggedInUser(request);

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tagIdParam = request.getParameter("tagId");
        tag = tagDao.getById(Integer.parseInt(tagIdParam));
        request.setAttribute("tag", tag);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteTag.jsp");
        dispatcher.forward(request, response);
    }
}
