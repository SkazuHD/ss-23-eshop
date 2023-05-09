package org.eshop.entities;

public class ShoppingCartItem {
    Products product;
    int quantity;

    public ShoppingCartItem(Products product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }
    public String toString() {
        return (product +"|"+quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Products p){
            // return this.product.getName().equals(p.getName());
        }
        return false;
    }
}
