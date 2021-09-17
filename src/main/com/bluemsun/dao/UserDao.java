package com.bluemsun.dao;

import com.bluemsun.entity.User;
import com.bluemsun.util.C3P0Utils;

import java.math.BigDecimal;
import java.sql.*;

public class UserDao {
    /**
     * 注册用户信息
     *
     * @param user 用户
     * @return 用户
     */
    public User userRegister(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "insert into tb_user(username,phone_number,password,sex) values(?,?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getSex());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return user;
    }

    /**
     * 用户登录查询
     *
     * @param user 用户
     * @return 查询到的用户
     */
    public User userLogin(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user1 = new User();
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select * from tb_user where phone_number=? and password=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getPhoneNumber());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user1 = new User();
                user1.setUserID(resultSet.getInt("user_id"));
                user1.setUsername(resultSet.getString("username"));
                user1.setSex(resultSet.getInt("sex"));
                user1.setIdentify(resultSet.getInt("identify"));
                user1.setPhoneNumber(resultSet.getString("phone_number"));
                user1.setPassword(resultSet.getString("password"));
                user1.setBalance(resultSet.getBigDecimal("balance"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return user1;
    }

    /**
     * 变更用户钱包（+金额）
     *
     * @param phoneNumber 手机号
     * @param money       充值金额
     * @return 充值金额
     */
    public BigDecimal updateBalance(String phoneNumber, BigDecimal money) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "update tb_user set balance=balance+? where phone_number=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(1, money);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            money = null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return money;
    }

    /**
     * 商品结算用户钱包扣钱
     *
     * @param userID 用户id
     * @param money  消费金额
     * @return 消费金额
     */
    public BigDecimal updateBalance(int userID, BigDecimal money) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "update tb_user set balance=balance-? where user_id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(1, money);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            money = null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement);
        }
        return money;
    }

    /**
     * 查询用户信息
     *
     * @param phoneNumber 手机号
     * @return 用户
     */
    public User queryUser(String phoneNumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = C3P0Utils.getConnection();
            String sql = "select * from tb_user where phone_number=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phoneNumber);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setSex(resultSet.getInt("sex"));
                user.setIdentify(resultSet.getInt("identify"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            C3P0Utils.releaseConnection(connection, preparedStatement, resultSet);
        }
        return user;
    }
}
