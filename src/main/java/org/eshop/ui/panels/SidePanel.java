package org.eshop.ui.panels;

import org.eshop.entities.User;
import org.eshop.shop.Shop;

import java.awt.*;

public class SidePanel extends javax.swing.JPanel {
    Shop shop;

    public SidePanel(Shop shop, User user) {
        //this.setPreferredSize(new Dimension(400,20));
        this.setBackground(new Color(40));
        this.setVisible(true);

    }
}
