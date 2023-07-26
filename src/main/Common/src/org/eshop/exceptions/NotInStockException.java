package org.eshop.exceptions;

/**
 * The type Not in stock exception.
 */
public class NotInStockException extends Exception {
    //unterschied zwischen Blitz Klasse und C?? also zwischen exeption class und nur Java??

    int quantity;

    /**
     * Instantiates a new Not in stock exception.
     *
     * @param quantity the quantity
     */
    public NotInStockException(int quantity) {
        super("Not enough items in stock. Only " + quantity + " added to Cart.");
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
