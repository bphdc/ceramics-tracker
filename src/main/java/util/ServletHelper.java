package util;

import entity.User;
import persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletHelper {

    private static GenericDao<User> userDao = new GenericDao<>(User.class);

    /**
     * is user admin?
     * @param user the user
     * @return boolean t or f
     */
    public static Boolean isAdmin(User user) {
        boolean isAdmin = false;
        if (user.getRole().equals("admin")) {
            isAdmin = true;
        }
        return isAdmin;
    }

    /**
     * gets logged in user object via session
     * @param request
     * @return
     */
    public static User getLoggedInUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        return userDao.getById(userId);
    }

    /**
     * sends user to error page with a note
     * @param request
     * @return
     */
    public static void sendToErrorPageWithMessage (HttpServletRequest request,  HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }


}
