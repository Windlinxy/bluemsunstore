package com.bluemsun.dao;

import com.bluemsun.entity.Notice;
import com.bluemsun.entity.Order;
import com.bluemsun.entity.Page;
import com.bluemsun.util.C3P0Utils;
import com.bluemsun.entity.Good;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PageDao {
    /**
     * 通过关键字获得商品数
     *
     * @param keyword 关键字
     * @return count 商品数
     */
    public int getResultCountByKeyword(String keyword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql;
        int count = 0;
        if (keyword != null) {
            sql = "select count(*) from tb_good where  locate (?,good_name)>0 ";
        } else {
            sql = "select count(*) from tb_good ";
        }
        try {
            connection = C3P0Utils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (keyword != null) {
                preparedStatement.setString(1, keyword);
            }
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            count = resultSet.getInt(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return count;
    }

    /**
     * 通过关键字获得商品列表
     *
     * @param startIndex 开始下标
     * @param pageSize   单页面显示商品数
     * @param keyword    关键字
     * @return 商品列表
     */
    public List<Good> getGoodByKeyword(int startIndex, int pageSize, String keyword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Good> goodList = new ArrayList<>();
        String sql;
        if (keyword != null) {
            sql = "select * from tb_good where locate (?, good_name)>0 order by buyer_number desc limit ?,?;";//locate模糊搜索
        } else {
            sql = "select * from tb_good order by buyer_number desc limit ?,?;";
        }
        try {
            connection = C3P0Utils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (keyword != null) {
                preparedStatement.setString(1, keyword);
                preparedStatement.setInt(2, startIndex);
                preparedStatement.setInt(3, pageSize);
            } else {
                preparedStatement.setInt(1, startIndex);
                preparedStatement.setInt(2, pageSize);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                goodList.add(fromResultSetToGood(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return goodList;
    }

    /**
     * 通过种类获得商品数
     *
     * @param keyword 关键字
     * @return 商品数
     */
    public int getResultCountByKind(String keyword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql;
        int count = 0;
        if (keyword != null) {
            sql = "select count(*) from tb_good  where  locate (?,kind)>0; ";
        } else {
            sql = "select count(*) from tb_good;";
        }
        try {
            connection = C3P0Utils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (keyword != null) {
                preparedStatement.setString(1, keyword);
            }
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            count = resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return count;
    }

    /**
     * 通过种类获得商品列表
     *
     * @param startIndex 开始下标
     * @param pageSize   单页面显示商品数
     * @param kind       种类
     * @return 商品列表
     */
    public List<Good> getGoodByKind(int startIndex, int pageSize, String kind) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Good> goodList = new ArrayList<>();
        String sql;
        if (kind != null) {
            sql = "select * from tb_good  where  locate(?, kind)>0 order by buyer_number desc limit ?,?;";//locate模糊搜索
        } else {
            sql = "select * from tb_good order by buyer_number desc limit ?,?;";
        }
        try {
            connection = C3P0Utils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (kind != null) {
                preparedStatement.setString(1, kind);
                preparedStatement.setInt(2, startIndex);
                preparedStatement.setInt(3, pageSize);
            } else {
                preparedStatement.setInt(1, startIndex);
                preparedStatement.setInt(2, pageSize);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                goodList.add(fromResultSetToGood(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }

        return goodList;
    }

    /**
     * 根据商品id获取到商品信息
     *
     * @param goodID 商品id
     * @return 商品
     */
    public Good getGoodByID(int goodID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Good good = null;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select * from tb_good where  good_id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                PageDao pageDao = new PageDao();
                good = pageDao.fromResultSetToGood(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return good;
    }

    /**
     * 结果集提取出商品信息
     *
     * @param resultSet 查询结果集
     * @return 商品列表
     * @throws SQLException sql异常
     */
    public Good fromResultSetToGood(ResultSet resultSet) throws SQLException {
        Good good = new Good();
        good.setGoodID(resultSet.getInt("good_id"));
        good.setName(resultSet.getString("good_name"));
        good.setPrice(resultSet.getBigDecimal("price"));
        good.setDescription(resultSet.getString("description"));
        good.setAmount(resultSet.getInt("amount"));
        good.setKind(resultSet.getString("kind"));
        good.setBuyerNumber(resultSet.getInt("buyer_number"));
        good.setImageUrl(resultSet.getString("img_url"));
        return good;
    }

    /**
     * 结果集提取订单信息
     *
     * @param resultSet 结果集
     * @return 商品
     * @throws SQLException sql异常
     */
    public Order fromResultSetToOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrderID(resultSet.getInt("order_id"));
        order.setSta(resultSet.getInt("sta"));
        order.setCreateDate(resultSet.getDate("create_time"));
        order.setMoney(resultSet.getBigDecimal("money"));
        order.setAddress(resultSet.getString("address"));
        order.setPhoneNumber(resultSet.getString("phone_number"));
        order.setUserID(resultSet.getInt("user_id"));
        order.setUsername(resultSet.getString("user_name"));
        order.setComment(resultSet.getString("user_comment"));
        return order;
    }

    /**
     * 结果集提取出通知信息
     *
     * @param resultSet 结果集
     * @return 通知
     * @throws SQLException sql异常
     */
    public Notice formResultSetToNotice(ResultSet resultSet) throws SQLException {
        Notice notice = new Notice();
        notice.setNoticeTime(resultSet.getDate("notice_time"));
        notice.setUserID(resultSet.getInt("user_id"));
        notice.setOrderID(resultSet.getInt("order_id"));
        notice.setSta(resultSet.getInt("sta"));
        return notice;
    }

    /**
     * 根据用户id获取订单数
     *
     * @param userID 用户id
     * @return 订单数
     */
    public int getOrderCountByUserID(int userID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select count(*) from tb_order where  user_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            count = resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return count;
    }

    /**
     * 获得订单总数
     *
     * @return 订单总数
     */
    public int getAllOrderCount() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select count(*) from tb_order";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            count = resultSet.getInt(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return count;
    }

    /**
     * 获取订单中商品列表
     *
     * @param orderID 订单id
     * @return 订单中商品列表
     */
    private List<Good> getGoodInOrder(int orderID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Good> goodList = new ArrayList<>();
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select * from tb_order_good where order_id=? order by id desc;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Good good = getGoodByID(resultSet.getInt("good_id"));
                good.setAmount(resultSet.getInt("count"));
                goodList.add(good);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return goodList;
    }

    /**
     * 根据用户id获取订单列表
     *
     * @param startIndex 开始下标
     * @param pageSize   单页面显示订单数
     * @param userID     用户id
     * @return 订单列表
     */
    public List<Order> getOrderByUserID(int startIndex, int pageSize, int userID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Good> goodList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select * from tb_order where user_id=? order by order_id desc limit ?,?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, startIndex);
            preparedStatement.setInt(3, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = fromResultSetToOrder(resultSet);
                goodList = getGoodInOrder(order.getOrderID());
                order.setList(goodList);
                orderList.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return orderList;
    }

    /**
     * 获得所有订单
     *
     * @param startIndex 开始下标
     * @param pageSize   单页面显示订单数
     * @return 订单列表
     */
    public List<Order> getAllOrder(int startIndex, int pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Good> goodList = null;
        List<Order> orderList = new ArrayList<>();
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select * from tb_order order by order_id desc limit ?,?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, startIndex);
            preparedStatement.setInt(2, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = fromResultSetToOrder(resultSet);
                goodList = getGoodInOrder(order.getOrderID());
                order.setList(goodList);
                orderList.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return orderList;
    }

    /**
     * 获取用户通知数
     *
     * @param userID 用户id
     * @return 用户通知总数
     */
    public int getNoticeCountByUserID(int userID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select count(*) from tb_notice where  user_id=? and sta=1 or sta=3 or sta = 4;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            count = resultSet.getInt(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return count;
    }

    /**
     * 获得商家通知总数
     *
     * @return 商家通知总数
     */
    public int getNoticeCount() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select count(*) from tb_notice where sta=2 or sta=4;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            count = resultSet.getInt(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return count;
    }

    /**
     * 获得用户通知列表
     *
     * @param startIndex 开始下标
     * @param pageSize   单页面显示通知数
     * @param userID     用户id
     * @return 用户通知列表
     */
    public List<Notice> getNotice(int startIndex, int pageSize, int userID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Notice> noticeList = new ArrayList<>();
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select * from tb_notice where  user_id=? and sta=1 or sta=3 or sta = 4 order by notice_time desc limit ?,?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, startIndex);
            preparedStatement.setInt(3, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Notice notice = formResultSetToNotice(resultSet);
                noticeList.add(notice);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return noticeList;
    }

    /**
     * 获得商家通知列表
     *
     * @param startIndex 开始下标
     * @param pageSize   单页面显示通知数
     * @return 商家通知列表
     */
    public List<Notice> getNotice(int startIndex, int pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Notice> noticeList = new ArrayList<>();
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select * from tb_notice where  sta=2 or sta=4 order by notice_time desc limit ?,?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, startIndex);
            preparedStatement.setInt(2, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Notice notice = formResultSetToNotice(resultSet);
                noticeList.add(notice);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return noticeList;
    }

}

