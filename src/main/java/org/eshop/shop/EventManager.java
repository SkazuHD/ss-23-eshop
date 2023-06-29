package org.eshop.shop;

import org.eshop.entities.Event;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.persistence.FileManager;
import org.eshop.persistence.ShopPersistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
        //productEvents.forEach((k, v) -> v.forEach(System.out::println));
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
            }
        } while (event != null);
    }

    //TODO IMPLEMENT
    public int[] getProductHistory(int productId, int days, int currentStock) {
        List<Event> allEvents = productEvents.get(productId);
        if (allEvents == null || allEvents.isEmpty()) return new int[days];
        //Filter Events by Date
        allEvents.removeIf(event -> event.getDayInYear() < (LocalDate.now().getDayOfYear() - days));
        //Create Array with size of days
        int[] history = new int[days];
        history[0] = currentStock;
        for (int i = 1; i < days; i++) {

            //Get All Events for the day
            AtomicInteger sum = new AtomicInteger();
            int finalI = i;
            allEvents.forEach(event -> {
                //Check if Date Matches;
                if (LocalDate.now().getDayOfYear() - finalI == event.getDayInYear()) {
                    sum.addAndGet(event.getQuantity());
                }
            });
            history[i] = history[i - 1] - sum.get();
        }

        return history;
    }


}
