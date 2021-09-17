package com.bluemsun.service.impl;


import com.bluemsun.dao.PageDao;
import com.bluemsun.entity.Good;
import com.bluemsun.entity.Notice;
import com.bluemsun.entity.Order;
import com.bluemsun.entity.Page;
import com.bluemsun.service.PageManagerService;
import com.bluemsun.util.Kind;



public class PageManagerServiceImpl implements PageManagerService {
    @Override
    public Page<Good> getGood(int curPage, int pageSize, String keyword) {
        PageDao pageDao = new PageDao();
        for (Kind kind : Kind.values()) {
            if (kind.getValue().equals(keyword)) {
                int totalResult = pageDao.getResultCountByKind(keyword);
                Page<Good> page = new Page<>(curPage, pageSize, totalResult);
                page.setList(pageDao.getGoodByKind(page.getStartIndex(), pageSize, keyword));
                return page;
            }
        }
        int totalResult = pageDao.getResultCountByKeyword(keyword);
        Page<Good> page = new Page<>(curPage, pageSize, totalResult);
        page.setList(pageDao.getGoodByKeyword(page.getStartIndex(), pageSize, keyword));
        return page;
    }

    @Override
    public Page<Order> getOrder(int curPage, int PageSize, int userID) {
        PageDao pageDao = new PageDao();
        int totalResult = pageDao.getOrderCountByUserID(userID);
        Page<Order> page = new Page<>(curPage, PageSize, totalResult);
        page.setList(pageDao.getOrderByUserID(page.getStartIndex(), PageSize, userID));
        return page;
    }

    @Override
    public Page<Order> getOrder(int curPage, int pageSize) {
        PageDao pageDao = new PageDao();
        int totalResult = pageDao.getAllOrderCount();
        Page<Order> page = new Page<>(curPage, pageSize, totalResult);
        page.setList(pageDao.getAllOrder(page.getStartIndex(), pageSize));
        return page;
    }

    @Override
    public Page<Notice> getNotice(int curPage, int pageSize, int userID) {
        PageDao pageDao = new PageDao();
        int totalResult = pageDao.getNoticeCountByUserID(userID);
        Page<Notice> page = new Page<>(curPage, pageSize, totalResult);
        page.setList(pageDao.getNotice(page.getStartIndex(), pageSize, userID));
        return page;
    }

    @Override
    public Page<Notice> getNotice(int curPage, int pageSize) {
        PageDao pageDao = new PageDao();
        int totalResult = pageDao.getNoticeCount();
        Page<Notice> page = new Page<>(curPage, pageSize, totalResult);
        page.setList(pageDao.getNotice(page.getStartIndex(), pageSize));
        return page;
    }
}
