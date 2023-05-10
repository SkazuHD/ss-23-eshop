package org.eshop.exceptions;

/**
 * The type Customer not found.
 */
public class CustomerNotFound extends Exception {
    /**
     * Instantiates a new Customer not found.
     *
     * @param username the username
     */
    public CustomerNotFound(String username) {
        super("Customer with username: " + username + " not found!");
    }
}
