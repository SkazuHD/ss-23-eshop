package org.eshop.ui.panels;

import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.GuiCustomer;

import javax.swing.*;
import java.awt.*;

public class CheckoutPanel extends javax.swing.JPanel {

    GuiCustomer guiCustomer;
    private final ShopFacade server;
    private final User loggedInUser;

    public CheckoutPanel(ShopFacade shop, User user) {
        server = shop;
        loggedInUser = user;
        setupUI();
    }

    private void setupUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(200, 500));
        //TODO invoice ausgeben
    }
}