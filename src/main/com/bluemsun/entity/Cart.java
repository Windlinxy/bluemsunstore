package com.bluemsun.entity;

import java.util.List;

public class Cart {
    private int userID;
    private int goodID;
    private int amount;
    List<Good> list; //用于存放数据库中的数据结果集,使用泛型，增强通用性

    public Cart() {
    }

    public Cart(int userID, int goodID, int amount) {
        this.userID = userID;
        this.goodID = goodID;
        this.amount = amount;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGoodID() {
        return goodID;
    }

    public void setGoodID(int goodID) {
        this.goodID = goodID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Good> getList() {
        return list;
    }

    public void setList(List<Good> list) {
        this.list = list;
    }
}
