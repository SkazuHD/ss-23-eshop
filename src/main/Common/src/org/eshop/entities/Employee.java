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
     * Instantiates a new Employee.
     *
     * @param name     the name
     * @param id       the perso nr
     * @param username the username
     * @param password the password
     */
    public Employee(int id, String name, String username, String password) {
        super(username, password);
        setName(name);
        setID(String.valueOf(id));
    }

    @Override
    public String toString() {
        return super.toString() + " " + getName();
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


}