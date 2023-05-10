package org.eshop.exceptions;

/**
 * The type Customer exists exception.
 */
public class CustomerExistsException extends Exception {
    /**
     * Instantiates a new Customer exists exception.
     *
     * @param username the username
     */
    public CustomerExistsException(String username) {
        super(username + " Already exists!");
    }
}
