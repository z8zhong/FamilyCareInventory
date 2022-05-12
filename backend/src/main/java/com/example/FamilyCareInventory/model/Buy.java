package com.example.FamilyCareInventory.model;

import java.util.ArrayList;
import java.util.Date;

public class Buy {
    private int buyId;
    private Date buy_time;
    private String note;
    private float deposit;

    private ArrayList<BuyLine> products;

    private Date ship_time;

    private String name;

    public Buy(int buyId, Date buy_time, String note, float deposit, ArrayList<BuyLine> products, Date ship_time, String name) {
        this.buyId = buyId;
        this.buy_time = buy_time;
        this.note = note;
        this.deposit = deposit;
        this.products = products;
        this.ship_time = ship_time;
        this.name = name;
    }

    public Buy() {
    }

    public int getBuyId() {
        return buyId;
    }

    public void setBuyId(int buyId) {
        this.buyId = buyId;
    }

    public Date getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(Date buy_time) {
        this.buy_time = buy_time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public ArrayList<BuyLine> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<BuyLine> products) {
        this.products = products;
    }

    public Date getShip_time() {
        return ship_time;
    }

    public void setShip_time(Date ship_time) {
        this.ship_time = ship_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
