package org.eshop.entities;

/**
 * The type User.
 */
public abstract class User {
    /**
     * The Uid.
     */
    protected String uid;
    /**
     * The Username.
     */
    protected String username;
    /**
     * The Password.
     */
    protected String password;
    /**
     * The Logged in.
     */
    protected boolean loggedIn = false;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param password the password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "ID:" + getID() + " Username:" + getUsername();
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getID() {
        return this.uid;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setID(String id) {
        this.uid = id;
    }

    /**
     * Is logged in boolean.
     *
     * @return the boolean
     */
    public boolean isLoggedIn() {
        return this.loggedIn;

    }

    /**
     * Login.
     */
    public void login() {
        this.loggedIn = true;
    }

    /**
     * Logout.
     */
    public void logout() {
        this.loggedIn = false;
    }
}
