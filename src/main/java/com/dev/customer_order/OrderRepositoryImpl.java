package com.dev.customer_order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.config.DatabaseConfig;

public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public boolean addOrder(Order order) throws ClassNotFoundException, SQLException {
        DatabaseConfig dbConfig = new DatabaseConfig();
        Connection conn = dbConfig.getConnection();

        String sql = "INSERT INTO orders (customer_id, order_date, total_amount) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, order.getCustomerId());
        ps.setDate(2, new Date(order.getOrderDate().getTime()));  // java.sql.Date
        ps.setDouble(3, order.getTotalAmount());

        int rows = ps.executeUpdate();
        ps.close();
        conn.close();
        return rows > 0;
    }

    @Override
    public List<Order> getAllOrders() throws ClassNotFoundException, SQLException {
        List<Order> orders = new ArrayList<>();
        DatabaseConfig dbConfig = new DatabaseConfig();
        Connection conn = dbConfig.getConnection();

        String sql = "SELECT * FROM orders";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int customerId = rs.getInt("customer_id");
            Date orderDate = rs.getDate("order_date");
            double totalAmount = rs.getDouble("total_amount");

            orders.add(new Order(id, customerId, orderDate, totalAmount));
        }

        rs.close();
        ps.close();
        conn.close();
        return orders;
    }

    @Override
    public Order getMostExpensiveOrder() throws ClassNotFoundException, SQLException {
        DatabaseConfig dbConfig = new DatabaseConfig();
        Connection conn = dbConfig.getConnection();

        // Query by highest total_amount:
        String sql = "SELECT * FROM orders ORDER BY total_amount DESC LIMIT 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        Order order = null;
        if (rs.next()) {
            int id = rs.getInt("id");
            int customerId = rs.getInt("customer_id");
            Date orderDate = rs.getDate("order_date");
            double totalAmount = rs.getDouble("total_amount");
            order = new Order(id, customerId, orderDate, totalAmount);
        }

        rs.close();
        ps.close();
        conn.close();
        return order;
    }

    @Override
    public List<Customer> getTop3CustomersByTotalOrders() throws ClassNotFoundException, SQLException {
        
        String sql = 
            "SELECT c.id, c.name, c.email, SUM(o.total_amount) as totalSpent " +
            "FROM customers c " +
            "JOIN orders o ON c.id = o.customer_id " +
            "GROUP BY c.id, c.name, c.email " +
            "ORDER BY totalSpent DESC " +
            "LIMIT 3";

        List<Customer> topCustomers = new ArrayList<>();

        DatabaseConfig dbConfig = new DatabaseConfig();
        Connection conn = dbConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            topCustomers.add(new Customer(id, name, email));
        }

        rs.close();
        ps.close();
        conn.close();
        return topCustomers;
    }
}

