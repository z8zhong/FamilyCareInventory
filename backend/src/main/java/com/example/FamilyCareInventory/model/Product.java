package com.example.FamilyCareInventory.model;

import java.util.Date;

public class Product {
    private int productId;
    private String product_name;
    private String product_version;
    private int inventory;

    private Date created_time;

    public Product(int productId, String product_name, String product_version, int inventory, Date created_time) {
        this.productId = productId;
        this.product_name = product_name;
        this.product_version = product_version;
        this.inventory = inventory;
        this.created_time = created_time;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }
}
