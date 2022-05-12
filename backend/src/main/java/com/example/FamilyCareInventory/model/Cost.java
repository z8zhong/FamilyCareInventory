package com.example.FamilyCareInventory.model;

import java.util.Date;

public class Cost {
    private int costId;
    private String name;
    private float amt;
    private Date cost_time;
    private String note;

    public Cost(int costId, String name, float amt, Date cost_time, String note) {
        this.costId = costId;
        this.name = name;
        this.amt = amt;
        this.cost_time = cost_time;
        this.note = note;
    }

    public Cost() {
    }

    public int getCostId() {
        return costId;
    }

    public void setCostId(int costId) {
        this.costId = costId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmt() {
        return amt;
    }

    public void setAmt(float amt) {
        this.amt = amt;
    }

    public Date getCost_time() {
        return cost_time;
    }

    public void setCost_time(Date cost_time) {
        this.cost_time = cost_time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
