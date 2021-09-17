package com.bluemsun.controller;

import com.bluemsun.entity.Notice;
import com.bluemsun.entity.Order;
import com.bluemsun.entity.Page;
import com.bluemsun.entity.User;
import com.bluemsun.service.impl.PageManagerServiceImpl;
import com.bluemsun.service.impl.UserManagerServiceImpl;
import com.bluemsun.util.JSONUtils;
import com.bluemsun.util.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        switch (uri) {
            case "/order/regulator":
                doGetAllOrder(request, response);
                break;
            case "/order/user":
                doGetOrder(request, response);
                break;
            case "/order/comment":
                doCommentOrder(request, response);
                break;
            case "/order/comment/delete":
                doDeleteComment(request, response);
                break;
            case "/confirm/regulator":
                doConfirmSend(request, response);
                break;
            case "/confirm/user":
                doConfirmReceive(request, response);
                break;
        }
    }

    /**
     * 用户页面订单查看
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doGetOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String josnString = JSONUtils.getJson(request);
        //System.out.println(josnString);
        Map<String, Object> map = new HashMap<>();
        PageManagerServiceImpl pageManagerService = new PageManagerServiceImpl();
        Page<Order> page = JSONUtils.fromJson(josnString, Page.class);
        User user = JSONUtils.fromJson(josnString, User.class);
        int curPage = page.getCurrentPage();
        int pageSize = page.getPageSize();
        int userID = user.getUserID();
        if(curPage<1||pageSize<1&&userID==0){
            map.put("status", Status.FAILED.getCode());
            map.put("msg",Status.FAILED.getMessage());
        }else{
            Page<Order> page1 = pageManagerService.getOrder(curPage, pageSize, userID);
            map.put("status", Status.SUCCESS.getCode());
            map.put("msg",Status.SUCCESS.getMessage());
            map.put("page", page1);
        }
        JSONUtils.parseJson(map, response);
    }

    /**
     * 商家页面订单查看
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doGetAllOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String josnString = JSONUtils.getJson(request);
        Map<String, Object> map = new HashMap<>();
        PageManagerServiceImpl pageManagerService = new PageManagerServiceImpl();
        Page<Order> page = JSONUtils.fromJson(josnString, Page.class);
        int currentPage = page.getCurrentPage();
        int pageSize = page.getPageSize();
        if(currentPage<1||pageSize<1){
            map.put("msg", Status.FAILED.getMessage());
            map.put("status", Status.FAILED.getCode());
        }else {
            Page<Order> page1 = pageManagerService.getOrder(currentPage, pageSize);
            map.put("msg", Status.SUCCESS.getMessage());
            map.put("status", Status.SUCCESS.getCode());
            map.put("page", page1);
        }
        JSONUtils.parseJson(map, response);
    }

    /**
     * 用户评价订单
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doCommentOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String josnStrig = JSONUtils.getJson(request);
        Map<String, Object> map = new HashMap<>();
        UserManagerServiceImpl userManagerService = new UserManagerServiceImpl();
        Order order = JSONUtils.fromJson(josnStrig, Order.class);
        String comment = order.getComment();
        int orderID = order.getOrderID();
        userManagerService.addComment(orderID, comment);
        map.put("msg", "successfully");
        map.put("status", Status.SUCCESS.getCode());
        JSONUtils.parseJson(map, response);
    }

    /**
     * 用户删除评价
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doDeleteComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String josnStrig = JSONUtils.getJson(request);
        Map<String, Object> map = new HashMap<>();
        UserManagerServiceImpl userManagerService = new UserManagerServiceImpl();
        Order order = JSONUtils.fromJson(josnStrig, Order.class);
        int orderID = order.getOrderID();
        System.out.println(josnStrig + orderID);
        userManagerService.deleteComment(orderID);
        map.put("msg", "successfully");
        map.put("status", Status.SUCCESS.getCode());
        JSONUtils.parseJson(map, response);
    }

    /**
     * 商家确认发货
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doConfirmSend(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String josnString = JSONUtils.getJson(request);
        Map<String, Object> map = new HashMap<>();
        UserManagerServiceImpl userManagerService = new UserManagerServiceImpl();
        Notice notice = JSONUtils.fromJson(josnString, Notice.class);
        userManagerService.confirm(notice.getOrderID(), notice.getUserID(), 3);
        map.put("msg", "successfully");
        map.put("status", Status.SUCCESS.getCode());
        JSONUtils.parseJson(map, response);
    }

    /**
     * 用户确认收货
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doConfirmReceive(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String josnString = JSONUtils.getJson(request);
        Map<String, Object> map = new HashMap<>();
        UserManagerServiceImpl userManagerService = new UserManagerServiceImpl();
        Notice notice = JSONUtils.fromJson(josnString, Notice.class);
        userManagerService.confirm(notice.getOrderID(), notice.getUserID(), 4);
        map.put("msg", "successfully");
        map.put("status", Status.SUCCESS.getCode());
        JSONUtils.parseJson(map, response);
    }

}
