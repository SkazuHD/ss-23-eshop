package org.eshop.exceptions;

public class NotInStockException extends Exception {
    public NotInStockException(int quantity) {
        super("Not enough items in stock. Only " + quantity + " added to Cart.");
    }
}
