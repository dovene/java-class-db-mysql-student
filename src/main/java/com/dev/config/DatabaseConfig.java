package com.dev.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "rootroot";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER); 
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

