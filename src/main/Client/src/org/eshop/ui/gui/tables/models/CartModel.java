package org.eshop.ui.gui.tables.models;


import org.eshop.entities.Product;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;

public class CartModel extends AbstractTableModel {

    Map<Product, Integer> cart;
    List<Product> prods;

    public CartModel(Map<Product, Integer> cart) {
        this.cart = cart;
        prods = cart.keySet().stream().toList();

        updateCart();
        fireTableDataChanged();
    }

    public void updateCart() {
    }

    @Override
    public int getRowCount() {
        return cart.size();
    }


    @Override
    public int getColumnCount() {
        return 4;
    }


    @Override
    public Object getValueAt(int row, int col) {
        Product p = prods.get(row);
        return switch (col) {
            case 0 -> p.getId();
            case 1 -> p.getName();
            case 2 -> p.getPrice();
            case 3 -> cart.get(p);
            
            default -> null;
        };
    }
}



