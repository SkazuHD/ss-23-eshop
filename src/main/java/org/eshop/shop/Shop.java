package org.eshop.shop;

import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;
import org.eshop.exceptions.CustomerNotFound;

public class Shop {

    CustomerManager customerManager = new CustomerManager();
    ProductManager productManager = new ProductManager();
    EmployeeManager employeeManager = new EmployeeManager();

    //Customer
    public boolean registerUser(String username, String password,String name, String address) throws CustomerExistsException {
        if (!customerManager.register(username, password, name, address)) {
            throw new CustomerExistsException(username);
        }
        return true;
    }
    public boolean loginUser(String username, String password) throws CustomerLoginFailed{
        if(!customerManager.login(username, password)){
            throw new CustomerLoginFailed(username);
        }return true;
    }


    //Products


    //Employees


}
