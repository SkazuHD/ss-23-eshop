package org.eshop.shop;

import org.eshop.entities.MassProducts;
import org.eshop.entities.Products;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.PacksizeNotMatching;
import org.eshop.exceptions.ProductNotFound;

import java.util.*;

/**
 * The type Product manager.
 */
public class ProductManager {
    /**
     * The Product map.
     */
//Maps the Product Name to a List of Product Numbers
    //Used to find a Product Number by Name or to find all Product Numbers of a Products with the same Name
    public Map<String, List<Integer>> productMap = new HashMap<>();
    /**
     * The Product nr map.
     */
//Maps the Product Number to a Product
    //Find a specific Product by its Product Number
    public Map<Integer, Products> productNrMap = new HashMap<>();
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
     * @throws ProductNotFound the product not found
     */
    public void removeProduct(int id, int quantity) throws ProductNotFound, NotInStockException, PacksizeNotMatching {
        Products p = getProductById(id);
        //Check if enough Products are in Stock
        if(p instanceof MassProducts mp){
            if(mp.getPacksize() % quantity != 0)
                throw new PacksizeNotMatching(mp.getPacksize());
        }
        if (p.getQuantity() >= quantity)
            p.setQuantity(p.getQuantity() - quantity);
        else throw new NotInStockException(quantity);


    }

    /**
     * Get products set set.
     *
     * @return the set
     */
    public Collection<Products> getProducts() {
        return productNrMap.values();
    }

    /**
     * Gets product by name.
     *
     * @param name the name
     * @return the product by name
     */
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

    /**
     * Gets product by id.
     *
     * @param id the id
     * @return the product by id
     * @throws ProductNotFound the product not found
     */
    public Products getProductById(int id) throws ProductNotFound {
        if (productNrMap.containsKey(id)) {
            return productNrMap.get(id);
        } else {
            throw new ProductNotFound(id);
        }
    }

    /**
     * Create product.
     *
     * @param name     the name
     * @param price    the price
     * @param quantity the quantity
     * @return the products
     */
    public Products createProduct(String name, double price, int quantity, int packsize) {
        //Generate id
        int id = pNrCounter;
        while (productNrMap.containsKey(id)) {
            pNrCounter++;
            id = pNrCounter;
        }
        Products p;

        if(packsize != 0){
            p = new MassProducts(id, price, name, quantity, packsize);
        }else {
            p = new Products(id, price, name, quantity);
        }

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

        return p;
    }

    /**
     * Increase quantity.
     *
     * @param id       the id
     * @param quantity the quantity
     * @throws ProductNotFound the product not found
     */
    public void increaseQuantity(int id, int quantity) throws ProductNotFound, PacksizeNotMatching {
        Products p = getProductById(id);
        if (p instanceof MassProducts mp){
            if (mp.getPacksize() % quantity != 0){
                throw new PacksizeNotMatching(mp.getPacksize());
            }
        }
        p.setQuantity(p.getQuantity() + quantity);
    }

}
