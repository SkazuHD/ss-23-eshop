package org.eshop.ui;

import org.eshop.entities.Products;
import org.eshop.entities.Customer;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.shop.ProductManager;
import org.eshop.shop.CustomerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShoppingCartPanel extends JPanel{

    private JButton checkoutButton = new JButton("Checkout");
    private Shop server;
    private User loggedInUser;
    private javax.swing.JList Waren =
            new javax.swing.JList<Customer>();

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
        this.add(Waren);
        // Waren.setListData(server.getCart(c).toArray());
        //TODO Waren als Liste ausgeben
        this.add(checkoutButton);
        // checkoutButton.setActionCommand("checkoutPanel");
        // checkoutButton.addActionListener(guiCustomer);
        //TODO Button nutzbar machen

    }

}