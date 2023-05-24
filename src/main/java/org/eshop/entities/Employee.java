package org.eshop.entities;

/**
 * The type Employee.
 */
public class Employee extends User {
    /**
     * The Name.
     */
    String name;
    /**
     * The Perso nr.
     */
    int persoNr;

    /**
     * Instantiates a new Employee.
     *
     * @param name     the name
     * @param persoNr  the perso nr
     * @param username the username
     * @param password the password
     */
    public Employee(String name, int persoNr, String username, String password) {
        super(username, password);
        this.name = name;
        this.persoNr = persoNr;
    }

    @Override
    public String toString() {
        return getPersoNr() + " " + getName();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets perso nr.
     *
     * @return the perso nr
     */
    public int getPersoNr() {
        return persoNr;
    }

    /**
     * Sets perso nr.
     *
     * @param persoNr the perso nr
     */
    public void setPersoNr(int persoNr) {
        this.persoNr = persoNr;
    }

}