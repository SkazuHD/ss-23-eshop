package org.eshop.ui;

import org.eshop.entities.User;
import org.eshop.shop.Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShoppingCartPanel extends JPanel{

    private final JTextField productName = new JTextField();
    //TODO SHOULD BE REPLACED WITH CUSTOM JNumberField for Input Validation
    private final JTextField productQuantity = new JTextField();
    private final JButton checkoutButton = new JButton("Checkout");
    private Shop server;
    private User loggedInUser;
    GuiCustomer guiCustomer;

    public ShoppingCartPanel(Shop shop, User user) {
        server = shop;
        loggedInUser = user;
        setupUI();
    }

    private void setupUI() {
        Dimension inputMaxSize = new Dimension(300, 25);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(200, 500));
        productName.setPreferredSize(inputMaxSize);
        productName.setMaximumSize(inputMaxSize);
        this.add(new JLabel("Name:"));
        this.add(productName);
        productQuantity.setPreferredSize(inputMaxSize);
        productQuantity.setMaximumSize(inputMaxSize);
        this.add(new JLabel("Quantity"));
        this.add(productQuantity);
        this.add(checkoutButton);

    }

}