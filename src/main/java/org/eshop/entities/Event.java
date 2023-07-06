package org.eshop.entities;


import java.time.LocalDate;

/**
 * The type Event.
 * Creates an event object that is used to store the user id, product id and quantity of a product.
 */
public class Event {
    /**
     * The User id.
     */
    final String userId;
    /**
     * The Product id.
     */
    final int productId;
    /**
     * The Quantity.
     */
    final int quantity;
    /**
     * The Day in year.
     */
    final int dayInYear;

    /**
     * Instantiates a new Event.
     *
     * @param user     the user
     * @param p        the p
     * @param quantity the quantity
     */
    public Event(User user, Product p, int quantity) {
        this.userId = user.getID();
        this.productId = p.getId();
        this.quantity = quantity;
        this.dayInYear = LocalDate.now().getDayOfYear();
    }

    //Load from file
    public Event(int dayInYear, String user, int p, int quantity) {
        this.userId = user;
        this.productId = p;
        this.quantity = quantity;
        this.dayInYear = dayInYear;
    }

    @Override
    public String toString() {
        return "Event{" +
                "userId='" + userId + '\'' +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", dayInYear=" + dayInYear +
                '}';
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public int getProductId() {
        return productId;
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
     * Gets day in year.
     *
     * @return the day in year
     */
    public int getDayInYear() {
        return dayInYear;
    }
}
