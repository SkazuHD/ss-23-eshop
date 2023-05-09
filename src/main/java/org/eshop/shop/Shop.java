package org.eshop.shop;

import org.eshop.entities.Customer;
import org.eshop.entities.Products;
import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;

import java.util.Set;

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
    public Customer loginUser(String username, String password) throws CustomerLoginFailed{
        return customerManager.login(username, password);
    }


    //Products
    public Set<Products> getProducts() {
        System.err.println("TODO IMPLEMENT GET PRODUCTS");
        return null;
        //return productManager.getProducts();
    }
    //CUSTOMER ONLY
    public void addProductToCart(String name, int quantity){

    }
    public void removeProductFromCart(String name, int quantity){

    }
    public void checkout(){

    }
    //EMPLOYEE ONLY
    public void addProduct(String name, double price, int quantity){

    }
    public void removeProduct(String name, int quantity){

    }
    //Employees


}
