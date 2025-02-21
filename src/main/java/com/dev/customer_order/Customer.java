package com.dev.customer_order;

public class Customer {
    private int id;         // auto-increment in DB
    private String name;
    private String email;

    // Constructors
    public Customer() {}

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters / Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() { 
        return name; 
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // toString
    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}
