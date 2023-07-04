package org.eshop.ui.panels;

import org.eshop.entities.Customer;
import org.eshop.entities.User;
import org.eshop.shop.CustomerManager;
import org.eshop.shop.Shop;
import org.eshop.ui.GuiCustomer;

import javax.swing.*;
import java.awt.*;


public class ShoppingCartPanel extends JPanel {

    private final JButton checkoutButton = new JButton("Checkout");
    private final Shop server;
    private final User loggedInUser;
    private final JTable shoppingCart = new JTable();
    CustomerManager customerManager;
    Customer c;
    GuiCustomer guiCustomer;

    public ShoppingCartPanel(Shop shop, User user) {
        server = shop;
        loggedInUser = user;
        setupUI();
    }

    private void setupUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(200, 500));
        //TODO Waren als Liste ausgeben
        this.add(checkoutButton);
        //TODO Button nutzbar machen

    }

}