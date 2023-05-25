package org.eshop.entities;

public class MassProducts extends Products {

    int packsize;

    public MassProducts (int pNr, double price, String name, int quantity, int packsize){
        super(pNr, price, name, quantity);
        this.packsize = packsize;
    }

    public int getPacksize() {
        return packsize;
    }

    public void setPacksize(int packsize) {
        this.packsize = packsize;
    }
}
