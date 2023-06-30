package org.eshop.ui;
import org.eshop.shop.Shop;
import org.eshop.ui.components.SearchWidget;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomerMenu extends javax.swing.JPanel {
    private javax.swing.JMenuBar jMenuBar =
            new javax.swing.JMenuBar();
    private javax.swing.JButton User =
            new javax.swing.JButton( "User" );
    private javax.swing.JFormattedTextField Search =
            new javax.swing.JFormattedTextField("Search");
    private javax.swing.JMenuBar Menu =
            new javax.swing.JMenuBar();

    private javax.swing.JButton Produkte =
            new javax.swing.JButton("Produkte");

    private javax.swing.JButton Warenkorb =
            new javax.swing.JButton("Warenkorb");

    private javax.swing.JButton Logout =
            new  javax.swing.JButton("Logout");

    Shop shop;

    public CustomerMenu(Shop shop, SearchWidget.SearchListener searchListener) {
        this.add(jMenuBar);
        this.setBackground(new Color(50));

        jMenuBar.setBackground(new Color(50));
        jMenuBar.setBorderPainted(true);
        jMenuBar.setBorder(new EmptyBorder(10,10,10,10));
        jMenuBar.add(Menu);

        SearchWidget search = new SearchWidget(shop, searchListener);
        search.setBackground(new Color(50));
        Menu.add(search);

        Menu.add(Produkte,BorderLayout.LINE_START);
        Menu.setBackground(new Color(50));
        Menu.setBorder(new EmptyBorder(10,10,10,10));
        Menu.add(Warenkorb);
        Menu.add(Logout);

        Search.setPreferredSize(new Dimension(300,10));
    }
}
