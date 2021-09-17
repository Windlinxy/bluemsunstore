package com.bluemsun.controller;

import com.bluemsun.entity.Notice;
import com.bluemsun.entity.Page;
import com.bluemsun.entity.User;
import com.bluemsun.service.impl.PageManagerServiceImpl;
import com.bluemsun.util.JSONUtils;
import com.bluemsun.util.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NoticeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        switch (uri) {
            case "/notice/user":
                doGetNoticeByUser(request, response);
                break;
            case "/notice/regulator":
                doGetNoticeByMan(request, response);
                break;
        }
    }

    /**
     * 用户通知查看
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doGetNoticeByUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String josnString = JSONUtils.getJson(request);
        //System.out.println(josnString);
        Map<String, Object> map = new HashMap<>();
        PageManagerServiceImpl pageManagerService = new PageManagerServiceImpl();
        Page<Notice> page = JSONUtils.fromJson(josnString, Page.class);
        User user = JSONUtils.fromJson(josnString, User.class);
        int currentPage = page.getCurrentPage();
        int pageSize = page.getPageSize();
        if (currentPage < 1 && pageSize < 1) {
            map.put("status", Status.FAILED.getCode());
            map.put("msg", Status.FAILED.getMessage());
        } else {
            Page<Notice> page1 = pageManagerService.getNotice(currentPage, pageSize, user.getUserID());
            map.put("msg", "successfully");
            map.put("status", Status.SUCCESS.getCode());
            map.put("page", page1);
        }
        JSONUtils.parseJson(map, response);
    }

    /**
     * 商家通知查看
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doGetNoticeByMan(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String josnString = JSONUtils.getJson(request);
        Map<String, Object> map = new HashMap<>();
        PageManagerServiceImpl pageManagerService = new PageManagerServiceImpl();
        Page<Notice> page = JSONUtils.fromJson(josnString, Page.class);
        int currentPage = page.getCurrentPage();
        int pageSize = page.getPageSize();
        if (currentPage < 1 || pageSize < 1) {
            map.put("msg", Status.FAILED.getMessage());
            map.put("status", Status.FAILED.getCode());
        } else {
            Page<Notice> page1 = pageManagerService.getNotice(currentPage, pageSize);
            map.put("msg", "successfully");
            map.put("status", Status.SUCCESS.getCode());
            map.put("page", page1);
        }
        JSONUtils.parseJson(map, response);
    }
}
