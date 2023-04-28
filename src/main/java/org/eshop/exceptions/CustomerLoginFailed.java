package org.eshop.exceptions;

public class CustomerLoginFailed extends Exception{
    public CustomerLoginFailed(String username){
        super("Customer with username: " + username + " not found! Or password is incorrect!");
    }
}
