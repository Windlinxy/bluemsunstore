package com.bluemsun.service;


import com.bluemsun.entity.User;

import java.math.BigDecimal;

public interface UserManagerService {
    /**
     * 判断用户是否存在
     *
     * @param user 用户
     * @return 用户
     */
    User isUser(User user);

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 用户
     */
    User insertUser(User user);

    /**
     * 钱包充值
     *
     * @param phoneNumber 手机号
     * @param money       充值金额
     * @return 充值金额
     */
    BigDecimal rechargeBalance(String phoneNumber, BigDecimal money);

    /**
     * 查询用户
     *
     * @param phoneNumber 手机号
     * @return 用户
     */
    User queryUser(String phoneNumber);

    /**
     * 变更订单状态
     *
     * @param orderID 订单id
     * @param userID  用户id
     * @param sta     状态码
     */
    void confirm(int orderID, int userID, int sta);

    /**
     * 添加评价
     *
     * @param orderID 订单id
     * @param comment 评价内容
     */
    void addComment(int orderID, String comment);

    /**
     * 删除评价
     *
     * @param orderID 用户id
     */
    void deleteComment(int orderID);
}
