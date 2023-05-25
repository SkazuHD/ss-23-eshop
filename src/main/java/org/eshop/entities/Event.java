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
    int dayInYear;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDayInYear() {
        return dayInYear;
    }

    public void setDayInYear(int dayInYear) {
        this.dayInYear = dayInYear;
    }
}
