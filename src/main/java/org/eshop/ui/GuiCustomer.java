package org.eshop.ui;

import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCustomer extends javax.swing.JFrame implements ActionListener {
    Shop shop;
    CustomerMenu customerMenu;
    SidePanel sidePanel;
    ShoppingCartPanel shoppingCart;
    CheckoutPanel checkoutPanel;
    ProductPanel productPanel;

    public GuiCustomer(Shop shop, User loggedInUser, CustomerMenu.addLogoutListener logoutListener) {
        this.shop = shop;
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        shoppingCart = new ShoppingCartPanel(shop, loggedInUser);
        productPanel = new CustomerCenterPanel(shop, shoppingCart, loggedInUser);
        checkoutPanel = new CheckoutPanel(shop, loggedInUser);
        customerMenu = new CustomerMenu(shop, productPanel, this, logoutListener, loggedInUser);

        sidePanel = new SidePanel(shop, loggedInUser);
        sidePanel.add(shoppingCart);
        sidePanel.add(checkoutPanel);

        shoppingCart.setVisible(true);
        checkoutPanel.setVisible(false);

        this.add(sidePanel, BorderLayout.EAST);
        this.add(new JScrollPane(productPanel), BorderLayout.CENTER);

        this.add(customerMenu, BorderLayout.PAGE_START);


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
