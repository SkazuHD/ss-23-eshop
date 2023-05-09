package org.eshop.entities;

public class Products {
    //Attribute  Price, Nem und Producktnumber für das Product
      private int Productnumber;
      private int Price;

      private String name;
    //constructor für die Attribute

    public Products (int pNr, int price, String name ){
       Productnumber = pNr;
       Price = price;
       this.name = name;

    }
    // Getter und setter für die Attribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getProductnumber() {
        return Productnumber;
    }

    public void setProductnumber(int productnumber) {
        Productnumber = productnumber;
    }


    public String toString() {
        return ("name: " + name +"Productnumber: " + Productnumber+ " / Price: " + Price );}
}
