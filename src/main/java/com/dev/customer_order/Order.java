package com.dev.customer_order;

import java.util.Date;

public class Order {
    private int id;           // auto-increment
    private int customerId;   // foreign key
    private Date orderDate;
    private double totalAmount;

    // Constructors
    public Order() {}

    // For reading from DB
    public Order(int id, int customerId, Date orderDate, double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    // For inserting a new order (auto-increment ID)
    public Order(int customerId, Date orderDate, double totalAmount) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    // Getters / Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // toString
    @Override
    public String toString() {
        return "Order [id=" + id + ", customerId=" + customerId 
               + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + "]";
    }
}

