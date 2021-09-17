package com.bluemsun.service;

import com.bluemsun.entity.Cart;
import com.bluemsun.entity.Order;

public interface CartManagerService {
    /**
     * 添加商品至购物车
     *
     * @param userID 用户id
     * @param goodID 商品id
     * @param count  商品数量
     * @return 执行顺利与否
     */
    int addGoodInCart(int userID, int goodID, int count);

    /**
     * 删除购物车中商品
     *
     * @param userID 用户id
     * @param goodID 商品id
     * @return 执行顺利与否
     */
    int deleteGoodInCart(int userID, int goodID);

    /**
     * 清空购物车
     *
     * @param userID 用户id
     * @return 执行顺利与否
     */
    int clearCart(int userID);

    /**
     * 更新购物车
     *
     * @param userID 用户id
     * @param goodID 商品id
     * @param count  商品数量
     * @return 执行顺利与否
     */
    int updateGoodInCart(int userID, int goodID, int count);

    /**
     * 获取购物车
     *
     * @param userID 用户id
     * @return 执行顺利与否
     */
    Cart getCart(int userID);

    /**
     * 单独购物
     *
     * @param order  订单
     * @param goodID 商品id
     * @param count  商品数量
     * @return 执行顺利与否
     */
    Order buyGood(Order order, int goodID, int count);

    /**
     * 结算购物车
     *
     * @param order 订单
     * @return 执行顺利与否
     */
    Order settleCart(Order order);
}
