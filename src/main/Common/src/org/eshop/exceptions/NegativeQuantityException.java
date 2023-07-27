package org.eshop.exceptions;

public class NegativeQuantityException extends Exception {
    int quantity;

    public NegativeQuantityException(int quantity) {
        super("Negative quantity not allowed. Quantity: " + quantity);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
