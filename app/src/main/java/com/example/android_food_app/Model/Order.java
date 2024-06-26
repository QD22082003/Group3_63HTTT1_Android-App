package com.example.android_food_app.Model;

public class Order {
    private String id, userID, name, phone, address, date, status;
    private double total, deliveryCost;

    public Order(String id, String userID, String name, String phone, String address, double total, double deliveryCost, String date, String status) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.total = total;
        this.deliveryCost = deliveryCost;
        this.date = date;
        this.status = status;
    }

    // Constructor without arguments
    public Order() {
        // Initialize default values or leave them null
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
