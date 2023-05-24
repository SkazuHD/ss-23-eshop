package org.eshop.shop;

import org.eshop.entities.*;
import org.eshop.exceptions.LoginFailed;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.exceptions.UserExistsException;
import org.eshop.persistence.FileManager;
import org.eshop.persistence.ShopPersistence;
import org.eshop.util.Loger;

import java.io.File;
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
     * The Persistence.
     */
    ShopPersistence persistence = new FileManager();

    /**
     * Instantiates a new Shop.
     * and loads the data from the csv files
     */
    public Shop() {
        load();
    }

    /**
     * Save async.
     * Saves products to csv file Async
     */
    public void saveAsync() {
        // Parallel Process
        new Thread(() -> {

            //Test if file is in use
            File file = new File("products.csv");
            while (!file.renameTo(file)) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                persistence.openForWriting("products.csv", false);
                Collection<Products> products = productManager.getProductsSet();
                for (Products p : products) {
                    persistence.writeProducts(p);
                }
                persistence.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Load.
     * Loads the data from the csv files using the persistence Module
     */
    public void load() {
        try {

            persistence.openForReading("customers.csv");
            Customer c;
            do {
                c = persistence.readCustomer();
                if (c != null) {
                    customerManager.loadCustomer(c);
                }
            } while (c != null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            persistence.close();
        }
        try {
            persistence.openForReading("employees.csv");
            Employee e;
            do {
                e = persistence.readEmployee();
                if (e != null) {
                    employeeManager.register(e.getUsername(), e.getPersoNr(), e.getName(), e.getPassword());
                }
            } while (e != null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            persistence.close();
        }
        try {
            persistence.openForReading("products.csv");
            Products p;
            do {
                p = persistence.readProducts();
                if (p != null) {
                    productManager.loadProduct(p);
                }
            } while (p != null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            persistence.close();
        }
    }

    /**
     * Register user.
     *
     * @param username the username
     * @param password the password
     * @param name     the name
     * @param address  the address
     * @throws UserExistsException the customer exists exception
     */
    public void registerUser(String username, String password, String name, String address) throws UserExistsException {
        if (!customerManager.register(username, password, name, address)) {
            throw new UserExistsException(username);
        }
        //Try Saveing to File
        try {
            persistence.openForWriting("customers.csv", true);
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
     * @throws LoginFailed the customer login failed
     */
    public User loginUser(String username, String password) throws LoginFailed {
        return customerManager.login(username, password);
    }

    public User loginEmployee(String username, String password) throws LoginFailed {
        return employeeManager.login(username, password);
    }

    public boolean logOutUser(User u) {
        u.logout();
        return u.isLoggedIn();
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
     * @param name     the name of the product
     * @param quantity the quantity
     * @param c        the Customer
     * @throws NotInStockException Exception thrown when the product is not in stock
     * @throws ProductNotFound     Exception thrown when the product is not found
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
        return customerManager.getCart(c);
    }

    /**
     * Checkout.
     *
     * @param c the Customer
     */
    public void checkout(Customer c) {
        Map<Products, Integer> cart = c.getCart();
        for (Products key : cart.keySet()) {
            Loger.log(key + " " + cart.get(key));
            productManager.removeProduct(key.getName(), cart.get(key));
        }
        saveAsync();
        cart.clear();


    }

    /**
     * Gets invoice.
     *
     * @param c the c
     * @return the invoice
     */
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
    public void addProduct(String name, double price, int quantity, Employee e) {
        productManager.addProduct(name, price, quantity);
        saveAsync();

        Loger.log(e + "|" + "Added: " + " " + name + "|" + price + "|" + quantity);
    }

    /**
     * Remove product.
     *
     * @param name     the name
     * @param quantity the quantity
     */
    public void removeProduct(String name, int quantity, User user) {
        productManager.removeProduct(name, quantity);
        saveAsync();
        if (user instanceof Employee) {
            Loger.log(((Employee) user).getPersoNr() + "|" + user.getUsername() + "|" + "Removed: " + name + "|" + quantity);
        }
    }

    /**
     * Register employee.
     *
     * @param username the username
     * @param persoNr  the Personalnummmer
     * @param name     the name
     * @param password the password
     * @throws UserExistsException the customer exists exception
     */
    //Employees
    public void registerEmployee(String username, int persoNr, String name, String password) throws UserExistsException {
        if (!employeeManager.register(username, persoNr, name, password)) {
            throw new UserExistsException(username);
        }
        //Try Saveing to File
        try {
            persistence.openForWriting("employees.csv", true);
            Employee e = employeeManager.getEmployee(username);
            persistence.writeEmployee(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            persistence.close();
        }
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



