package org.eshop.shop;

import org.eshop.entities.Customer;
import org.eshop.entities.Invoice;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.exceptions.LoginFailed;
import org.eshop.exceptions.NotInStockException;

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
    int id = 3000;

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
            Customer c = new Customer(username, password, name, address, "C" + id++);
            customer.put(username, c);
            return true;
        }
    }

    public void loadCustomer(Customer c) {
        customer.put(c.getUsername(), c);
        //Update ID counter
        id = Integer.parseInt(c.getID().substring(1)) + 1; //increment it by 1


    }

    /**
     * Login a Customer
     * checks if username exists and if password matches
     *
     * @param username the username
     * @param password the password
     * @return User customer
     * @throws LoginFailed the customer login failed
     */
    public User login(String username, String password) throws LoginFailed {
        //Find User in Set
        User u = customer.get(username);
        if (u != null) {
            if (u.getPassword().equals(password)) {
                u.login();
                return u;
            }
        }
        throw new LoginFailed(username);
    }

    /**
     * Gets customer.
     *
     * @param username the username
     * @return the customer
     */
    public Customer getCustomer(String username) {
        return customer.get(username);
    }

    /**
     * Buy product.
     *
     * @param p      the Products
     * @param anzahl the Quantity
     * @param c      the Customer
     * @throws NotInStockException the not in stock exception
     */
    public void buyProduct(Products p, int anzahl, Customer c) throws NotInStockException {
        int addedAmount = c.addToCart(p, anzahl);
        if (addedAmount != anzahl) {
            throw new NotInStockException(addedAmount);
        }
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
