package org.eshop.entities;

import java.util.Date;
import java.util.Map;

/**
 * Invoice
 * Contains the customer, date, items with quantity and price and total price of the invoice
 * Returns the invoice as a string
 */
public class Invoice {
    Customer c;
    Map<Products, Integer> cart;
    Date date;

    public Invoice(Customer c) {
        this.c = c;
        this.date = new Date();
        this.cart = c.getCart();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Invoice for ").append(c.getName()).append("\n");
        output.append("Date: ").append(date.toString()).append("\n");
        output.append("Items:\n");
        for (Map.Entry<Products, Integer> entry : cart.entrySet()) {
            output.append(entry.getKey().getName())
                    .append(" x")
                    .append(entry.getValue())
                    .append(" ")
                    .append(String.format("%.2f", entry.getKey().getPrice() * entry.getValue()))
                    .append(" EURO \n");
        }
        output.append("Total: ")
                .append(String.format("%.2f", getTotalPrice()))
                .append(" EURO");
        return output.toString();
    }

    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Products, Integer> entry : cart.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

}