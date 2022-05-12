package com.example.FamilyCareInventory.model;

import java.util.Date;

public class Trans {
    private int transId;
    private String trans_name;
    private Date trans_date;
    private float trans_amt;
    private float cur_acc_amt;
    private float cur_cash_amt;

    private float total_amt;
    private String note;

    public Trans(int transId, String trans_name, Date trans_date, float trans_amt, float cur_acc_amt, float cur_cash_amt, float total_amt, String note) {
        this.transId = transId;
        this.trans_name = trans_name;
        this.trans_date = trans_date;
        this.trans_amt = trans_amt;
        this.cur_acc_amt = cur_acc_amt;
        this.cur_cash_amt = cur_cash_amt;
        this.total_amt = total_amt;
        this.note = note;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public String getTrans_name() {
        return trans_name;
    }

    public void setTrans_name(String trans_name) {
        this.trans_name = trans_name;
    }

    public Date getTrans_date() {
        return trans_date;
    }

    public void setTrans_date(Date trans_date) {
        this.trans_date = trans_date;
    }

    public float getTrans_amt() {
        return trans_amt;
    }

    public void setTrans_amt(float trans_amt) {
        this.trans_amt = trans_amt;
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

    public float getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(float total_amt) {
        this.total_amt = total_amt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
