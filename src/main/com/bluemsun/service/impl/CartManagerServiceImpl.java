package com.bluemsun.service.impl;

import com.bluemsun.dao.*;
import com.bluemsun.entity.Cart;
import com.bluemsun.entity.Good;
import com.bluemsun.entity.Order;
import com.bluemsun.service.CartManagerService;
import com.bluemsun.util.Status;

import java.util.ArrayList;
import java.util.List;

public class CartManagerServiceImpl implements CartManagerService {
    @Override
    public int addGoodInCart(int userID, int goodID, int count) {
        int jud = 0;
        CartDao cartDao = new CartDao();
        int oricount = cartDao.getGoodCount(userID, goodID);
        if (oricount == 0) {
            jud = cartDao.insertGoodInCart(userID, goodID, count);
        } else {
            jud = updateGoodInCart(userID, goodID, count + oricount);
        }
        return jud;
    }

    @Override
    public int deleteGoodInCart(int userID, int goodID) {
        int jud;
        CartDao cartDao = new CartDao();
        jud = cartDao.dropGoodInCart(userID, goodID);
        return jud;
    }

    @Override
    public int clearCart(int userID) {
        int jud = 0;
        CartDao cartDao = new CartDao();
        jud = cartDao.dropCart(userID);
        return jud;
    }

    @Override
    public int updateGoodInCart(int userID, int goodID, int count) {
        int jud = 0;
        CartDao cartDao = new CartDao();
        jud = cartDao.updateGoodInCart(userID, goodID, count);
        return jud;
    }

    @Override
    public Cart getCart(int userID) {
        CartDao cartDao = new CartDao();
        Cart cart = new Cart();
        cart.setList(cartDao.getGoodListByUserID(userID));
        return cart;
    }

    @Override
    public Order buyGood(Order order, int goodID, int count) {
        UserDao userDao = new UserDao();
        GoodDao goodDao = new GoodDao();
        OrderDao orderDao = new OrderDao();
        PageDao pageDao = new PageDao();
        NoticeDao noticeDao = new NoticeDao();
        Good good = pageDao.getGoodByID(goodID);
        //生成订单
        orderDao.insertOrder(order);
        order = orderDao.getOrderID(order);
        List<Good> list = new ArrayList<>();
        list.add(good);
        order.setList(list);
        //将商品放入订单
        orderDao.insertGoodInOrder(order.getOrderID(), goodID, count);
        //变更商品人气与库存
        goodDao.addGoodBuyerNumber(goodID, count);
        //变更用户钱包
        userDao.updateBalance(order.getUserID(), order.getMoney());
        //生成通知（买家
        noticeDao.insertNotice(order.getOrderID(), order.getUserID(), 1);
        //生成通知（商家
        noticeDao.insertNotice(order.getOrderID(), order.getUserID(), 2);
        return order;
    }

    @Override
    public Order settleCart(Order order) {
        UserDao userDao = new UserDao();
        GoodDao goodDao = new GoodDao();
        OrderDao orderDao = new OrderDao();
        CartDao cartDao = new CartDao();
        NoticeDao noticeDao = new NoticeDao();
        //生成订单
        orderDao.insertOrder(order);
        order = orderDao.getOrderID(order);
        List<Good> list = getCart(order.getUserID()).getList();
        order.setList(list);
        System.out.println(order.getCreateDate());
        for (Good good : list) {
            //将商品加入订单
            if (orderDao.insertGoodInOrder(order.getOrderID(), good.getGoodID(), good.getAmount())== Status.OP_SUCCESS.getCode()) {
                System.out.println(good.getGoodID() + " " + good.getAmount());
                //变更商品库存与人气
                goodDao.addGoodBuyerNumber(good.getGoodID(), good.getAmount());
            }
        }
        //变更购物车状态
        cartDao.updateCartStatus(order.getUserID());
        //变更用户钱包
        userDao.updateBalance(order.getUserID(), order.getMoney());
        //生成通知（买家
        noticeDao.insertNotice(order.getOrderID(), order.getUserID(), 1);
        //生成通知（商家
        noticeDao.insertNotice(order.getOrderID(), order.getUserID(), 2);
        return order;
    }
}
