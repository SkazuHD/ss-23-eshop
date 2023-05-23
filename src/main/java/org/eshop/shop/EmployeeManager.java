package org.eshop.shop;

import org.eshop.entities.Employee;
import org.eshop.entities.User;
import org.eshop.exceptions.CustomerLoginFailed;

import java.util.HashMap;
import java.util.Map;

public class EmployeeManager {

    Map<Employee> employees = new HashMap<>();

    public User login(String username, String password) throws CustomerLoginFailed {
        User u = employees.get(username);
        if (u != null) {
            if (u.getPassword().equals(password)) {
                u.login();
                return u;
            }
        }
        throw new CustomerLoginFailed(username);
    }

    public boolean register(String username, int persoNr, String name, String password) {
        Employee e = new Employee(username, persoNr, name, password);
        return employees.add(e);
    }
}