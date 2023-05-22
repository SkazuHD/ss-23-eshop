package org.eshop.entities;

/**
 * The type Products.
 */
public class Products {
    //Attribute  Price, Nem und Producktnumber für das Product
    private int Productnumber;
    private double Price;

    private String name;
    private int quantity;


    //constructor für die Attribute

    /**
     * Instantiates a new Products.
     *
     * @param pNr      the p nr
     * @param price    the price
     * @param name     the name
     * @param quantity the quantity
     */
    public Products(int pNr, double price, String name, int quantity) {
        Productnumber = pNr;
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
    public int getProductnumber() {
        return Productnumber;
    }

    /**
     * Sets productnumber.
     *
     * @param productnumber the productnumber
     */
    public void setProductnumber(int productnumber) {
        Productnumber = productnumber;
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
        return (name + " | " + Productnumber + " | " + Price + " EURO");
    }
}
