package org.eshop.entities;

public interface User {

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getID();

    void setID(String id);

    boolean isLoggedin();

    void login();

    void logout();
}
