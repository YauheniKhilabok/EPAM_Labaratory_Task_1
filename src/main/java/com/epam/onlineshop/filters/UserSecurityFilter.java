package com.epam.onlineshop.filters;

import com.epam.onlineshop.constants.PathConstants;
import com.epam.onlineshop.constants.WebConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The UserSecurityFilter filter is responsible for the control
 * and restriction of access to certain pages for the user.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
@WebFilter(urlPatterns = {WebConstants.USER_SECURITY_PATTERN},
        initParams = {@WebInitParam(name = WebConstants.NAME_WEB_INIT_PARAM, value = PathConstants.ERROR_PAGE)})
public class UserSecurityFilter implements Filter {
    /**
     * Property - the path to the page on which the user will be redirected to unauthorized access
     */
    private String indexPath;

    public void init(FilterConfig fConfig) throws ServletException {
        indexPath = fConfig.getInitParameter(WebConstants.NAME_WEB_INIT_PARAM);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(true);
        if (session.getAttribute(WebConstants.ROLE_ATTRIBUTE) == null) {
            session.setAttribute(WebConstants.ROLE_ATTRIBUTE, WebConstants.GUEST_ROLE_VALUE);
        }
        String role = (String) session.getAttribute(WebConstants.ROLE_ATTRIBUTE);
        if (role.equals(WebConstants.USER_ROLE_VALUE)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
