package com.bluemsun.entity;

import java.math.BigDecimal;

public class Good {
    private int goodID;
    private String name;
    private BigDecimal price;
    private String kind;
    private int amount;
    private String description;
    private int buyerNumber;
    private String imageUrl;

    public Good() {
    }

    public Good(String name, BigDecimal price, String kind, int amount, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.kind = kind;
        this.amount = amount;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public int getGoodID() {
        return goodID;
    }

    public void setGoodID(int goodID) {
        this.goodID = goodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBuyerNumber() {
        return buyerNumber;
    }

    public void setBuyerNumber(int buyerNumber) {
        this.buyerNumber = buyerNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String toString() {
        return "\n" + "{goodID:" + goodID + "\n"
                + "name:" + name + "\n"
                + "price:" + price + "\n"
                + "description:" + description + "\n"
                + "kind:" + kind + "\n"
                + "amount" + amount + "\n"
                + "imageUrl" + imageUrl + "\n"
                + "}";
    }
}
