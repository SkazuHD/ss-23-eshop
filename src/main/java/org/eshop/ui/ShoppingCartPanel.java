package org.eshop.ui;

import org.eshop.shop.Shop;

import java.awt.*;

public class ShoppingCartPanel extends javax.swing.JPanel {

    Shop shop;

    public ShoppingCartPanel (Shop shop) {
        this.setPreferredSize(new Dimension(400,20));
        this.setBackground(new Color(40));
        this.setVisible(true);
    }
}
