package com.bluemsun.dao;

import com.bluemsun.entity.Good;
import com.bluemsun.util.C3P0Utils;
import com.bluemsun.util.Status;

import java.sql.*;

public class GoodDao {
    /**
     * 商家添加商品
     *
     * @param good 商品
     * @return 商品
     */
    public Good insertGood(Good good) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {

            connection = C3P0Utils.getConnection();
            String sql = "insert into tb_good(good_name,price,kind,description,amount,buyer_number,img_url) values(?,?,?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, good.getName());
            preparedStatement.setBigDecimal(2, good.getPrice());
            preparedStatement.setString(3, good.getKind());
            preparedStatement.setString(4, good.getDescription());
            preparedStatement.setInt(5, good.getAmount());
            preparedStatement.setInt(6, good.getBuyerNumber());
            preparedStatement.setString(7, good.getImageUrl());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return good;
    }

    /**
     * 变更商品人气与库存
     *
     * @param goodID 商品id
     * @param count  商品数量
     *
     */
    public int addGoodBuyerNumber(int goodID, int count) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "update tb_good set buyer_number=buyer_number+? ,amount=amount-? where good_id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, count);
            preparedStatement.setInt(2, count);
            preparedStatement.setInt(3, goodID);
            preparedStatement.executeUpdate();
            jud = Status.OP_SUCCESS.getCode();
        } catch (SQLIntegrityConstraintViolationException e) {
            jud = Status.OP_FAILED.getCode();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return jud;
    }

    /**
     * 更改商品信息
     *
     * @param good 商品
     * @return 更改成功与否
     */
    public int updateGood(Good good) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "update tb_good set good_name=?, price=?, kind=?, description=?, amount=? where good_id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, good.getName());
            preparedStatement.setBigDecimal(2, good.getPrice());
            preparedStatement.setString(3, good.getKind());
            preparedStatement.setString(4, good.getDescription());
            preparedStatement.setInt(5, good.getAmount());
            preparedStatement.setInt(6, good.getGoodID());
            preparedStatement.executeUpdate();
            jud = Status.OP_SUCCESS.getCode();
        } catch (SQLIntegrityConstraintViolationException e) {
           jud = Status.OP_FAILED.getCode();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return jud;
    }

    /**
     * 删除商品
     *
     * @param goodID 商品id
     * @return 删除成功与否
     */
    public int deleteGood(int goodID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "delete from tb_good where good_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodID);
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
