package org.eshop.shop;

import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;

public class Shop {

    CustomerManager customerManager = new CustomerManager();
    ProductManager productManager = new ProductManager();
    EmployeeManager employeeManager = new EmployeeManager();

    //Customer
    public void registerUser(String username, String password, String name, String address) throws CustomerExistsException {
        if (!customerManager.register(username, password, name, address)) {
            throw new CustomerExistsException(username);
        }
    }
    public void loginUser(String username, String password) throws CustomerLoginFailed{
        if(!customerManager.login(username, password)){
            throw new CustomerLoginFailed(username);
        }
    }


    //Products


    //Employees


}
