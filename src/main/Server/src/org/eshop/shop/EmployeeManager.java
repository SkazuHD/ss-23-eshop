package org.eshop.shop;

import org.eshop.entities.Employee;
import org.eshop.entities.User;
import org.eshop.exceptions.LoginFailed;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Employee manager.
 */
public class EmployeeManager {

    /**
     * The Employees.
     */
    Map<String, Employee> employees = new HashMap<>();

    /**
     * Login user.
     *
     * @param username the username
     * @param password the password
     * @return the user
     * @throws LoginFailed the logIn failed
     */
    public User login(String username, String password) throws LoginFailed {
        User u = employees.get(username);
        if (u != null) {
            if (u.getPassword().equals(password)) {
                u.login();
                return u;
            }
        }
        throw new LoginFailed(username);
    }

    /**
     * Register boolean.
     *
     * @param username the username
     * @param id       the perso nr
     * @param name     the name
     * @param password the password
     * @return the boolean
     */
    public boolean register(int id, String name, String username, String password) {

        if (employees.containsKey(username)) {
            return false;
        } else {
            Employee e = new Employee(id, name, username, password);
            employees.put(username, e);
            return true;
        }

    }

    public Collection<Employee> getEmployee() {
        return employees.values();
    }

    /**
     * Gets employee.
     *
     * @param username the username
     * @return the employee
     */
    public Employee getEmployee(String username) {
        return employees.get(username);

    }
}