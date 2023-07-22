package org.eshop.exceptions;

/**
 * The type Customer logIn failed.
 */
public class LoginFailed extends Exception {
    /**
     * Instantiates a new Customer logIn failed.
     *
     * @param username the username
     */
    public LoginFailed(String username) {
        super("User with username: " + username + " not found! Or password is incorrect!");
    }
}
