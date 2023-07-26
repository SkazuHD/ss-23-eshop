package org.eshop.ui.gui;

import org.eshop.entities.User;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.gui.listener.ShopCloseListener;
import org.eshop.ui.gui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCustomer extends javax.swing.JFrame implements ActionListener {
    ShopFacade shop;
    CustomerMenu customerMenu;
    SidePanel sidePanel;
    ShoppingCartPanel shoppingCart;
    CheckoutPanel checkoutPanel;
    ProductPanel productPanel;

    public GuiCustomer(ShopFacade shop, User loggedInUser, CustomerMenu.addLogoutListener logoutListener) {
        this.shop = shop;
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new ShopCloseListener(shop));
        shoppingCart = new ShoppingCartPanel(shop, loggedInUser);
        productPanel = new CustomerCenterPanel(shop, shoppingCart, loggedInUser);
        checkoutPanel = new CheckoutPanel();
        customerMenu = new CustomerMenu(shop, productPanel, this, logoutListener, loggedInUser);

        sidePanel = new SidePanel();
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
            case "shoppingCart" -> {
                shoppingCart.setVisible(true);
                checkoutPanel.setVisible(false);
            }
            case "checkoutPanel" -> {
                checkoutPanel.setVisible(true);
                shoppingCart.setVisible(false);
            }
        }


    }

}
