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
    String userId;
    /**
     * The Product id.
     */
    int productId;
    /**
     * The Quantity.
     */
    int quantity;
    /**
     * The Day in year.
     */
    int dayInYear;

    /**
     * Instantiates a new Event.
     *
     * @param user     the user
     * @param p        the p
     * @param quantity the quantity
     */
    public Event(User user, Products p, int quantity) {
        this.userId = user.getID();
        this.productId = p.getId();
        this.quantity = quantity;
        this.dayInYear = LocalDate.now().getDayOfYear();
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
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(int productId) {
        this.productId = productId;
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

    /**
     * Gets day in year.
     *
     * @return the day in year
     */
    public int getDayInYear() {
        return dayInYear;
    }

    /**
     * Sets day in year.
     *
     * @param dayInYear the day in year
     */
    public void setDayInYear(int dayInYear) {
        this.dayInYear = dayInYear;
    }
}
