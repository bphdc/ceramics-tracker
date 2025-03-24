package controller;

import entity.User;
import entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewProfile")
public class ViewProfile extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ViewProfile.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Project> projectDao = new GenericDao<>(Project.class);

        User user = userDao.getById(userId);
        List<Project> projects = projectDao.getByPropertyEqual("user", user);

        if (user != null) {
            request.setAttribute("user", user);
            request.setAttribute("projects", projects);
            request.getRequestDispatcher("/viewProfile.jsp").forward(request, response);
        } else {
            log.error("User not found with ID: " + userId);
            response.sendRedirect("login.jsp");
        }
    }
}

