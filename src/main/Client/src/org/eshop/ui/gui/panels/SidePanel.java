package org.eshop.ui.gui.panels;

import org.eshop.entities.User;
import org.eshop.shop.ShopFacade;

import java.awt.*;

public class SidePanel extends javax.swing.JPanel {
    ShopFacade shop;

    public SidePanel(ShopFacade shop, User user) {
        //this.setPreferredSize(new Dimension(400,20));
        this.setBackground(new Color(40));
        this.setVisible(true);

    }
}
