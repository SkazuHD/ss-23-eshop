package org.eshop.shop;

import org.eshop.entities.Employee;
import org.eshop.entities.User;
import org.eshop.exceptions.LoginFailed;

import java.util.HashMap;
import java.util.Map;

public class EmployeeManager {

    Map<String, Employee> employees = new HashMap<>();

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

    public boolean register(String username, int persoNr, String name, String password) {

        if (employees.containsKey(username)) {
            return false;
        } else {
            Employee e = new Employee(username, persoNr, name, password);
            employees.put(username, e);
            return true;
        }

    }

    public Employee getEmployee(String username) {
        return employees.get(username);
    }
}