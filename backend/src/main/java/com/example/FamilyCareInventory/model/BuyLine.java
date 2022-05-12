package com.example.FamilyCareInventory.model;

public class BuyLine {
    private int buyId;
    private int buylineId;

    private String product_name;

    private String product_version;
    private int quantity;
    private float price;

    public BuyLine(int buyId, int buylineId, String product_name, String product_version, int quantity, float price) {
        this.buyId = buyId;
        this.buylineId = buylineId;
        this.product_name = product_name;
        this.product_version = product_version;
        this.quantity = quantity;
        this.price = price;
    }

    public int getBuyId() {
        return buyId;
    }

    public void setBuyId(int buyId) {
        this.buyId = buyId;
    }

    public int getBuylineId() {
        return buylineId;
    }

    public void setBuylineId(int buylineId) {
        this.buylineId = buylineId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_version() {
        return product_version;
    }

    public void setProduct_version(String product_version) {
        this.product_version = product_version;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
