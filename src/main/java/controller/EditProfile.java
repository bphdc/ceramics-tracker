package controller;


import entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;
import util.S3Helper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * A servlet to edit profile
 * @author pete
 */

@WebServlet("/updateProfile")
public class EditProfile extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";
    private static final Logger log = LoggerFactory.getLogger(EditProfile.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        String ACCESS_KEY = (String) context.getAttribute("ACCESS_KEY");
        String SECRET_KEY = (String) context.getAttribute("SECRET_KEY");
        GenericDao<User> userDao = new GenericDao<>(User.class);
        int userId = (int) session.getAttribute("userId");
        User user = userDao.getById(userId);

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (!ServletFileUpload.isMultipartContent(request)) {
            log.error("Form must have enctype=multipart/form-data.");
            response.sendRedirect("editProfile.jsp?error=invalidForm");
            return;
        }

        try {
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> formItems = upload.parseRequest(request);

            String bio = null;
            String name=null;
            String profilePictureUrl = null;

            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    //figure out the bio first and name first
                    if ("bio".equals(item.getFieldName())) {
                        bio = item.getString();
                        log.info("received bio: " + bio);
                    }
                    if ("name".equals(item.getFieldName())) {
                        name = item.getString();
                        log.info("received name: " + name);
                    }

                } else {
                    //now figure out the profile pic
                    if ("profilePicture".equals(item.getFieldName()) && item.getSize() > 0) {
                        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                        String fileName = new File(item.getName()).getName();
                        String originalFileName = fileName;

                        //get file extension
                        String fileExtension = "";
                        int dotIndex = fileName.lastIndexOf(".");
                        if (dotIndex != -1) {
                            fileExtension = originalFileName.substring(dotIndex); // Includes the dot (.)
                            originalFileName = originalFileName.substring(0, dotIndex); // Remove extension
                        }

                        fileName = originalFileName + "_" + timestamp + fileExtension;

                        File tempFile = new File(System.getProperty("java.io.tmpdir"), fileName);
                        item.write(tempFile);

                        profilePictureUrl = S3Helper.uploadFile(tempFile, "profile_pictures/" + fileName, ACCESS_KEY, SECRET_KEY);
                        user.setProfilePicture(profilePictureUrl);
                    }
                }
            }

            //update fields on user object
            if (bio != null) {
                user.setBio(bio);
            }

            if (name != null) {
                user.setName(name);
            }
            if (profilePictureUrl != null) {
                user.setProfilePicture(profilePictureUrl);
            }

            //shoot that to the DB
            userDao.saveOrUpdate(user);
            log.info("Updated user: " + user);

            response.sendRedirect("viewProfile?userId=" + userId);
        } catch (Exception ex) {
            log.error("Error processing form: ", ex);
            response.sendRedirect("editProfile.jsp?error=uploadFailed");
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
            request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}

