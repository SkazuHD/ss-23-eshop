package org.eshop.entities;

import java.util.Objects;

public class Customer {
    private String uid;
    private String username;
    private String password;
    private String name;
    private String address;
    /**
     * Constructor for Customer
     * @param username
     * @param password
     * @param name
     * @param address
     */
    public Customer (String username, String password, String name, String address){
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
    }


    //Overrides of default Object Methods
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
        return Objects.hash(username);
    }

    //Basic Setter and Getter from here on
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
