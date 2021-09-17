package com.bluemsun.dao;

import com.bluemsun.entity.Good;
import com.bluemsun.util.C3P0Utils;
import com.bluemsun.util.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    /**
     * 将商品添加至购物车
     *
     * @param userID 用户id
     * @param goodID 商品id
     * @param count  商品数量
     * @return 执行顺利与否
     */
    public int insertGoodInCart(int userID, int goodID, int count) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud =0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "insert into tb_cart(user_id,good_id,`count`) values(?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, goodID);
            preparedStatement.setInt(3, count);
            preparedStatement.executeUpdate();
            jud = Status.OP_SUCCESS.getCode();
        } catch (SQLIntegrityConstraintViolationException e) {
            jud = Status.OP_FAILED.getCode();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return jud;
    }

    /**
     * 删除购物车商品
     *
     * @param userID 用户id
     * @param goodID 商品id
     * @return 执行正确与否
     */
    public int dropGoodInCart(int userID, int goodID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud = 0;
        try {

            connection = C3P0Utils.getConnection();
            String sql = "delete from tb_cart where user_id = ? and good_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, goodID);
            preparedStatement.executeUpdate();
            jud = Status.OP_SUCCESS.getCode();;
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
     * 更改购物车商品数量
     *
     * @param userID 用户id
     * @param goodID 商品id
     * @param count  商品数量
     * @return true
     */
    public int updateGoodInCart(int userID, int goodID, int count) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "update tb_cart set `count`= ? where user_id = ? and good_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(2, userID);
            preparedStatement.setInt(3, goodID);
            preparedStatement.setInt(1, count);
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
     * 用户id获得商品列表
     *
     * @param userID 用户id
     * @return 商品列表
     */
    public List<Good> getGoodListByUserID(int userID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Good> goodList = new ArrayList<>();
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select good_id ,`count` from tb_cart where user_id=? order by id desc";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            PageDao pageDao = new PageDao();
            while (resultSet.next()) {
                Good good = pageDao.getGoodByID(resultSet.getInt("good_id"));
                good.setAmount(resultSet.getInt("count"));
                goodList.add(good);
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return goodList;
    }

    /**
     * 获取商品数量
     *
     * @param userID 用户id
     * @param goodID 商品id
     * @return 数量
     */
    public int getGoodCount(int userID, int goodID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select `count` from tb_cart where user_id=? and good_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, goodID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return count;
    }

    /**
     * 购物车清空
     *
     * @param userID 用户id
     * @return true
     */
    public int dropCart(int userID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "delete from tb_cart where user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
            jud = Status.OP_SUCCESS.getCode();
        } catch (SQLIntegrityConstraintViolationException e) {
            jud = Status.OP_FAILED.getCode();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return jud;
    }

    /**
     * @param userID 用户id
     * @return       执行正确与否
     */
    public int updateCartStatus(int userID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int jud = 0;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "delete from tb_cart where user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
            jud = Status.OP_SUCCESS.getCode();
        }  catch (SQLIntegrityConstraintViolationException e) {
            jud = Status.OP_FAILED.getCode();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return jud;
    }
}
