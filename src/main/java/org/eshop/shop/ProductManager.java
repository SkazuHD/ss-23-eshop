package org.eshop.shop;

import org.eshop.entities.Products;

// producte hinzufügen
// löschen
// Mitarbeiter soll den bestand ändern
// exeption
//product wird gekauft





public class ProductManager {
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

        float preis = p.getPrice();

// Rechnung wird erstellt
        float gesamt = preis * anzahl;
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