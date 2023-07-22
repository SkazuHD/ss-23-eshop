package org.eshop.exceptions;

/**
 * The type Customer exists exception.
 */
public class UserExistsException extends Exception {
    /**
     * Instantiates a new Customer exists exception.
     *
     * @param username the username
     */
    public UserExistsException(String username) {
        super(username + " Already exists!");
    }
}
