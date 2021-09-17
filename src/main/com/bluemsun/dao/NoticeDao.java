package com.bluemsun.dao;

import com.bluemsun.entity.Notice;
import com.bluemsun.util.C3P0Utils;
import com.bluemsun.util.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NoticeDao {
    /**
     * 添加通知
     *
     * @param orderID 订单id
     * @param userID  用户id
     * @param sta     通知识别码（1、3用户/2、4商家）
     * @return 添加成功与否
     */
    public int insertNotice(int orderID, int userID, int sta) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "insert into tb_notice(user_id,order_id,sta) values(?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, orderID);
            preparedStatement.setInt(3, sta);
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
