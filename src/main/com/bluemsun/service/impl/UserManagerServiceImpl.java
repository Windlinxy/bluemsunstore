package com.bluemsun.service.impl;

import com.bluemsun.dao.NoticeDao;
import com.bluemsun.dao.OrderDao;
import com.bluemsun.dao.UserDao;
import com.bluemsun.entity.User;
import com.bluemsun.service.UserManagerService;
import com.bluemsun.util.Status;

import java.math.BigDecimal;

public class UserManagerServiceImpl implements UserManagerService {
    @Override
    public User isUser(User user) {
        UserDao userDao = new UserDao();
        User user1;
        user1 = userDao.userLogin(user);
        return user1;
    }

    @Override
    public User insertUser(User user) {
        UserDao userDao = new UserDao();
        String regex = "1\\d{10}";
        User user1 = null;
        if (user.getPhoneNumber().matches(regex)) {
            user1 = userDao.userRegister(user);
        }
        return user1;
    }

    @Override
    public BigDecimal rechargeBalance(String phoneNumber, BigDecimal money) {

        UserDao userDao = new UserDao();
        if (userDao.queryUser(phoneNumber) != null) {
            userDao.updateBalance(phoneNumber, money);
        } else {
            money = null;
        }
        return money;
    }

    @Override
    public User queryUser(String phoneNumber) {
        UserDao userDao = new UserDao();
        User user = null;
        user = userDao.queryUser(phoneNumber);
        return user;
    }

    @Override
    public void confirm(int orderID, int userID, int sta) {
        NoticeDao noticeDao = new NoticeDao();
        int jud;
        jud = noticeDao.insertNotice(orderID, userID, sta);
        if (jud ==Status.OP_SUCCESS.getCode()) {
            if (sta == 4) {
                OrderDao orderDao = new OrderDao();
                orderDao.updateOrderSta(orderID, 0);
            } else {
                OrderDao orderDao = new OrderDao();
                orderDao.updateOrderSta(orderID, 2);
            }
        }
    }

    @Override
    public void addComment(int orderID, String comment) {
        OrderDao orderDao = new OrderDao();
        orderDao.addOrderComment(orderID, comment);
    }

    @Override
    public void deleteComment(int orderID) {
        OrderDao orderDao = new OrderDao();
        orderDao.dropComment(orderID);
    }
}
