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
        super("Product " + name + " not found.");
    }
}
