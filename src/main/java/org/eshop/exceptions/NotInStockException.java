package org.eshop.exceptions;

/**
 * The type Not in stock exception.
 */
public class NotInStockException extends Exception {
    /**
     * Instantiates a new Not in stock exception.
     *
     * @param quantity the quantity
     */
    int quantity;
    public NotInStockException(int quantity) {
        super("Not enough items in stock. Only " + quantity + " added to Cart.");
        this.quantity = quantity;
    }
    public int getQuantity(){
        return quantity;
    }
}
