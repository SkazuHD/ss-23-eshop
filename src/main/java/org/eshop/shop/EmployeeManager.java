package org.eshop.shop;

import org.eshop.entities.Employee;
import org.eshop.entities.User;
import org.eshop.exceptions.CustomerLoginFailed;

import java.util.HashSet;
import java.util.Set;

public class EmployeeManager {

    Set<Employee> employees = new HashSet<>();

    public User login(String username, String password) throws CustomerLoginFailed {
        for (Employee e : employees) {
            // check if usernam & password match
            if (e.getUsername().equals(username)) {
                if (e.getPassword().equals(password)) {
                    e.login();
                    return e;
                }
            }
        }
        throw new CustomerLoginFailed(username);
    }

    public boolean register(String username, int persoNr, String name, String password) {
        Employee e = new Employee(username, persoNr, name, password);
        return employees.add(e);
    }
}