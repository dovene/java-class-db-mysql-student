package com.dev.customer_order;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {
    boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException;
    List<Customer> getAllCustomers() throws ClassNotFoundException, SQLException;

    // Optionally: getCustomerById, updateCustomer, deleteCustomer, etc.
    // For brevity, only the required methods are shown.
}

