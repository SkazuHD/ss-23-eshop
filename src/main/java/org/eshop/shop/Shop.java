package org.eshop.shop;

import org.eshop.entities.*;
import org.eshop.exceptions.LoginFailed;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.exceptions.UserExistsException;
import org.eshop.persistence.FileManager;
import org.eshop.persistence.ShopPersistence;

import java.io.File;
import java.util.Collection;
import java.util.List;
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
     * The Event manager.
     */
    EventManager eventManager = new EventManager();
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
                Collection<Products> products = productManager.getProducts();
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
                    employeeManager.register(e.getUsername(), Integer.parseInt(e.getID()), e.getName(), e.getPassword());
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
        //Try Saving to File
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
        try {
            return customerManager.login(username, password);
        } catch (LoginFailed e) {
            return employeeManager.login(username, password);
        }
    }


    /**
     * Log out user boolean.
     *
     * @param u the u
     * @return the boolean
     */
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
        return productManager.getProducts();
    }

    /**
     * Add product to cart.
     *
     * @param id       the id of the product
     * @param quantity the quantity
     * @param c        the Customer
     * @throws NotInStockException Exception thrown when the product is not in stock
     * @throws ProductNotFound     Exception thrown when the product is not found
     */
//CUSTOMER ONLY

    //TODO Check if Massproduct -> Packsize match quantiry
    public void addProductToCart(int id, int quantity, Customer c) throws NotInStockException, ProductNotFound {
        Products p = productManager.getProductById(id);
        if (p != null) {
            customerManager.buyProduct(p, quantity, c);
        } else {
            throw new ProductNotFound(id);
        }
    }

    /**
     * Remove product from cart.
     *
     * @param id       the name
     * @param quantity the quantity
     * @param c        the c
     * @throws ProductNotFound the product not found
     */
    public void removeProductFromCart(int id, int quantity, Customer c) throws ProductNotFound {
        Products p = productManager.getProductById(id);
        if (p != null) {
            customerManager.removeProduct(p, quantity, c);
        } else {
            throw new ProductNotFound(id);
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
//TODO Check if the product is in stock before checking out
    public void checkout(Customer c) {
        Map<Products, Integer> cart = c.getCart();
        for (Products key : cart.keySet()) {
            try {
                eventManager.addEvent(c, key, -cart.get(key));
                productManager.removeProduct(key.getId(), cart.get(key));
            } catch (ProductNotFound e) {
                System.out.println(e.getMessage());
            }
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
    //EMPLOYEE ONLY

    /**
     * Increase quantity.
     *
     * @param id       the id
     * @param quantity the quantity
     * @param u        the u
     * @throws ProductNotFound the product not found
     */
    public void increaseQuantity(int id, int quantity, User u) throws ProductNotFound {
        productManager.increaseQuantity(id, quantity);
        saveAsync();
        eventManager.addEvent(u, productManager.getProductById(id), quantity);
    }

    /**
     * Create product.
     *
     * @param name     the name
     * @param price    the price
     * @param quantity the quantity
     * @param u        the u
     */
    public void createProduct(String name, double price, int quantity, User u) {
        Products p = productManager.createProduct(name, price, quantity, 0);
        saveAsync();
        eventManager.addEvent(u, p, quantity);

    }
    public void createMassProduct(String name, double price, int quantity, int packsize, User u){
        Products p = productManager.createProduct(name, price, quantity, packsize);
        saveAsync();
        eventManager.addEvent(u, p, quantity);
    }

    /**
     * Find products list.
     *
     * @param name the name
     * @return the list
     */
    public List<Products> findProducts(String name) {
        return productManager.getProductByName(name);
    }

    /**
     * Find product products.
     *
     * @param id the id
     * @return the products
     * @throws ProductNotFound the product not found
     */
    public Products findProduct(int id) throws ProductNotFound {
        return productManager.getProductById(id);
    }

    /**
     * Remove product.
     *
     * @param id       the name
     * @param quantity the quantity
     * @param user     the user
     * @throws ProductNotFound the product not found
     */
    public void removeProduct(int id, int quantity, User user) throws ProductNotFound {
        productManager.removeProduct(id, quantity);
        saveAsync();

        eventManager.addEvent(user, productManager.getProductById(id), -quantity);
    }

    /**
     * Register employee.
     *
     * @param username the username
     * @param id       the Personalnummmer
     * @param name     the name
     * @param password the password
     * @throws UserExistsException the customer exists exception
     */
//Employees
    public void registerEmployee(String username, int id, String name, String password) throws UserExistsException {
        if (!employeeManager.register(username, id, name, password)) {
            throw new UserExistsException(username);
        }
        //Try Saving to File
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
    public List<Products> getProduct(String name) {
        //TODO MAYBE JUST RETURN THE FIRST ONE
        return productManager.getProductByName(name);
    }
}
//Employees



