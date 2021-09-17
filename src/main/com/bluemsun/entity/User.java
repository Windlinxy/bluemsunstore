package com.bluemsun.entity;

import java.math.BigDecimal;

public class User {
    private int identify;
    private int sex;
    private int userID;
    private String username;
    private String phoneNumber;
    private String password;
    private BigDecimal balance;

    public User() {
    }

    public int getIdentify() {
        return identify;
    }

    public void setIdentify(int identify) {
        this.identify = identify;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "user{" + "\n"
                + "identify:" + identify + "\n"
                + "sex:" + sex + "\n"
                + "userID" + userID + "\n"
                + "username:" + username + "\n"
                + "phoneNUmber" + password + "\n"
                + "balance:" + balance + "\n"
                + "}";
    }
}
