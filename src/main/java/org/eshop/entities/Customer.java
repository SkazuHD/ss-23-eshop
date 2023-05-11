package org.eshop.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The type Customer.
 */
public class Customer implements User {
    private final Map<Products, Integer> cart = new HashMap<>();
    private boolean loggedIn = false;
    private String uid;
    private String username;
    private String password;
    private String name;
    private String address;

    /**
     * Constructor for Customer
     *
     * @param username the username
     * @param password the password
     * @param name     the name
     * @param address  the address
     */
    public Customer(String username, String password, String name, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
    }


    /**
     * Returns the customer as a string
     *
     * @return String
     */
    @Override
    public String toString() {
        return username;
    }


    /**
     * Checks if the customer is equal to another customer based on the username
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return username.equals(customer.username);
    }

    /**
     * Returns the hashcode of the customer based on the username
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Returns the uid of the customer
     *
     * @return String uid
     */
    public String getID() {
        return uid;
    }

    /**
     * Sets the uid of the customer
     *
     * @param uid String
     */
    public void setID(String uid) {
        this.uid = uid;
    }


    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the customer
     *
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedin() {
        return loggedIn;
    }

    public void login() {
        this.loggedIn = true;
    }

    public void logout() {
        this.loggedIn = false;
    }

    /**
     * Gets cart.
     *
     * @return the cart
     */
    public Map<Products, Integer> getCart() {
        return cart;
    }

    /**
     * Add to cart.
     *
     * @param product  the product
     * @param quantity the quantity
     */
    public void addToCart(Products product, int quantity) {
        cart.put(product, quantity);
    }

    /**
     * Remove from cart.
     *
     * @param product  the product
     * @param quantity the quantity
     */
    public void removeFromCart(Products product, int quantity) {
        if (quantity >= cart.get(product)) {
            cart.remove(product);
        } else {
            cart.put(product, cart.get(product) - quantity);
        }
    }
}
