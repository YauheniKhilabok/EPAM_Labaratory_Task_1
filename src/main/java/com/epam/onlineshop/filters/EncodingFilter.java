package com.epam.onlineshop.filters;

import com.epam.onlineshop.constants.WebConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * The EncodingFilter filter is responsible encoding unit for request and response.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
@WebFilter(urlPatterns = {WebConstants.ENCODING_PATTERN},
        initParams = {@WebInitParam(name = WebConstants.ENCODING_NAME, value = WebConstants.ENCODING,
                description = WebConstants.ENCODING_DESCRIPTION)})
public class EncodingFilter implements Filter {
    private String code;

    public void init(FilterConfig fConfig) throws ServletException {
        code = fConfig.getInitParameter(WebConstants.ENCODING_NAME);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
        code = null;
    }
}
