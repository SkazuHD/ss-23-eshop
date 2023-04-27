package org.eshop.shop;

import org.eshop.entities.Customer;

import java.security.CryptoPrimitive;
import java.util.*;

public class CustomerManager {
    Set<Customer> customers = new HashSet<>();

    public CustomerManager() {

    }

    public boolean register(String username, String password) {
        Customer c = new Customer(username, password);
        return customers.add(c);
    }
}
