package org.eshop.entities;

public class MassProduct extends Product {

    int packsize;

    public MassProduct(int id, double price, String name, int quantity, int packsize){
        super(id, price, name, quantity);
        this.packsize = packsize;
    }

    public int getPacksize() {
        return packsize;
    }

    public void setPacksize(int packsize) {
        this.packsize = packsize;
    }
}
