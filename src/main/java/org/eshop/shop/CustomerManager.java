package org.eshop.shop;

import org.eshop.entities.Customer;
import org.eshop.entities.Invoice;
import org.eshop.entities.Products;
import org.eshop.exceptions.CustomerLoginFailed;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The type Customer manager.
 */
public class CustomerManager {
    /**
     * The Customers.
     */
    Set<Customer> customers = new HashSet<>();
    /**
     * The Gesamt rechnung.
     */
    float gesamtRechnung;
    /**
     * The Gekaufte prod liste.
     */
//  erstellt ein Array mit dem Namen gekaufteProdListe in dem Producte angelegt werden aber bisher keine drin sind
    Products[] gekaufteProdListe = new Products[0];

    /**
     * Instantiates a new Customer manager.
     */
    public CustomerManager() {

    }

    /**
     * Register boolean.
     *
     * @param username the username
     * @param password the password
     * @param name     the name
     * @param address  the address
     * @return the boolean
     */
    public boolean register(String username, String password, String name, String address) {
        Customer c = new Customer(username, password, name, address);
        return customers.add(c);
    }

    /**
     * Login a Customer
     * checks if username exists and if password matches
     *
     * @param username the username
     * @param password the password
     * @return Customer customer
     * @throws CustomerLoginFailed the customer login failed
     */
    public Customer login(String username, String password) throws CustomerLoginFailed {
        //Find User in Set
        for (Customer c : customers) {
            if (c.getUsername().equals(username)) {
                //Check if password matches
                if (c.getPassword().equals(password)) {
                    return c;
                }
            }
        }
        throw new CustomerLoginFailed(username);
    }

    /**
     * Buy product.
     *
     * @param p      the Products
     * @param anzahl the Quantity
     * @param c      the Customer
     */
    public void buyProduct(Products p, int anzahl, Customer c) {
        c.addToCart(p, anzahl);
    }

    /**
     * Remove product.
     *
     * @param p      the Products
     * @param anzahl the Quantity
     * @param c      the Customer
     */
    public void removeProduct(Products p, int anzahl, Customer c) {
        c.removeFromCart(p, anzahl);
    }

    /**
     * Gets cart.
     *
     * @param c the Customer
     * @return the cart of the Customer
     */
    public Map<Products, Integer> getCart(Customer c) {
        return c.getCart();
    }

    /**
     * Checkout invoice.
     *
     * @param c the c
     * @return the invoice
     */
    public Invoice checkout(Customer c) {
        return new Invoice(c);
    }
}
