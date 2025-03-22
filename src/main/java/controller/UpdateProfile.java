package controller;


import entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * A servlet to add the prj.
 * @author pete
 */

@WebServlet("/updateProfile")
public class UpdateProfile extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";
    private static final Logger log = LoggerFactory.getLogger(UpdateProfile.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        GenericDao<User> userDao = new GenericDao<>(User.class);
        int userId = (int) session.getAttribute("userId");
        User user = userDao.getById(userId);

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (!ServletFileUpload.isMultipartContent(request)) {
            log.error("Form must have enctype=multipart/form-data.");
            response.sendRedirect("updateProfile.jsp?error=invalidForm");
            return;
        }

        try {
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> formItems = upload.parseRequest(request);

            String bio = null;
            String profilePictureUrl = null;

            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    //figre out the bio first
                    if ("bio".equals(item.getFieldName())) {
                        bio = item.getString();
                        log.info("received bio: " + bio);
                    }
                } else {
                    //now figure out the profile pic
                    if ("profilePicture".equals(item.getFieldName()) && item.getSize() > 0) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
                        File uploadDir = new File(filePath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }

                        File storeFile = new File(uploadDir, fileName);
                        item.write(storeFile); // Save the file
                        profilePictureUrl = UPLOAD_DIR + "/" + fileName;
                        log.info("uploaded file: " + profilePictureUrl);
                    }
                }
            }

            //update fields on user object
            if (bio != null) {
                user.setBio(bio);
            }
            if (profilePictureUrl != null) {
                user.setProfilePicture(profilePictureUrl);
            }

            //shoot that to the DB
            userDao.saveOrUpdate(user);
            log.info("Updated user: " + user);

            response.sendRedirect("viewProfile.jsp");
        } catch (Exception ex) {
            log.error("Error processing form: ", ex);
            response.sendRedirect("updateProfile.jsp?error=uploadFailed");
        }
    }


    //i need a doGet too to display current bio and pic before update
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(userId);

        if (user != null) {
            request.setAttribute("user", user);
            if (user.getProfilePicture() == null) {
                user.setProfilePicture("images/noprofilepicture.png");
            }
            request.getRequestDispatcher("/updateProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}

