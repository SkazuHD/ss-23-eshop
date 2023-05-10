package org.eshop.shop;

import org.eshop.entities.Customer;
import org.eshop.entities.Products;
import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;

import java.util.Map;
import java.util.Set;

public class Shop {

    CustomerManager customerManager = new CustomerManager();
    ProductManager productManager = new ProductManager();
    EmployeeManager employeeManager = new EmployeeManager();

    public Shop() {
        customerManager.register("Peter", "peter", "peter", "peter");

        productManager.addProduct("Brot", 1.99, 100);
        productManager.addProduct("Milch", 0.99, 100);
        productManager.addProduct("Eier", 2.99, 100);
        productManager.addProduct("Wurst", 3.99, 100);
        productManager.addProduct("Käse", 4.99, 100);
    }

    //Customer
    public void registerUser(String username, String password, String name, String address) throws CustomerExistsException {
        if (!customerManager.register(username, password, name, address)) {
            throw new CustomerExistsException(username);
        }
    }

    public Customer loginUser(String username, String password) throws CustomerLoginFailed {
        return customerManager.login(username, password);
    }


    //Products
    public Set<Products> getProductSet() {
        return productManager.getProductsSet();
    }

    //CUSTOMER ONLY
    public void addProductToCart(String name, int quantity, Customer c) {
        Products p = productManager.getProduct(name);
        customerManager.buyProduct(p, quantity, c);
    }

    public void removeProductFromCart(String name, int quantity) {
    }

    public Map<Products, Integer> getCart(Customer c) {
        return c.getCart();
    }

    public void checkout() {

    }

    //EMPLOYEE ONLY
    public void addProduct(String name, double price, int quantity) {

    }

    public void removeProduct(String name, int quantity) {

    }
}
//Employees



