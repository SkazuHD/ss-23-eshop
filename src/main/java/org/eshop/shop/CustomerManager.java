package org.eshop.shop;

import org.eshop.entities.Customer;
import org.eshop.entities.Products;
import org.eshop.exceptions.CustomerLoginFailed;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomerManager {
    Set<Customer> customers = new HashSet<>();
    float gesamtRechnung;
    //  erstellt ein Array mit dem Namen gekaufteProdListe in dem Producte angelegt werden aber bisher keine drin sind
    Products[] gekaufteProdListe = new Products[0];

    public CustomerManager() {

    }

    public boolean register(String username, String password, String name, String address) {
        Customer c = new Customer(username, password, name, address);
        return customers.add(c);
    }

    /**
     * Login a Customer
     * checks if username exists and if password matches
     *
     * @param username
     * @param password
     * @return Customer
     * @throws CustomerLoginFailed
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

    // der Methode einkaufen werden die parameter Products übergeben und der param. anzahl erstellt
    public void buyProduct(Products p, int anzahl, Customer c) {
        c.addToCart(p, anzahl);
    }

    public void removeProduct(Products p, int anzahl, Customer c) {
        c.removeFromCart(p, anzahl);
    }

    public Map<Products, Integer> getCart(Customer c) {
        return c.getCart();
    }
}
