package com.bluemsun.entity;

import java.util.Date;

public class Notice {
    private int orderID;
    private int userID;
    private int sta;
    private Date noticeTime;

    public Notice() {
    }

    public Notice(int orderID, int userID, int sta) {
        this.orderID = orderID;
        this.userID = userID;
        this.sta = sta;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSta() {
        return sta;
    }

    public void setSta(int sta) {
        this.sta = sta;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", sta=" + sta +
                ", noticeTime=" + noticeTime +
                '}';
    }
}
