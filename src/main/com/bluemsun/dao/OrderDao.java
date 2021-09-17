package com.bluemsun.dao;

import com.bluemsun.entity.Order;
import com.bluemsun.util.C3P0Utils;
import com.bluemsun.util.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao {
    /**
     * 生成订单
     *
     * @param order 订单
     * @return 订单
     */
    public Order insertOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "insert into tb_order(user_id,money,phone_number,address,user_name) values (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getUserID());
            preparedStatement.setBigDecimal(2, order.getMoney());
            preparedStatement.setString(3, order.getPhoneNumber());
            preparedStatement.setString(4, order.getAddress());
            preparedStatement.setString(5, order.getUsername());
            preparedStatement.executeUpdate();
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
    }

    /**
     * 获取订单生成时间时间及id
     *
     * @param order 订单
     * @return 含有id与生成时间的订单
     */
    public Order getOrderID(Order order) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select order_id,create_time from tb_order where user_id=? and sta=1 order by create_time DESC";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getUserID());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setOrderID(resultSet.getInt("order_id"));
                order.setCreateDate(resultSet.getDate("create_time"));
            } else {
                order = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return order;
    }

    /**
     * 添加物品至订单
     *
     * @param orderID 订单id
     * @param goodID  商品id
     * @param count   商品数量
     * @return 执行成功与否
     */
    public int insertGoodInOrder(int orderID, int goodID, int count) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "insert into tb_order_good(order_id,good_id,`count`) values (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, goodID);
            preparedStatement.setInt(3, count);
            preparedStatement.executeUpdate();
            jud = Status.OP_SUCCESS.getCode();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return jud;
    }

    /**
     * 更新订单状态
     *
     * @param orderID 订单id
     * @param sta     状态码（0已完成/1等待发货/2等待收货）
     */
    public int updateOrderSta(int orderID, int sta) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "update tb_order set sta=? where order_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, sta);
            preparedStatement.setInt(2, orderID);
            preparedStatement.executeUpdate();
            jud = Status.OP_SUCCESS.getCode();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return jud;
    }

    /**
     * 添加评价
     *
     * @param orderId 订单号
     * @param comment 评价内容
     */
    public int addOrderComment(int orderId, String comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "update tb_order set user_comment=? where order_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, comment);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
            jud = Status.OP_SUCCESS.getCode();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return jud;
    }

    /**
     * 删除评价
     *
     * @param orderID 订单id
     */
    public int dropComment(int orderID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "update tb_order set user_comment=null where order_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderID);
            preparedStatement.executeUpdate();
            jud = Status.OP_SUCCESS.getCode();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return jud;
    }

}
