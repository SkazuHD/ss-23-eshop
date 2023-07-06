package org.eshop.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Invoice
 * Contains the customer, date, items with quantity and price and total price of the invoice
 * Returns the invoice as a string
 */

public class Invoice {
    Customer c;
    Map<Product, Integer> cart;
    Date date;

    /**
     * Constructor
     *
     * @param c Customer
     */
    public Invoice(Customer c) {
        this.c = c;
        this.date = new Date();
        Map<Product, Integer> cartCopy = c.getCart();
        this.cart = new HashMap<>();
        cart.putAll(cartCopy);

    }

    /**
     * Returns the invoice as a string
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("--------------------\n");
        output.append("Invoice for ").append(c.getName()).append("\n");
        output.append("Date: ").append(date.toString()).append("\n");
        output.append("Items:\n");
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            output.append(entry.getKey().getName())
                    .append(" x")
                    .append(entry.getValue())
                    .append(" ")
                    .append(String.format("%.2f", entry.getKey().getPrice() * entry.getValue()))
                    .append(" EURO \n");
        }
        output.append("Total: ")
                .append(String.format("%.2f", getTotalPrice()))
                .append(" EURO\n");
        output.append("--------------------\n");
        return output.toString();
    }

    /**
     * Returns the total calculated price of the invoice
     *
     * @return double
     */
    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public Customer getCustomer() {
        return c;
    }

    public Date getDate() {
        return date;
    }

}
