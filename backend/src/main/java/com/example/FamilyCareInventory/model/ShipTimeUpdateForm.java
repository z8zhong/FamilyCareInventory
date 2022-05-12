package com.example.FamilyCareInventory.model;

import java.util.Date;

public class ShipTimeUpdateForm {
    private int buyId;
    private String token;
    private Date ship_time;

    public ShipTimeUpdateForm() {
    }

    public ShipTimeUpdateForm(int buyId, String token, Date ship_time) {
        this.buyId = buyId;
        this.token = token;
        this.ship_time = ship_time;
    }

    public int getBuyId() {
        return buyId;
    }

    public void setBuyId(int buyId) {
        this.buyId = buyId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getShip_time() {
        return ship_time;
    }

    public void setShip_time(Date ship_time) {
        this.ship_time = ship_time;
    }
}
