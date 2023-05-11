package org.eshop.entities;

public abstract class User {
    protected String uid;
    protected String username;
    protected String password;
    protected boolean loggedIn = false;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {
        return this.uid;
    }

    public void setID(String id) {
        this.uid = id;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;

    }

    public void login() {
        this.loggedIn = true;
    }

    public void logout() {
        this.loggedIn = false;
    }
}
