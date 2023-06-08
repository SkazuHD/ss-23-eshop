package org.eshop.entities;

/**
 * The type Products.
 */
public class Products {
    //Attribute  Price, Nem und Producktnumber für das Product
    private int id;
    private double Price;

    private String name;
    private int quantity;


    //constructor für die Attribute

    /**
     * Instantiates a new Products.
     *
     * @param id       the p nr
     * @param price    the price
     * @param name     the name
     * @param quantity the quantity
     */
    public Products(int id, double price, String name, int quantity) {
        this.id = id;
        Price = price;
        this.name = name;
        this.quantity = quantity;

    }

    /**
     * Gets name.
     *
     * @return the name
     */
// Getter und setter für die Attribute
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return Price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        Price = price;
    }

    /**
     * Gets productnumber.
     *
     * @return the productnumber
     */
    public int getId() {
        return id;
    }

    /**
     * Sets productnumber.
     *
     * @param id the productnumber
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return String.format("%-5d %-6.2f %-22s %d", this.getId(), this.getPrice(), this.getName(), this.getQuantity());
    }
}
