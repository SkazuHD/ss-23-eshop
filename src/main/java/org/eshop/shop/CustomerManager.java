package org.eshop.shop;
import org.eshop.entities.Customer;
import org.eshop.exceptions.CustomerLoginFailed;

import java.util.*;

public class CustomerManager{
    Set<Customer> customers = new HashSet<>();

    public CustomerManager() {

    }

    public boolean register(String username, String password, String name, String address) {
        Customer c = new Customer(username, password, name, address);
        return customers.add(c);
    }

    /**
     * Login a Customer
     *  checks if username exists and if password matches
     * @param username
     * @param password
     * @return Customer
     * @throws CustomerLoginFailed
     */
    public Customer login(String username, String password) throws CustomerLoginFailed {
        //Find User in Set
        for (Customer c : customers) {
            if (c.getUsername().equals(username)) {
                //Check if password matches
                if (c.getPassword().equals(password)) {
                    return c;
                }
            }
        }
        throw new CustomerLoginFailed(username);
    }
}
