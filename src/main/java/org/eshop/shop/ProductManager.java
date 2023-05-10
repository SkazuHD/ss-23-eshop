package org.eshop.shop;

import org.eshop.entities.Products;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
    public Set<Products> productsSet = new HashSet<>();
    /**
     * The P nr counter.
     */
    int pNrCounter = 1000;

    /**
     * Fügt ein Produckt hinzu und zählt die Producktnummer hoch
     *
     * @param name     gibt dem Produckt einen Namen
     * @param price    gibt dem Prodcukt einen Preis
     * @param quantity gibt dem Produckt eine Mengenanzahl
     * @return the boolean
     */
    public boolean addProduct(String name, double price, int quantity) {
        Products p = new Products(pNrCounter, price, name, quantity);
        pNrCounter++;
        productsSet.add(p);

        return true;
    }

    /**
     * Remove product.
     *
     * @param name     the name
     * @param quantity the quantity
     */
    public void removeProduct(String name, int quantity) {

        Iterator<Products> it = productsSet.iterator();

        while (it.hasNext()) {
            Products p = it.next();
            if (p.getName().equals(name)) {
                p.setQuantity(p.getQuantity() - quantity);
                if (p.getQuantity() <= 0) {
                    productsSet.remove(p);
                }
                break;
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

        Iterator<Products> it = productsSet.iterator();

        while (it.hasNext()) {
            Products p = it.next();
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;

    }

    /**
     * Get products set set.
     *
     * @return the set
     */
    public Set<Products> getProductsSet() {
        return productsSet;
    }

}
