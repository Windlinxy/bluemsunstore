package com.bluemsun.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter implements Filter {

    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (!uri.contains("images")) {
            response.setContentType("text/html;charset=utf-8");
        }
        response.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }
}
