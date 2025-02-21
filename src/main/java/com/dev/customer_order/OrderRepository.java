package com.dev.customer_order;

import java.sql.SQLException;
import java.util.List;

public interface OrderRepository {
    boolean addOrder(Order order) throws ClassNotFoundException, SQLException;
    List<Order> getAllOrders() throws ClassNotFoundException, SQLException;

    // For the required statistics:
    Order getMostExpensiveOrder() throws ClassNotFoundException, SQLException;
    List<Customer> getTop3CustomersByTotalOrders() throws ClassNotFoundException, SQLException;
}

