package com.bluemsun.filter;

import com.bluemsun.entity.User;
import com.bluemsun.util.JSONUtils;
import com.bluemsun.util.Status;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CheckLoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();

        //放行请求
        if (uri.contains("register") || uri.contains("images") || uri.contains("login") || uri.equals("/good/search")) {
            chain.doFilter(request, response);
        }else {
            HttpSession session = request.getSession();
            System.out.println("SESSION_ID: " + request.getSession().getId());
            User user = new User();
            //判断用户登录
            if (session.getAttribute(session.getId()) != null) {
                user = (User) session.getAttribute(session.getId());
                //商家与用户权限限制
                if (user.getIdentify() == 1) {
                    if (uri.contains("good") || uri.contains("regulator")||uri.contains("out")) {
                        System.out.println("允许商家");
                        chain.doFilter(request, response);
                    } else {
                        System.out.println("禁止商家");
                        returnCode(Status.ERROR_NO_AUTHORITY, response);
                    }
                } else if (user.getIdentify() == 0) {
                    if (uri.contains("user") || uri.contains("cart") || uri.contains("comment")) {
                        System.out.println("允许用户");
                        chain.doFilter(request, response);
                    } else {
                        System.out.println("禁止用户");
                        returnCode(Status.ERROR_NO_AUTHORITY, response);
                    }
                }
            } else {
                returnCode(Status.ERROR_NO_LOGIN, response);
            }
        }
    }

    private void returnCode(Status status, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.getCode());
        map.put("msg", status.getMessage());
        JSONUtils.parseJson(map, response);
    }
}
