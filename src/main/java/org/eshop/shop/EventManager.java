package org.eshop.shop;

import org.eshop.entities.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    // Manges Product events
    Map<Integer, List<Event>> productEvents;

    public EventManager() {
        productEvents = new HashMap<>();
    }

    public void addEvent(Event event) {
        if (productEvents.containsKey(event.getProductId())) {
            //  productEvents.get(event.getProductId()).add(event);
        } else {
            productEvents.put(event.getProductId(), List.of(event));
        }


    }


}
