package org.eshop.exceptions;

/**
 * The type Product not found.
 */
public class ProductNotFound extends Exception {
    /**
     * Instantiates a new Product not found.
     *
     * @param name the name
     */
    public ProductNotFound(String name) {
        super("Product with name:" + name + " not found.");
    }

    public ProductNotFound(int id) {
        super("Product with ID:" + id + " not found.");
    }

}
