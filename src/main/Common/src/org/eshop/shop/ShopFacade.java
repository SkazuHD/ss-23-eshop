package org.eshop.shop;

import org.eshop.entities.*;
import org.eshop.exceptions.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * The interface Shop facade.
 */
public interface ShopFacade {
    //Interface definiert wie die Methode aussieht. /Klasse = Logik  der Methoden
    //mit super ruft man  die Klasse von der geerbt wurde

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
    void registerUser(String username, String password, String name, String address) throws UserExistsException;

    /**
     * Register employee.
     *
     * @param id       the id
     * @param username the username
     * @param password the password
     * @param name     the name
     * @throws UserExistsException the user exists exception
     */
    void registerEmployee(int id, String username, String password, String name) throws UserExistsException;

    /**
     * Log in user.
     *
     * @param username the username
     * @param password the password
     * @return the user
     * @throws LoginFailed the login failed
     */
    User logIn(String username, String password) throws LoginFailed;

    /**
     * Log out boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean logOut(User user);

    User getUser(String username);

    /**
     * Create product.
     *
     * @param name     the name
     * @param price    the price
     * @param quantity the quantity
     * @param user     the user
     */
//Product API
    void createProduct(String name, double price, int quantity, User user);

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
    void createProduct(String name, double price, int quantity, int packSize, User user) throws PacksizeNotMatching;

    /**
     * Delete product.
     *
     * @param id the id
     * @throws ProductNotFound the product not found
     */
    void deleteProduct(int id) throws ProductNotFound;

    /**
     * Edit product details products.
     *
     * @param id    the id
     * @param name  the name
     * @param price the price
     * @return the products
     * @throws ProductNotFound the product not found
     */
    Product editProductDetails(int id, String name, double price) throws ProductNotFound;

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
    Product editProductDetails(int id, String name, double price, int packSize) throws ProductNotFound;

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
    void changeQuantity(int id, int quantity, User u) throws ProductNotFound, PacksizeNotMatching, NotInStockException, NegativeQuantityException;

    /**
     * Gets all products.
     *
     * @return the all products
     */
    Collection<Product> getAllProducts();

    Collection<Event> getAllEvents();

    Collection<Employee> getAllEmployees();

    /**
     * Find products list.
     *
     * @param name the name
     * @return the list
     */
    List<Product> findProducts(String name);

    /**
     * Find product products.
     *
     * @param ID the id
     * @return the products
     * @throws ProductNotFound the product not found
     */
    Product findProduct(int ID) throws ProductNotFound;

    int[] getProductHistory(int productId, int days);

    /**
     * Gets invoice.
     *
     * @param c the c
     * @return the invoice
     */
//Customer API
    Invoice getInvoice(Customer c);

    /**
     * Checkout.
     *
     * @param c the c
     */
    void checkout(Customer c) throws CheckoutFailed;

    /**
     * Gets cart.
     *
     * @param c the c
     * @return the cart
     */
    Map<Product, Integer> getCart(Customer c);

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
    void addToCart(int id, int quantity, Customer c) throws PacksizeNotMatching, NotInStockException, ProductNotFound;

    /**
     * Remove from cart.
     *
     * @param id       the id
     * @param quantity the quantity
     * @param c        the c
     * @throws PacksizeNotMatching the packsize not matching
     * @throws ProductNotFound     the product not found
     */
    void removeFromCart(int id, int quantity, Customer c) throws PacksizeNotMatching, ProductNotFound;

    void clearCart(Customer c);

    /**
     * Save.
     */
//PERSISTENCE API
    void save();


}
