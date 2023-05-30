package org.eshop.shop;

import org.eshop.entities.Event;
import org.eshop.entities.Products;
import org.eshop.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Event manager.
 */
public class EventManager {

    /**
     * The Product events.
     */
// Manges Product events
    Map<Integer, List<Event>> productEvents;

    /**
     * Instantiates a new Event manager.
     */
    public EventManager() {
        productEvents = new HashMap<>();
    }

    /**
     * Add event.
     *
     * @param event the event
     */
    public void addEvent(Event event) {
        if (productEvents.containsKey(event.getProductId())) {
            productEvents.get(event.getProductId()).add(event);
        } else {
            ArrayList<Event> list = new ArrayList<>();
            list.add(event);
            productEvents.put(event.getProductId(), list);
        }

        //Show all Events for Debugging

        productEvents.forEach((k, v) -> {
            v.forEach((e) -> {
                System.out.println(e);
            });
        });
    }

    /**
     * Add event.
     *
     * @param user     the user
     * @param p        the p
     * @param quantity the quantity
     */
    public void addEvent(User user, Products p, int quantity) {
        Event event = new Event(user, p, quantity);
        addEvent(event);
    }


}
