package org.eshop.shop;

import org.eshop.entities.Event;

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
            productEvents.put(event.getProductId(), List.of(event));
        }


    }


}
