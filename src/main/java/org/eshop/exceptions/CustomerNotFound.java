package org.eshop.exceptions;

public class CustomerNotFound extends Exception{
    public CustomerNotFound(String username){
        super("Customer with username: " + username + " not found!");
    }
}
