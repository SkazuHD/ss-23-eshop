package org.eshop.exceptions;

public class ProductNotFound extends Exception {
    public ProductNotFound(String name) {
        super("Product " + name + " not found.");
    }
}
