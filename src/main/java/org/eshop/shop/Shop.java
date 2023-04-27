package org.eshop.shop;

import org.eshop.exceptions.CustomerExistsException;

public class Shop {

    CustomerManager customerManager = new CustomerManager();
    ProductManager productManager = new ProductManager();
    EmployeeManager employeeManager = new EmployeeManager();

    //Customer
    public boolean registerUser(String username, String password) throws CustomerExistsException {
        if (!customerManager.register(username, password)) {
            throw new CustomerExistsException(username);
        }
        return true;
    }


    //Products


    //Employees


}
