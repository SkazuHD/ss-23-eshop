package org.eshop.ui;
import org.eshop.entities.Products;
import org.eshop.shop.Shop;

import javax.swing.*;
import java.awt.*;

public class GuiCustomer extends javax.swing.JFrame {
    private javax.swing.JPanel panel = new javax.swing.JPanel();

    Shop shop;
    CustomerMenu customerMenu;
    ShoppingCartPanel shoppingCart;
    ProducktPanel productPanel;

    private javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane();

    public GuiCustomer(Shop shop) {
        this.shop = shop;
        panel.setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        productPanel = new ProducktPanel(shop);
        panel.add(productPanel, BorderLayout.CENTER);

        shoppingCart = new ShoppingCartPanel(shop);
        panel.add(shoppingCart, BorderLayout.EAST);
        customerMenu = new CustomerMenu(shop, productPanel);
        panel.add(customerMenu, BorderLayout.PAGE_START);

        this.getContentPane().add(panel);
        pack();
        this.setVisible(true);
    }

}
