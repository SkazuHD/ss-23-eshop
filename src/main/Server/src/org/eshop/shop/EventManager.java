package org.eshop.shop;

import org.eshop.entities.Event;
import org.eshop.entities.Product;
import org.eshop.entities.User;
import org.eshop.persistence.FileManager;
import org.eshop.persistence.ShopPersistence;
import java.time.LocalDate;
import java.util.*;

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
    public void addEvent(User user, Product p, int quantity) {
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
    public Collection<Event> getAllEvents(){
        List<Event> allEvents = new ArrayList<>();
        productEvents.forEach((k, v) -> allEvents.addAll(v));
        return allEvents;
    }

    public int[] getProductHistory(int productId, int days, int currentStock) {
        List<Event> allEvents = productEvents.get(productId);
        //Return current Stock if no Events are found
        if (allEvents == null || allEvents.isEmpty()){
            int[] history = new int[days];
            Arrays.fill(history, currentStock);
            return history;
        }
        //Filter Events by Date
        allEvents.removeIf(event -> event.getDayInYear() <= (LocalDate.now().getDayOfYear() - days));

        //Create Array with size of days
        int[] history = new int[days];

        history[0] = currentStock;
        System.out.println("Day 0 " + history[0]);
        System.out.println(LocalDate.now().getDayOfYear());
        //Iterate over Events and add or subtract quantity
        for(int i = 1; i < days; i++){
            int sum = 0;
            int currentDayToLookAt = LocalDate.now().getDayOfYear() + 1 - i;
            for (Event event : allEvents){
                if (event.getDayInYear() == currentDayToLookAt){
                    sum += event.getQuantity();
                }
            }
            history[i] = history[i-1] - sum;
        }
        //REVERSE THE ORDER
        int[] reversed = new int[history.length];
        for(int i = 0; i<history.length; i++){
            reversed[history.length-1-i] = history[i];
        }
        return reversed;
    }


}
