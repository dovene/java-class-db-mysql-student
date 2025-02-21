package com.dev.customer_order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.config.DatabaseConfig;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        DatabaseConfig dbConfig = new DatabaseConfig();
        Connection conn = dbConfig.getConnection();

        String sql = "INSERT INTO customers (name, email) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getEmail());

        int rows = ps.executeUpdate();
        ps.close();
        conn.close();
        return rows > 0;
    }

    @Override
    public List<Customer> getAllCustomers() throws ClassNotFoundException, SQLException {
        List<Customer> customers = new ArrayList<>();
        DatabaseConfig dbConfig = new DatabaseConfig();
        Connection conn = dbConfig.getConnection();

        String sql = "SELECT * FROM customers";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");

            Customer customer = new Customer(id, name, email);
            customers.add(customer);
        }

        rs.close();
        ps.close();
        conn.close();
        return customers;
    }
}

