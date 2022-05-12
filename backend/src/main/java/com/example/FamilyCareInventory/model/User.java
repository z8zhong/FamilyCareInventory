package com.example.FamilyCareInventory.model;

public class User {
    private int userId;
    private String username;
    private String role;
    private String pwd;

    public User(int userId, String username, String role, String pwd) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.pwd = pwd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int empId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
