package org.eshop.ui;

import org.eshop.entities.User;
import org.eshop.exceptions.LoginFailed;
import org.eshop.shop.Shop;
import org.eshop.ui.components.SearchWidget;
import org.eshop.ui.frames.LoginFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerMenu extends javax.swing.JPanel {
    private final javax.swing.JMenuBar jMenuBar =
            new javax.swing.JMenuBar();
    private final javax.swing.JButton User =
            new javax.swing.JButton("User");
    private final javax.swing.JFormattedTextField Search =
            new javax.swing.JFormattedTextField("Search");
    private final javax.swing.JMenuBar Menu =
            new javax.swing.JMenuBar();

    private final javax.swing.JButton Produkte =
            new javax.swing.JButton("Produkte");

    private final javax.swing.JButton Warenkorb =
            new javax.swing.JButton("Warenkorb");

    private final javax.swing.JButton Logout =
            new javax.swing.JButton("Logout");

    Shop shop;
    User loggedInUser;
    GuiCustomer guiCustomer;
    LoginFrame loginFrame;


    public CustomerMenu(Shop shop, SearchWidget.SearchListener searchListener, GuiCustomer guiCustomer) {
        this.guiCustomer = guiCustomer;
        buildUI(searchListener);
        setupEvents();
    }

    private void buildUI(SearchWidget.SearchListener searchListener) {
        this.add(jMenuBar);
        this.setBackground(new Color(50));

        jMenuBar.setBackground(new Color(50));
        jMenuBar.setBorderPainted(true);
        jMenuBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        jMenuBar.add(Menu);

        SearchWidget search = new SearchWidget(shop, searchListener);
        search.setBackground(new Color(50));
        Menu.add(search);

        Menu.add(Produkte, BorderLayout.LINE_START);
        Menu.setBackground(new Color(50));
        Menu.setBorder(new EmptyBorder(10, 10, 10, 10));

        Menu.add(Warenkorb);
        Warenkorb.setActionCommand("shoppingCart");
        Warenkorb.addActionListener(guiCustomer);

        Menu.add(Logout);

        Search.setPreferredSize(new Dimension(300, 10));
    }

    private void setupEvents() {
        Logout.addActionListener((e) -> {
            loggedInUser.logout();
        });
    }
}
