package org.eshop.ui.tables;

import org.eshop.entities.Products;

public interface TableListener {
    void updateCart();

    void editProduct(Products p);

    void viewGraph();
}
