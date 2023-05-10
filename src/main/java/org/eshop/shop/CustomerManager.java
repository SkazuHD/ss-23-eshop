package org.eshop.shop;
import org.eshop.entities.Customer;
import org.eshop.exceptions.CustomerLoginFailed;

import org.eshop.entities.Products;

import java.util.*;

public class CustomerManager{
    Set<Customer> customers = new HashSet<>();

    public CustomerManager() {

    }

    public boolean register(String username, String password, String name, String address) {
        Customer c = new Customer(username, password, name, address);
        return customers.add(c);
    }

    /**
     * Login a Customer
     *  checks if username exists and if password matches
     * @param username
     * @param password
     * @return Customer
     * @throws CustomerLoginFailed
     */
    public Customer login(String username, String password) throws CustomerLoginFailed {
        //Find User in Set
        for (Customer c : customers) {
            if (c.getUsername().equals(username)) {
                //Check if password matches
                if (c.getPassword().equals(password)) {
                    return c;
                }
            }
        }
        throw new CustomerLoginFailed(username);
    }
    float gesamtRechnung;
    //  erstellt ein Array mit dem Namen gekaufteProdListe in dem Producte angelegt werden aber bisher keine drin sind
    Products[] gekaufteProdListe = new Products[0];
    // der Methode einkaufen werden die parameter Products übergeben und der param. anzahl erstellt
    public void einkaufen(Products p, int anzahl) {
        //es wird ein int namens size angelegt in dem die anzahl auf die länge der ProdListe drauf gerechnet wird
        int size = gekaufteProdListe.length + anzahl;
//  // Das array bekommt size als parameter übergeben wodurch die Größe variabel ist und nicht auf eine größe begrenzt
        Products[] neu = new Products[size];
        //Array wird mit jedem Produkt größer?
        if (gekaufteProdListe.length > 0) {
            for (int i = 0; i < gekaufteProdListe.length; i++) {
                neu[i] = gekaufteProdListe[i];
            }
        }
//Product wird dem Array hinzugefügt?
        for (int i = gekaufteProdListe.length; i < neu.length; i++)
            neu[i] = p;

        gekaufteProdListe = neu;

        double preis = p.getPrice();

// Rechnung wird erstellt
        double gesamt = preis * anzahl;
        gesamtRechnung += gesamt;
    }
    public void loeschen(Products p, int anzahl) {
        int size = gekaufteProdListe.length + anzahl;

        Products[] neu = new Products[size];
        if (anzahl < gekaufteProdListe.length) {
            for (int i = 0; i > gekaufteProdListe.length; i--) {
                neu[i] = gekaufteProdListe[i];
            }
        }
        for (int i = gekaufteProdListe.length; i > neu.length; i--)
            neu[i] = p;

        gekaufteProdListe = neu;

    }
}
