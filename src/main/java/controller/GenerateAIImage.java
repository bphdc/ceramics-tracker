package controller;

import com.hiveai.Response;
import entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.HiveAIClient;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * A servlet to view projects
 * @author pete
 */

@WebServlet("/generateAIImage")
public class GenerateAIImage extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HiveAIClient hiveClient = new HiveAIClient();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        String API_KEY = (String) context.getAttribute("HIVE_API_KEY");
        String prompt = request.getParameter("prompt");

        try {
            Response hiveResponse = hiveClient.generateImage(prompt, API_KEY);
            String imageUrl = hiveResponse.getOutput().get(0).getUrl();

            request.setAttribute("imageUrl", imageUrl);
            request.setAttribute("prompt", prompt);

        } catch (Exception e) {
            logger.error("Image generation failed", e);
            request.setAttribute("errorMessage", "Could not generate image. Reach out to site administrator if problem persists");
            request.setAttribute("prompt", prompt);
        }

        request.getRequestDispatcher("/aiImage.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/aiImage.jsp").forward(request, response);
    }
}

