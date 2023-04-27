package org.eshop.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private String uid;
    private String username;
    private String password;
    private boolean isLoggedIn;
    public Customer (String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString(){
        return username;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return username.equals(customer.username);
    }
    @Override
    public int hashCode() {
        return Objects.hash(uid, username);
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }





}
