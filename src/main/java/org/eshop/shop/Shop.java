package org.eshop.shop;

import org.eshop.entities.Customer;
import org.eshop.entities.Invoice;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.persistence.FileManager;
import org.eshop.persistence.ShopPersistence;
import org.eshop.util.Loger;

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

    ShopPersistence persistence = new FileManager();

    Loger loger = new Loger();

    /**
     * Instantiates a new Shop.
     */
    public Shop() {
        load();
        //TODO: REMOVE TEST DATA WHEN PERSISTENCE IS IMPLEMENTED


        productManager.addProduct("Brot", 1.99, 100);
        productManager.addProduct("Milch", 0.99, 100);
        productManager.addProduct("Eier", 2.99, 100);
        productManager.addProduct("Wurst", 3.99, 100);
        productManager.addProduct("Käse", 4.99, 100);
    }

    public void load() {
        try {
            persistence.openForReading("customers.csv");
            Customer c;
            do {
                c = persistence.readCustomer();
                if (c != null) {
                    customerManager.register(c.getUsername(), c.getPassword(), c.getName(), c.getAddress());
                }
            } while (c != null);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        //Try Saveing to File
        try {
            persistence.openForWriting("customers.csv");
            Customer c = customerManager.getCustomer(username);
            persistence.writeCustomer(c);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            persistence.close();
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
     * @throws NotInStockException the not in stock exception
     * @throws ProductNotFound     the product not found
     */
//CUSTOMER ONLY
    public void addProductToCart(String name, int quantity, Customer c) throws NotInStockException, ProductNotFound {
        Products p = productManager.getProduct(name);
        if (p != null) {
            customerManager.buyProduct(p, quantity, c);
        } else {
            throw new ProductNotFound(name);
        }
    }

    /**
     * Remove product from cart.
     *
     * @param name     the name
     * @param quantity the quantity
     * @param c        the c
     * @throws ProductNotFound the product not found
     */
    public void removeProductFromCart(String name, int quantity, Customer c) throws ProductNotFound {
        Products p = productManager.getProduct(name);
        if (p != null) {
            customerManager.removeProduct(p, quantity, c);
        } else {
            throw new ProductNotFound(name);
        }
    }


    /**
     * }
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
    public void checkout(Customer c) {
        Map<Products, Integer> cart = c.getCart();
        for (Products key : cart.keySet()) {
            productManager.removeProduct(key.getName(), cart.get(key));
            Loger.log(key + " | " + cart.get(key) + " | " + "Customer:" + c.getName());
        }
        cart.clear();

    }

    public String getInvoice(Customer c) {
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

    /**
     * Gets product.
     *
     * @param name the name
     * @return the product
     */
    public Products getProduct(String name) {
        return productManager.getProduct(name);
    }
}
//Employees



