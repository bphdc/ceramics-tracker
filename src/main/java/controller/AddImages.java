package controller;

import entity.Image;
import entity.Project;
import entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;
import util.S3Helper;
import util.ServletHelper;

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
 * A servlet to handle image uploads for a project.
 * Uploads images to AWS S3 and saves metadata in the database.
 *
 * @author pete
 */
@WebServlet("/addImages")
public class AddImages extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(AddImages.class);

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
        ServletContext context = getServletContext();
        String ACCESS_KEY = (String) context.getAttribute("ACCESS_KEY");
        String SECRET_KEY = (String) context.getAttribute("SECRET_KEY");
        GenericDao<Project> projectDao = new GenericDao<>(Project.class);
        GenericDao<Image> imageDao = new GenericDao<>(Image.class);
        User user = ServletHelper.getLoggedInUser(request, response);

        //because the multiform thing is weird



        if (!ServletFileUpload.isMultipartContent(request)) {
            log.error("Form must have enctype=multipart/form-data.");
            response.sendRedirect("editProject.jsp?error=invalidForm");
            return;
        }

        try {
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> formItems = upload.parseRequest(request);
            Project project = new Project();
            String projectIdParam = null;
            int projectId = 0;

            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    if ("projectId".equals(item.getFieldName())) {
                        projectIdParam = item.getString();
                        log.info("received prj id: {}", projectIdParam);
                        project = projectDao.getById(Integer.parseInt(projectIdParam));
                        projectId = project.getProjectId();
                    }
                }
                else {
                    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                    String fileName = new File(item.getName()).getName();
                    String originalFileName = fileName;

                    String fileExtension = "";
                    int dotIndex = fileName.lastIndexOf(".");
                    if (dotIndex != -1) {
                        fileExtension = originalFileName.substring(dotIndex);
                        originalFileName = originalFileName.substring(0, dotIndex);
                    }

                    fileName = originalFileName + "_" + timestamp + fileExtension;

                    File tempFile = new File(System.getProperty("java.io.tmpdir"), fileName);
                    item.write(tempFile);

                    String imageUrl = S3Helper.uploadFile(tempFile, "project_images/" + fileName, ACCESS_KEY, SECRET_KEY);

                    Image image = new Image();
                    image.setProject(project);
                    image.setImageUrl(imageUrl);
                    imageDao.saveOrUpdate(image);
                }


            }

            response.sendRedirect("editProject?projectId=" + projectId);
        } catch (Exception e) {
            log.error("error processing image upload: ", e);
            response.sendRedirect("editProject.jsp?error=uploadFailed");
        }
    }
}
