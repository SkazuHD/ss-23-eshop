package org.eshop.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The type Customer.
 */
public class Customer extends User {
    private final Map<Product, Integer> cart = new HashMap<>();
    private String name;
    private final String address;

    /**
     * Constructor for Customer
     *
     * @param username the username
     * @param password the password
     * @param name     the name
     * @param address  the address
     * @param id       the id
     */
    public Customer(String username, String password, String name, String address, String id) {
        super(username, password);
        this.name = name;
        this.address = address;
        this.setID(id);
    }


    /**
     * Returns the customer as a string
     *
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + " " + getName() + " " + getAddress();
    }


    /**
     * Checks if the customer is equal to another customer based on the username
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getUsername().equals(customer.getUsername());
    }

    /**
     * Returns the hashcode of the customer based on the username
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets cart.
     *
     * @return the cart
     */
    public Map<Product, Integer> getCart() {
        return cart;
    }

    /**
     * Add to cart.
     *
     * @param product  the product
     * @param quantity the quantity
     * @return the int
     */
    public int addToCart(Product product, int quantity) {
        //Check if product is in stock
        if (cart.containsKey(product)) { //iST produkt schon im warenkop wird geprüft/ addiert auf
            if (quantity + cart.get(product) >= product.getQuantity()) { // Passt die Quantity / ist genug im Lager
                //Differenz wird berechnet Wichtig/ Wird geschaut wie viel man dem Korb noch hinzufügen kann
                int difference = product.getQuantity() - cart.get(product);
                cart.put(product, product.getQuantity());//Die maximal verfügbare menge des Prod. wird in den Korb getan
                return difference;
            } else {
                cart.put(product, quantity + cart.get(product));
                return quantity; //Wenn genug im Lager ist wird hinzugefügt wie viel man will
            }
        } else if (quantity >= product.getQuantity()) {
            cart.put(product, product.getQuantity());//Prod wird in den Warenkob gelegt
            return product.getQuantity();// Maximale anzahl wird hinzugefügt
        } else {
            cart.put(product, quantity);
            return quantity;//Wenn genug im Lager ist wird hinzugefügt wie viel man will
        }

    }

    /**
     * Remove from cart.
     *
     * @param product  the product
     * @param quantity the quantity
     */
    public void removeFromCart(Product product, int quantity) {
        if (cart.get(product) == null) return;

        if (quantity >= cart.get(product)) {
            cart.remove(product);
        } else {
            cart.put(product, cart.get(product) - quantity);
        }
    }

    public void clearCart() {
        cart.clear();
    }
}
