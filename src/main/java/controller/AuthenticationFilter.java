package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;
import util.ServletHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter when not logged in
 */
@WebFilter("/*") //
public class AuthenticationFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    /**
     * Filter when not logged in
     * @param request the req
     * @param response the res
     * @param chain the chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getRequestURI();

        // skip auth check for public resources
        if (path.endsWith("/") || path.contains("/index") || path.contains("/logIn") || path.contains("/css/") || path.contains("/images/") || path.contains("auth")) {
            chain.doFilter(request, response); // allow through
            return;
        }

        Boolean isLoggedIn = false;
        isLoggedIn = ServletHelper.isLoggedIn(req, resp);
        log.info("isLoggedIn from filter: {}", isLoggedIn);

        if (isLoggedIn) {
            chain.doFilter(request, response); // allow through
        } else {
            ServletHelper.sendToErrorPageWithMessage(req, resp, "Log in to continue");
        }
    }

    /**
     * Init method
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {}

    /**
     * destroy method
     */
    @Override
    public void destroy() {}
}
