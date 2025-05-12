package controller;

import entity.Image;
import entity.Project;
import entity.ProjectEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.GenericDao;
import util.S3Helper;
import util.ServletHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * A servlet to delete an image
 * @author pete
 */
@WebServlet("/deleteImage")
public class DeleteImage extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DeleteImage.class);
    private static GenericDao<Project> projectDao = new GenericDao<>(Project.class);
    private static GenericDao<Image> imageDao = new GenericDao<>(Image.class);
    private static Image image = new Image();

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

        int projectId = ServletHelper.getIntParam(request,response,"projectId");
        Project project = projectDao.getById(projectId);

        if (ServletHelper.isLoggedInUserProjectOwner(request, response , project.getProjectId()) ) {
            String imageIdParam = ServletHelper.getStringParam(request,response,"imageId");
            image = imageDao.getById(Integer.parseInt(imageIdParam));
            imageDao.delete(image);
            response.sendRedirect("viewProject?projectId=" + projectId);

            //also remove from s3 bucket
            String s3Key = S3Helper.extractS3KeyFromUrl(image.getImageUrl());
            S3Helper.deleteFile(s3Key, ACCESS_KEY, SECRET_KEY);
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
        int imageIdParam = ServletHelper.getIntParam(request,response,"id");
        image = imageDao.getById(imageIdParam);
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        Project project = projectDao.getById(projectId);
        request.setAttribute("image", image);
        request.setAttribute("project", project);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteImage.jsp");
        dispatcher.forward(request, response);
    }
}
