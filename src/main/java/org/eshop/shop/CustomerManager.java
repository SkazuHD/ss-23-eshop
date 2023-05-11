package org.eshop.shop;

import org.eshop.entities.Customer;
import org.eshop.entities.Invoice;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.exceptions.CustomerLoginFailed;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Customer manager.
 */
public class CustomerManager {
    /**
     * The Customers.
     */
    Map<String, Customer> customer = new HashMap<>();

    /**
     * Instantiates a new Customer manager.
     */
    public CustomerManager() {

    }

    /**
     * Register boolean.
     *
     * @param username the username
     * @param password the password
     * @param name     the name
     * @param address  the address
     * @return the boolean
     */
    public boolean register(String username, String password, String name, String address) {
        if (customer.containsKey(username)) {
            return false;
        } else {
            Customer c = new Customer(username, password, name, address);
            customer.put(username, c);
            return true;
        }
    }

    /**
     * Login a Customer
     * checks if username exists and if password matches
     *
     * @param username the username
     * @param password the password
     * @return User customer
     * @throws CustomerLoginFailed the customer login failed
     */
    public User login(String username, String password) throws CustomerLoginFailed {
        //Find User in Set
        User u = customer.get(username);
        if (u != null) {
            if (u.getPassword().equals(password)) {
                u.login();
                return u;
            }
        }
        throw new CustomerLoginFailed(username);
    }

    /**
     * Buy product.
     *
     * @param p      the Products
     * @param anzahl the Quantity
     * @param c      the Customer
     */
    public void buyProduct(Products p, int anzahl, Customer c) {
        c.addToCart(p, anzahl);
    }

    /**
     * Remove product.
     *
     * @param p      the Products
     * @param anzahl the Quantity
     * @param c      the Customer
     */
    public void removeProduct(Products p, int anzahl, Customer c) {
        c.removeFromCart(p, anzahl);
    }

    /**
     * Gets cart.
     *
     * @param c the Customer
     * @return the cart of the Customer
     */
    public Map<Products, Integer> getCart(Customer c) {
        return c.getCart();
    }

    /**
     * Checkout invoice.
     *
     * @param c the c
     * @return the invoice
     */
    public Invoice checkout(Customer c) {
        return new Invoice(c);
    }
}
