package org.eshop.ui.panels;

import org.eshop.entities.Customer;
import org.eshop.entities.User;
import org.eshop.shop.CustomerManager;
import org.eshop.shop.Shop;
import org.eshop.ui.GuiCustomer;

import javax.swing.*;
import java.awt.*;


public class ShoppingCartPanel extends JPanel {

    CustomerManager customerManager;
    Customer c;
    GuiCustomer guiCustomer;
    private final JButton checkoutButton = new JButton("Checkout");
    private final Shop server;
    private final User loggedInUser;
    private final javax.swing.JList Waren =
            new javax.swing.JList<Customer>();

    public ShoppingCartPanel(Shop shop, User user) {
        server = shop;
        loggedInUser = user;
        setupUI();
    }

    private void setupUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(200, 500));
        this.add(Waren);
        // Waren.setListData(server.getCart(c).toArray());
        //TODO Waren als Liste ausgeben
        this.add(checkoutButton);
        // checkoutButton.setActionCommand("checkoutPanel");
        // checkoutButton.addActionListener(guiCustomer);
        //TODO Button nutzbar machen

    }

}