package com.example.FamilyCareInventory.model;

public class OrderLine {
    private int orderId;
    private int orderlineId;
    private String product_name;
    private String product_version;
    private int quantity;
    private float price;

    public OrderLine(int orderId, int orderlineId, String product_name, String product_version, int quantity, float price) {
        this.orderId = orderId;
        this.orderlineId = orderlineId;
        this.product_name = product_name;
        this.product_version = product_version;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderlineId() {
        return orderlineId;
    }

    public void setOrderlineId(int orderlineId) {
        this.orderlineId = orderlineId;
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
