package com.manojbisht.input_order.model;

public class Order {
    private String orderNumber;
    private String orderUserNAme;
    private String orderEmail;
    private String orderPhoneNumber;
    private String orderTripDate;
    private String orderCurrentDate;


    public Order() {
    }

    public Order(String orderNumber, String orderUserNAme, String orderEmail, String orderPhoneNumber, String orderTripDate, String orderCurrentDate) {
        this.orderNumber = orderNumber;
        this.orderUserNAme = orderUserNAme;
        this.orderEmail = orderEmail;
        this.orderPhoneNumber = orderPhoneNumber;
        this.orderTripDate = orderTripDate;
        this.orderCurrentDate = orderCurrentDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderUserNAme() {
        return orderUserNAme;
    }

    public void setOrderUserNAme(String orderUserNAme) {
        this.orderUserNAme = orderUserNAme;
    }

    public String getOrderEmail() {
        return orderEmail;
    }

    public void setOrderEmail(String orderEmail) {
        this.orderEmail = orderEmail;
    }

    public String getOrderPhoneNumber() {
        return orderPhoneNumber;
    }

    public void setOrderPhoneNumber(String orderPhoneNumber) {
        this.orderPhoneNumber = orderPhoneNumber;
    }

    public String getOrderTripDate() {
        return orderTripDate;
    }

    public void setOrderTripDate(String orderTripDate) {
        this.orderTripDate = orderTripDate;
    }

    public String getOrdercurrentDate() {
        return orderCurrentDate;
    }

    public void setOrdercurrentDate(String ordercurrentDate) {
        this.orderCurrentDate = ordercurrentDate;
    }
}
