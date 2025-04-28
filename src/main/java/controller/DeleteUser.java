package controller;


import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;
import util.CognitoService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * A servlet to delete a user
 * @author pete
 */
@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DeleteUser.class);
    private static GenericDao<User> userDao = new GenericDao<>(User.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String ACCESS_KEY = (String) context.getAttribute("ACCESS_KEY");
        String SECRET_KEY = (String) context.getAttribute("SECRET_KEY");
        HttpSession session = request.getSession(false);
        Integer loggedInUserId = (Integer) session.getAttribute("userId");
        User user = userDao.getById(loggedInUserId);
        String POOL_ID = context.getAttribute("POOL_ID").toString();

        try {
            userDao.delete(user);
            CognitoService cognitoService = new CognitoService(ACCESS_KEY, SECRET_KEY);
            cognitoService.deleteUserFromCognito(user.getUsername(), POOL_ID);
            session.invalidate();
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Integer loggedInUserId = (Integer) session.getAttribute("userId");
        User user = userDao.getById(loggedInUserId);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/deleteUser.jsp").forward(request, response);
    }
}
