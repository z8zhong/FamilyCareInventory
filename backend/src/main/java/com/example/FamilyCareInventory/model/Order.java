package com.example.FamilyCareInventory.model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int orderId;
    private String employee_name;
    private String customer_name;
    private Date trans_time;
    private String note;
    private String flag;
    private ArrayList<OrderLine> products;
    private float total;
    private String method;
    private String name;


    public Order(int orderId, String employee_name, String customer_name, Date trans_time, String note, String flag, ArrayList<OrderLine> products, float total, String method, String name) {
        this.orderId = orderId;
        this.employee_name = employee_name;
        this.customer_name = customer_name;
        this.trans_time = trans_time;
        this.note = note;
        this.flag = flag;
        this.products = products;
        this.total = total;
        this.method = method;
        this.name = name;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Date getTrans_time() {
        return trans_time;
    }

    public void setTrans_time(Date trans_time) {
        this.trans_time = trans_time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ArrayList<OrderLine> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<OrderLine> products) {
        this.products = products;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
