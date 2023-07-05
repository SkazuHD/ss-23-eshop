package org.eshop.ui;

import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.panels.CheckoutPanel;
import org.eshop.ui.panels.ProducktPanel;
import org.eshop.ui.panels.ShoppingCartPanel;
import org.eshop.ui.panels.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCustomer extends javax.swing.JFrame implements ActionListener {
    private final javax.swing.JPanel panel = new javax.swing.JPanel();
    private final javax.swing.JScrollPane scrollPane =
            new javax.swing.JScrollPane();
    private final javax.swing.JPanel panelCenter =
            new javax.swing.JPanel();

    Shop shop;
    CustomerMenu customerMenu;
    SidePanel sidePanel;
    ShoppingCartPanel shoppingCart;
    CheckoutPanel checkoutPanel;
    ProducktPanel productPanel;

    public GuiCustomer(Shop shop, User loggedInUser) {
        this.shop = shop;
        panel.setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        shoppingCart = new ShoppingCartPanel(shop, loggedInUser);
        productPanel = new ProducktPanel(shop, shoppingCart, loggedInUser);
        panel.add(new JScrollPane(panelCenter), BorderLayout.CENTER);
        panelCenter.add(productPanel);

        sidePanel = new SidePanel(shop, loggedInUser);
        checkoutPanel = new CheckoutPanel(shop, loggedInUser);
        sidePanel.add(shoppingCart);
        sidePanel.add(checkoutPanel);
        shoppingCart.setVisible(true);
        checkoutPanel.setVisible(false);

        panel.add(sidePanel, BorderLayout.EAST);

        customerMenu = new CustomerMenu(shop, productPanel, this);
        panel.add(customerMenu, BorderLayout.PAGE_START);

        this.getContentPane().add(panel);
        pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        shoppingCart.setVisible(false);
        checkoutPanel.setVisible(false);
        switch (e.getActionCommand()) {
            case "shoppingCart":
                shoppingCart.setVisible(true);
                checkoutPanel.setVisible(false);

                break;
            case "checkoutPanel":
                checkoutPanel.setVisible(true);
                shoppingCart.setVisible(false);

                break;

        }


    }

}
