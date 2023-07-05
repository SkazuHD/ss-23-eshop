package org.eshop.ui.models;


import org.eshop.entities.Products;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class CartModel  extends AbstractTableModel {

    Map<Products, Integer> cart;
    List<Products> prods;

    public  CartModel(Map<Products, Integer> cart){
        this.cart = cart;
        prods = cart.keySet().stream().toList();

        updateCart();
        fireTableDataChanged();
    }

    public void updateCart(){
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
            Products p = prods.get(row);
            switch (col) {
                case 0:
                    return p.getId();
                case 1:
                    return p.getName();
                case 2:
                    return p.getPrice();
                case 3:
                    return cart.get(p);
                case 4:
                    return null;
                default:
                    return null;
            }
        }
    }



