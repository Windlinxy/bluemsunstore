package com.bluemsun.controller;

import com.bluemsun.entity.Cart;
import com.bluemsun.entity.Order;
import com.bluemsun.service.impl.CartManagerServiceImpl;
import com.bluemsun.util.JSONUtils;
import com.bluemsun.util.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        switch (uri) {
            case "/cart/add":
                doAddGoodInCart(request, response);
                break;
            case "/cart/show":
                doGetCart(request, response);
                break;
            case "/cart/clear":
                doClearCart(request, response);
                break;
            case "/cart/delete":
                doDelGoodInCart(request, response);
                break;
            case "/cart/settle":
                doSettleCart(request, response);
                break;
        }
    }


    /**
     * 用户添加购物车
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doAddGoodInCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CartManagerServiceImpl cartManagerService = new CartManagerServiceImpl();
        Map<String, Object> map = new HashMap<>();
        String jsonString = JSONUtils.getJson(request);
        Cart cart = null;
        cart = JSONUtils.fromJson(jsonString, Cart.class);
        //System.out.println("ifor:"+cart.getUserID()+""+cart.getGoodID()+""+cart.getAmount());
        int jud = cartManagerService.addGoodInCart(cart.getUserID(), cart.getGoodID(), cart.getAmount());
        if (jud==Status.OP_SUCCESS.getCode()) {
            map.put("msg", "添加购物车成功");
            map.put("status", Status.SUCCESS.getCode());
            System.out.println("添加购物车成功");
        } else {
            map.put("msg", "添加购物车失败");
            map.put("status", Status.FAILED.getCode());
        }
        JSONUtils.parseJson(map, response);
    }

    /**
     * 用户查看购物车
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doGetCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CartManagerServiceImpl cartManagerService = new CartManagerServiceImpl();
        Map<String, Object> map = new HashMap<>();
        String jsonString = JSONUtils.getJson(request);
        Cart cart = JSONUtils.fromJson(jsonString, Cart.class);
        System.out.println("id:" + cart.getUserID());
        cart = cartManagerService.getCart(cart.getUserID());
        System.out.println(cart.getList());
        if (cart.getList() != null) {
            map.put("msg", "获取购物车成功");
            map.put("status", Status.SUCCESS.getCode());
            map.put("cart", cart.getList());
            System.out.println("购物车成功");
        } else {
            map.put("msg", "购物车为空");
            map.put("status", Status.FAILED.getCode());
            System.out.println("购物车为空");
        }
        JSONUtils.parseJson(map, response);
    }

    /**
     * 用户清空购物车
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doClearCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CartManagerServiceImpl cartManagerService = new CartManagerServiceImpl();
        Map<String, Object> map = new HashMap<>();
        String jsonString = JSONUtils.getJson(request);
        Cart cart = null;
        cart = JSONUtils.fromJson(jsonString, Cart.class);
        int jud = cartManagerService.clearCart(cart.getUserID());
        if (jud==Status.OP_SUCCESS.getCode()) {
            map.put("msg", "清空购物车成功");
            map.put("status", Status.SUCCESS.getCode());
            System.out.println("清空购物车成功");
        } else {
            map.put("msg", "清空购物车失败");
            map.put("status", Status.FAILED.getCode());
        }
        JSONUtils.parseJson(map, response);
    }

    /**
     * 用户从购物车删除商品
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doDelGoodInCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CartManagerServiceImpl cartManagerService = new CartManagerServiceImpl();
        Map<String, Object> map = new HashMap<>();
        String jsonString = JSONUtils.getJson(request);
        Cart cart = null;
        cart = JSONUtils.fromJson(jsonString, Cart.class);
        System.out.println(cart.getUserID() + ":::" + cart.getGoodID());
        int jud = cartManagerService.deleteGoodInCart(cart.getUserID(), cart.getGoodID());
        if (jud==Status.OP_SUCCESS.getCode()) {
            map.put("msg", "删除商品成功");
            map.put("status", Status.SUCCESS.getCode());
            System.out.println("删除购物车成功");
        } else {
            map.put("msg", "删除商品失败");
            map.put("status", Status.FAILED.getCode());
        }
        JSONUtils.parseJson(map, response);
    }

    /**
     * 用户结算购物车
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doSettleCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CartManagerServiceImpl cartManagerService = new CartManagerServiceImpl();
        Map<String, Object> map = new HashMap<>();
        String jsonString = JSONUtils.getJson(request);
        Order order;
        order = JSONUtils.fromJson(jsonString, Order.class);
        System.out.println(order);
        order = cartManagerService.settleCart(order);
        if (order.getList() != null) {
            map.put("msg", "结算商品成功");
            map.put("status", Status.SUCCESS.getCode());
            map.put("order", order);
            System.out.println("结算购物车成功");
        } else {
            map.put("msg", "结算商品失败");
            map.put("status", Status.FAILED.getCode());
        }
        JSONUtils.parseJson(map, response);
    }

}
