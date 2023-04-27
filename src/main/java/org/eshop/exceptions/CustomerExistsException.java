package org.eshop.exceptions;

public class CustomerExistsException extends Exception{
    public CustomerExistsException(String username){
        super(username+" Already exists!");
    }
}
