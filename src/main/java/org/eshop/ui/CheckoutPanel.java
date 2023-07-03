package org.eshop.ui;

import org.eshop.entities.User;
import org.eshop.shop.Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckoutPanel extends javax.swing.JPanel {

    private Shop server;
    private User loggedInUser;
    GuiCustomer guiCustomer;

    public CheckoutPanel(Shop shop, User user) {
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