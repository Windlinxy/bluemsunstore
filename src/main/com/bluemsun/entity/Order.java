package com.bluemsun.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {
    private int orderID;
    private Date createDate;
    private int userID;
    private BigDecimal money;
    private String phoneNumber;
    private String address;
    private String username;
    private String comment;
    private int sta;
    private List<Good> list;

    public Order() {
    }

    public Order(int userID, BigDecimal money, String phoneNumber, String address, String username) {
        this.userID = userID;
        this.money = money;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.username = username;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Good> getList() {
        return list;
    }

    public void setList(List<Good> list) {
        this.list = list;
    }

    public int getSta() {
        return sta;
    }

    public void setSta(int sta) {
        this.sta = sta;
    }

    @Override
    public String toString() {
        return "\nOrder{" + "\n" +
                "orderID=" + orderID + "\n" +
                "creatDate=" + createDate + "\n" +
                "userID=" + userID + "\n" +
                "money=" + money + "\n" +
                "phoneNumber='" + phoneNumber + "\n" +
                "address='" + address + "\n" +
                "username='" + username + "\n" +
                "userComment='" + comment + "\n" +
                '}';
    }
}
