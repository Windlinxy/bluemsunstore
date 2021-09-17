package com.bluemsun.service;

import com.bluemsun.entity.Good;
import com.bluemsun.entity.Notice;
import com.bluemsun.entity.Order;
import com.bluemsun.entity.Page;


public interface PageManagerService {
    /**
     * 获取搜索商品页面
     *
     * @param curPage  当前页数
     * @param PageSize 单页面显示商品数
     * @param keyword  关键字
     * @return 商品页面
     */
    Page<Good> getGood(int curPage, int PageSize, String keyword);

    /**
     * 获取用户订单页面（重载
     *
     * @param curPage  当前页数
     * @param pageSize 单页面显示商品数
     * @param userID   用户id
     * @return 商品页面
     */
    Page<Order> getOrder(int curPage, int pageSize, int userID);

    /**
     * 获取商家订单页面（重载
     *
     * @param curPage  当前页数
     * @param PageSize 单页面显示商品数
     * @return 商品页面
     */
    Page<Order> getOrder(int curPage, int PageSize);

    /**
     * 获取用户通知页面
     *
     * @param curPage  当前页数
     * @param PageSize 单页面显示商品数
     * @param userID   通知页面
     * @return
     */
    Page<Notice> getNotice(int curPage, int PageSize, int userID);

    /**
     * 获取商家通知页面（重载
     *
     * @param curPage  当前页数
     * @param PageSize 单页面显示商品数
     * @return 通知页面
     */
    public Page<Notice> getNotice(int curPage, int PageSize);
}
