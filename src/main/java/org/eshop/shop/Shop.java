package org.eshop.shop;

import org.eshop.entities.Customer;
import org.eshop.entities.Invoice;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;
import org.eshop.exceptions.NotInStockException;

import java.util.Collection;
import java.util.Map;

/**
 * The type Shop.
 */
public class Shop {

    /**
     * The Customer manager.
     */
    CustomerManager customerManager = new CustomerManager();
    /**
     * The Product manager.
     */
    ProductManager productManager = new ProductManager();
    /**
     * The Employee manager.
     */
    EmployeeManager employeeManager = new EmployeeManager();

    /**
     * Instantiates a new Shop.
     */
    public Shop() {

        //TODO: REMOVE TEST DATA WHEN PERSISTENCE IS IMPLEMENTED
        customerManager.register("Peter", "peter", "peter", "peter");

        productManager.addProduct("Brot", 1.99, 100);
        productManager.addProduct("Milch", 0.99, 100);
        productManager.addProduct("Eier", 2.99, 100);
        productManager.addProduct("Wurst", 3.99, 100);
        productManager.addProduct("Käse", 4.99, 100);
    }

    /**
     * Register user.
     *
     * @param username the username
     * @param password the password
     * @param name     the name
     * @param address  the address
     * @throws CustomerExistsException the customer exists exception
     */
    public void registerUser(String username, String password, String name, String address) throws CustomerExistsException {
        if (!customerManager.register(username, password, name, address)) {
            throw new CustomerExistsException(username);
        }
    }

    /**
     * Login user customer.
     *
     * @param username the username
     * @param password the password
     * @return the customer
     * @throws CustomerLoginFailed the customer login failed
     */
    public User loginUser(String username, String password) throws CustomerLoginFailed {
        return customerManager.login(username, password);
    }


    /**
     * Gets product set.
     *
     * @return the product set
     */
//Products
    public Collection<Products> getProductSet() {
        return productManager.getProductsSet();
    }

    /**
     * Add product to cart.
     *
     * @param name     the name
     * @param quantity the quantity
     * @param c        the c
     */
//CUSTOMER ONLY
    public void addProductToCart(String name, int quantity, Customer c) throws NotInStockException {
        Products p = productManager.getProduct(name);
        customerManager.buyProduct(p, quantity, c);
    }

    /**
     * Remove product from cart.
     *
     * @param name     the name
     * @param quantity the quantity
     * @param c        the c
     */
    public void removeProductFromCart(String name, int quantity, Customer c) {
        Products p = productManager.getProduct(name);
        customerManager.removeProduct(p, quantity, c);
    }

    /**
     * Gets cart.
     *
     * @param c the Customer
     * @return the cart
     */
    public Map<Products, Integer> getCart(Customer c) {
        return c.getCart();
    }

    /**
     * Checkout.
     *
     * @param c the Customer
     * @return the string
     */
    public String checkout(Customer c) {
        Invoice i = customerManager.checkout(c);
        return i.toString();
    }

    /**
     * Add product.
     *
     * @param name     the name
     * @param price    the price
     * @param quantity the quantity
     */
//EMPLOYEE ONLY
    public void addProduct(String name, double price, int quantity) {
        productManager.addProduct(name, price, quantity);
    }

    /**
     * Remove product.
     *
     * @param name     the name
     * @param quantity the quantity
     */
    public void removeProduct(String name, int quantity) {
        productManager.removeProduct(name, quantity);
    }
}
//Employees



