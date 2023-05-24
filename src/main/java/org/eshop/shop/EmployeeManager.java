package org.eshop.shop;

import org.eshop.entities.Employee;
import org.eshop.entities.User;
import org.eshop.exceptions.CustomerLoginFailed;

import java.util.HashMap;
import java.util.Map;

public class EmployeeManager {

    Map<String, Employee> employee = new HashMap<>();

    public User login(String username, String password) throws CustomerLoginFailed {
        for (Employee e : employee) {
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
        if (employee.containsKey(username)) {
            return false;
        } else {
            Employee e = new Employee(username, persoNr, name, password);
            employee.put(username, e);
            return true;
        }
    }
}