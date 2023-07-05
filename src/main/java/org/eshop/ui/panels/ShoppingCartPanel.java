package org.eshop.ui.panels;

import org.eshop.entities.Customer;
import org.eshop.ui.*;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.shop.CustomerManager;
import org.eshop.shop.Shop;
import org.eshop.ui.GuiCustomer;
import org.eshop.ui.Table;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;


public class ShoppingCartPanel extends JPanel {

    private final JButton checkoutButton = new JButton("Checkout");
    private final Shop server;
    private final User loggedInUser;
    private final JTable shoppingCart = new JTable();
    CustomerManager customerManager;
    Customer c;
    GuiCustomer guiCustomer;
    Table table;

    public ShoppingCartPanel(Shop shop, User user) {
        server = shop;
        loggedInUser = user;
        setupUI();
    }

    private void setupUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(200, 500));
        this.add(shoppingCart);
        //TODO Waren als Liste ausgeben
        this.add(checkoutButton);
        checkoutButton.setActionCommand("checkoutPanel");
        checkoutButton.addActionListener(guiCustomer);
        //TODO Button nutzbar machen

    }

}