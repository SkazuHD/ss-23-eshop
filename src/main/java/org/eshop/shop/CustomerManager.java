package org.eshop.shop;
import org.eshop.entities.Customer;
import java.util.*;

public class CustomerManager{
    Set<Customer> customers = new HashSet<>();

    public CustomerManager() {

    }

    public boolean register(String username, String password, String name, String address) {
        Customer c = new Customer(username, password, name, address);
        return customers.add(c);
    }
    public boolean login(String username, String password){
        //Find User in Set
        for (Customer c : customers) {
            if (c.getUsername().equals(username)) {
                //Check if password matches
                if (c.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
