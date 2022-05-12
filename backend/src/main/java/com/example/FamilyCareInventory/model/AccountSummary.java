package com.example.FamilyCareInventory.model;

public class AccountSummary {
    private float buy_amt;
    private float cost_amt;
    private float order_amt;
    private float cur_acc_amt;
    private float cur_cash_amt;
    private float cur_total_amt;

    public AccountSummary(float buy_amt, float cost_amt, float order_amt, float cur_acc_amt, float cur_cash_amt, float cur_total_amt) {
        this.buy_amt = buy_amt;
        this.cost_amt = cost_amt;
        this.order_amt = order_amt;
        this.cur_acc_amt = cur_acc_amt;
        this.cur_cash_amt = cur_cash_amt;
        this.cur_total_amt = cur_total_amt;
    }

    public AccountSummary() {
    }

    public float getBuy_amt() {
        return buy_amt;
    }

    public void setBuy_amt(float buy_amt) {
        this.buy_amt = buy_amt;
    }

    public float getCost_amt() {
        return cost_amt;
    }

    public void setCost_amt(float cost_amt) {
        this.cost_amt = cost_amt;
    }

    public float getOrder_amt() {
        return order_amt;
    }

    public void setOrder_amt(float order_amt) {
        this.order_amt = order_amt;
    }

    public float getCur_acc_amt() {
        return cur_acc_amt;
    }

    public void setCur_acc_amt(float cur_acc_amt) {
        this.cur_acc_amt = cur_acc_amt;
    }

    public float getCur_cash_amt() {
        return cur_cash_amt;
    }

    public void setCur_cash_amt(float cur_cash_amt) {
        this.cur_cash_amt = cur_cash_amt;
    }

    public float getCur_total_amt() {
        return cur_total_amt;
    }

    public void setCur_total_amt(float cur_total_amt) {
        this.cur_total_amt = cur_total_amt;
    }
}
