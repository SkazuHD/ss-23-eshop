package org.eshop.entities;

public class Products {
    //Attribute  Price, Nem und Producktnumber für das Product
      private int Productnumber;
      private double Price;

      private String name;
      private int quantity;



    //constructor für die Attribute

    public Products (int pNr, double price, String name , int quantity){
       Productnumber = pNr;
       Price = price;
       this.name = name;
       this.quantity = quantity;

    }
    // Getter und setter für die Attribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getProductnumber() {
        return Productnumber;
    }

    public void setProductnumber(int productnumber) {
        Productnumber = productnumber;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return ("name: " + name +"Productnumber: " + Productnumber+ " / Price: " + Price );}
}
