package org.eshop.shop;

import org.eshop.entities.Customer;
import org.eshop.entities.Employee;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.exceptions.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * The interface Shop facade.
 */
public interface ShopFacade {
    /**
     * Register user.
     *
     * @param username the username
     * @param password the password
     * @param name     the name
     * @param address  the address
     * @throws UserExistsException the user exists exception
     */
//User API
    public void registerUser(String username, String password, String name, String address) throws UserExistsException;

    /**
     * Register employee.
     *
     * @param id       the id
     * @param username the username
     * @param password the password
     * @param name     the name
     * @throws UserExistsException the user exists exception
     */
    public void registerEmployee(int id, String username, String password, String name) throws UserExistsException;

    /**
     * Log in user.
     *
     * @param username the username
     * @param password the password
     * @return the user
     * @throws LoginFailed the login failed
     */
    public User logIn(String username, String password) throws LoginFailed;

    /**
     * Log out boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean logOut(User user);

    public User getUser(String username);

    /**
     * Create product.
     *
     * @param name     the name
     * @param price    the price
     * @param quantity the quantity
     * @param user     the user
     */
//Product API
    public void createProduct(String name,double price, int quantity, User user);

    /**
     * Create product.
     *
     * @param name     the name
     * @param price    the price
     * @param quantity the quantity
     * @param packSize the pack size
     * @param user     the user
     * @throws PacksizeNotMatching the packsize not matching
     */
    public void createProduct(String name, double price, int quantity , int packSize, User user) throws PacksizeNotMatching;

    /**
     * Delete product.
     *
     * @param id the id
     * @throws ProductNotFound the product not found
     */
    public void deleteProduct(int id) throws ProductNotFound;

    /**
     * Edit product details products.
     *
     * @param id    the id
     * @param name  the name
     * @param price the price
     * @return the products
     * @throws ProductNotFound the product not found
     */
    public Products editProductDetails(int id, String name, double price) throws ProductNotFound;

    /**
     * Edit product details products.
     *
     * @param id       the id
     * @param name     the name
     * @param price    the price
     * @param packSize the pack size
     * @return the products
     * @throws ProductNotFound the product not found
     */
    public Products editProductDetails(int id, String name, double price, int packSize) throws ProductNotFound;

    /**
     * Change quantity.
     *
     * @param id       the id
     * @param quantity the quantity
     * @param u        the u
     * @throws ProductNotFound     the product not found
     * @throws PacksizeNotMatching the packsize not matching
     * @throws NotInStockException the not in stock exception
     */
    public void changeQuantity(int id, int quantity, User u) throws ProductNotFound, PacksizeNotMatching, NotInStockException;

    /**
     * Gets all products.
     *
     * @return the all products
     */
    public Collection<Products> getAllProducts();
    public Collection <Employee>getAllEmployees();
    /**
     * Find products list.
     *
     * @param name the name
     * @return the list
     */
    public List<Products> findProducts(String name);

    /**
     * Find product products.
     *
     * @param ID the id
     * @return the products
     * @throws ProductNotFound the product not found
     */
    public Products findProduct(int ID) throws ProductNotFound;


    /**
     * Gets invoice.
     *
     * @param c the c
     * @return the invoice
     */
//Customer API
    public String getInvoice(Customer c);

    /**
     * Checkout.
     *
     * @param c the c
     */
    public void checkout(Customer c);

    /**
     * Gets cart.
     *
     * @param c the c
     * @return the cart
     */
    public Map<Products, Integer> getCart(Customer c);

    /**
     * Add to cart.
     *
     * @param id       the id
     * @param quantity the quantity
     * @param c        the c
     * @throws PacksizeNotMatching the packsize not matching
     * @throws NotInStockException the not in stock exception
     * @throws ProductNotFound     the product not found
     */
    public void addToCart(int id, int quantity, Customer c) throws PacksizeNotMatching, NotInStockException, ProductNotFound;

    /**
     * Remove from cart.
     *
     * @param id       the id
     * @param quantity the quantity
     * @param c        the c
     * @throws PacksizeNotMatching the packsize not matching
     * @throws ProductNotFound     the product not found
     */
    public void removeFromCart(int id, int quantity, Customer c) throws PacksizeNotMatching, ProductNotFound;

    /**
     * Save.
     */
//PERSISTENCE API
    public void save();

    /**
     * Load.
     */
    public void load();
}
