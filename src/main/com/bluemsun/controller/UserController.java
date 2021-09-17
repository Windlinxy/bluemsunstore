package com.bluemsun.controller;

import com.bluemsun.entity.*;
import com.bluemsun.service.impl.CartManagerServiceImpl;
import com.bluemsun.service.impl.UserManagerServiceImpl;
import com.bluemsun.util.JSONUtils;
import com.bluemsun.util.Status;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("请求uri：" + request.getRequestURI());
        String uri = request.getRequestURI();
        switch (uri) {
            case "/user/out":
                doLoginOut(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("请求uri：" + request.getRequestURI());
        String uri = request.getRequestURI();
        switch (uri) {
            case "/user/login":
                doLogin(request, response);
                break;
            case "/user/register":
                doRegister(request, response);
                break;
            case "/user/recharge":
                doRecharge(request, response);
                break;
            case "/user/buy":
                doBuyGood(request, response);
                break;

        }


    }

    /**
     * 用户注册
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User();
        UserManagerServiceImpl userManagerService = new UserManagerServiceImpl();
        String jsonString = JSONUtils.getJson(request);
        user = JSONUtils.fromJson(jsonString, User.class);
        Map<String, Object> map = new HashMap<>();

        if (userManagerService.insertUser(user) != null) {
            map.put("msg", "Register successfully!");
            map.put("status", Status.SUCCESS.getCode());
            map.put("phoneNumber", user.getPhoneNumber());
            System.out.println("注册成功");
        } else {
            map.put("msg", "Register Failed!");
            map.put("status", Status.FAILED.getCode());
            System.out.println("注册失败请检查注册信息");
        }
        // 使用JSON将返回数据返回给前端
        JSONUtils.parseJson(map, response);
    }

    /**
     * 用户登录
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(工具类)
     */
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManagerServiceImpl userManagerService = new UserManagerServiceImpl();
        HttpSession session;
        Map<String, Object> map = new HashMap<>();
        // 获取处理好的json字符串
        String jsonString = JSONUtils.getJson(request);
        // 利用GSON进行JSON字符串的反序列化
        User user = JSONUtils.fromJson(jsonString, User.class);

        if ((user = userManagerService.isUser(user)).getUserID() != 0) {
            session = request.getSession();
            System.out.println("登录id:"+session.getId());
            session.setAttribute(session.getId(), user);
            map.put("msg", "Login successfully!成功");
            map.put("status", Status.SUCCESS.getCode());
            user.setPassword(null);
            map.put("data", user);
            System.out.println("登录成功");
        } else {
            map.put("msg", "Login failed!");
            map.put("status", Status.FAILED.getCode());
            System.out.println("登录失败");
        }
        // 使用JSON将返回数据返回给前端
        JSONUtils.parseJson(map, response);
    }

    /**
     * 用户充值
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doRecharge(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManagerServiceImpl userManagerService = new UserManagerServiceImpl();
        Map<String, Object> map = new HashMap<>();
        String jsonString = JSONUtils.getJson(request);
        User user = JSONUtils.fromJson(jsonString, User.class);
        if (userManagerService.rechargeBalance(user.getPhoneNumber(), user.getBalance()) != null) {
            map.put("msg", "Recharge successfully!");
            map.put("status", Status.SUCCESS.getCode());
            map.put("phoneNumber", user.getPhoneNumber());
            map.put("money", user.getBalance());
            System.out.println("充值成功");
        } else {
            map.put("msg", "Recharge failed!");
            map.put("status", Status.FAILED.getCode());
            System.out.println("充值失败");
        }
        JSONUtils.parseJson(map, response);
    }

    /**
     * 用户购买商品（单独）
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doBuyGood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CartManagerServiceImpl cartManagerService = new CartManagerServiceImpl();
        Map<String, Object> map = new HashMap<>();
        String jsonString = JSONUtils.getJson(request);
        Order order;
        order = JSONUtils.fromJson(jsonString, Order.class);
        Good good = JSONUtils.fromJson(jsonString, Good.class);
        //System.out.println(order+""+good);
        order = cartManagerService.buyGood(order, good.getGoodID(), good.getAmount());
        map.put("msg", "购买成功");
        map.put("status", Status.SUCCESS.getCode());
        map.put("order", order);
        System.out.println("购买成功");
        JSONUtils.parseJson(map, response);
    }

    /**
     * 用户退出登录(get)
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doLoginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        session.removeAttribute(session.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("status", Status.SUCCESS.getCode());
        map.put("msg", "退出登录成功");
        JSONUtils.parseJson(map, response);
    }

}
