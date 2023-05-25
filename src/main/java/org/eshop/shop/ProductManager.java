package org.eshop.shop;

import org.eshop.entities.Products;
import org.eshop.exceptions.ProductNotFound;

import java.util.*;

/**
 * The type Product manager.
 */
public class ProductManager {
    //Maps the Product Name to a List of Product Numbers
    //Used to find a Product Number by Name or to find all Product Numbers of a Products with the same Name
    public Map<String, List<Integer>> productMap = new HashMap<String, List<Integer>>();
    //Maps the Product Number to a Product
    //Find a specific Product by its Product Number
    public Map<Integer, Products> productNrMap = new HashMap<Integer, Products>();
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
     * Fügt ein Produckt hinzu und zählt die Producktnummer hoch
     *
     * @param name     gibt dem Produckt einen Namen
     * @param price    gibt dem Prodcukt einen Preis
     * @param quantity gibt dem Produckt eine Mengenanzahl
     */

    /**
     * Load product.
     *
     * @param p the p
     */
    public void loadProduct(Products p) {
        String name = p.getName();
        int id = p.getId();
        if (productMap.containsKey(name)) {
            productMap.get(name).add(id);
        } else {
            // Else add new Entry
            List<Integer> idList = new ArrayList<>();
            idList.add(id);
            productMap.put(name, idList);
        }
        productNrMap.put(id, p);
    }

    /**
     * Remove product.
     *
     * @param id       the id
     * @param quantity the quantity
     */
    public void removeProduct(int id, int quantity) throws ProductNotFound {
        //TODO IMPLEMENT
        Products p = getProductById(id);
        //Check if enough Products are in Stock
        if (p.getQuantity() >= quantity) p.setQuantity(p.getQuantity() - quantity);


    }

    /**
     * Get products set set.
     *
     * @return the set
     */
    public Collection<Products> getProducts() {
        return productNrMap.values();
    }

    public List<Products> getProductByName(String name) {
        List<Products> result = new ArrayList<>();
        List<Integer> ids = productMap.get(name);
        if (ids == null) {
            return result;
        }
        //Add all Products to result
        for (int id : ids) {
            result.add(productNrMap.get(id));
        }
        return result;
    }

    public Products getProductById(int id) throws ProductNotFound {
        if (productNrMap.containsKey(id)) {
            return productNrMap.get(id);
        } else {
            throw new ProductNotFound(id);
        }
    }

    public void createProduct(String name, double price, int quantity) {
        //Generate id
        int id = pNrCounter;
        while (productNrMap.containsKey(id)) {
            pNrCounter++;
            id = pNrCounter;
        }
        Products p = new Products(id, price, name, quantity);

        // Check if name already exist in Map and add id to List
        if (productMap.containsKey(name)) {
            productMap.get(name).add(id);
        } else {
            // Else add new Entry
            List<Integer> idList = new ArrayList<>();
            idList.add(id);
            productMap.put(name, idList);
        }
        productNrMap.put(id, p);

    }

    public void increaseQuantity(int id, int quantity) throws ProductNotFound {
        Products p = getProductById(id);
        p.setQuantity(p.getQuantity() + quantity);
    }

}
