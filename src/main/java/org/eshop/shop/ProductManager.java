package org.eshop.shop;

import org.eshop.entities.Products;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// producte hinzufügen
// löschen
// Mitarbeiter soll den bestand ändern
// exeption
//product wird gekauft


/**
 * The type Product manager.
 */
public class ProductManager {

    /**
     * The Products set.
     */
    public Map<String, Products> products = new HashMap<String, Products>();
    /**
     * The P nr counter.
     */
    int pNrCounter = 1000;

    /**
     * Instantiates a new Product manager.
     */
    public ProductManager() {
    }

    /**
     * Instantiates a new Product manager with a Filled Set.
     *
     * @param productsMap the products set
     */
    public ProductManager(Map<String, Products> productsMap) {
        this.products = productsMap;
    }

    /**
     * Fügt ein Produckt hinzu und zählt die Producktnummer hoch
     *
     * @param name     gibt dem Produckt einen Namen
     * @param price    gibt dem Prodcukt einen Preis
     * @param quantity gibt dem Produckt eine Mengenanzahl
     */
    public void addProduct(String name, double price, int quantity) {

        if (products.containsKey(name)) {
            //Increase Quantity of existing Product
            Products p = products.get(name);
            p.setQuantity(p.getQuantity() + quantity);
            p.setPrice(price);
        } else {
            //Create new Product
            Products p = new Products(pNrCounter, price, name, quantity);
            products.put(name, p);
            pNrCounter++;
        }

    }

    /**
     * Remove product.
     *
     * @param name     the name
     * @param quantity the quantity
     */
    public void removeProduct(String name, int quantity) {

        if (products.containsKey(name)) {
            //Decrease Quantity of existing Product
            Products p = products.get(name);
            p.setQuantity(p.getQuantity() - quantity);
            if (p.getQuantity() <= 0) {
                products.remove(name);
            }
        }
    }

    /**
     * Get product products.
     *
     * @param name the name
     * @return the products
     */
    public Products getProduct(String name) {

        return products.get(name);

    }

    /**
     * Get products set set.
     *
     * @return the set
     */
    public Collection<Products> getProductsSet() {
        return products.values();
    }

}
