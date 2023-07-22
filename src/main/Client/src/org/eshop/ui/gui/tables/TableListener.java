package org.eshop.ui.gui.tables;

import org.eshop.entities.Product;

public interface TableListener {
    void updateCart();

    void editProduct(Product p);

    void viewGraph();
}
