package org.eshop.shop;

import org.eshop.entities.Event;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.persistence.FileManager;
import org.eshop.persistence.ShopPersistence;

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
    Map<Integer, List<Event>> productEvents;
    ShopPersistence persistence = new FileManager();

    /**
     * Instantiates a new Event manager.
     */
    public EventManager() {
        productEvents = new HashMap<>();
        loadEvents("events.csv");
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
        try {
            persistence.openForWriting("events.csv", true);

        } catch (Exception e) {
            //TODO handle exception
            return;
        }
        persistence.writeEvent(event);
        persistence.close();

        //Show all Events for Debugging

        productEvents.forEach((k, v) -> v.forEach(System.out::println));
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

    public void loadEvents(String filename) {
        try {
            persistence.openForReading(filename);
        } catch (Exception e) {
            //TODO handle exception
            return;
        }
        Event event;
        do {
            event = persistence.readEvent();
            if (event != null) {
                addEvent(event);
            }
        } while (event != null);
    }

    //TODO IMPLEMENT
    public int[] getProductHistory(){
        return null;
    }
}
