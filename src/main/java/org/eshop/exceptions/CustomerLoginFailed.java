package org.eshop.exceptions;

/**
 * The type Customer login failed.
 */
public class CustomerLoginFailed extends Exception {
    /**
     * Instantiates a new Customer login failed.
     *
     * @param username the username
     */
    public CustomerLoginFailed(String username) {
        super("Customer with username: " + username + " not found! Or password is incorrect!");
    }
}
