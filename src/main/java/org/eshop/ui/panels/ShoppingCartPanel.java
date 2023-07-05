package org.eshop.ui.panels;

import org.eshop.entities.Customer;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.frames.CheckOutFrame;
import org.eshop.ui.tables.TableListener;
import org.eshop.ui.tables.models.CartModel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;


public class ShoppingCartPanel extends JPanel implements TableListener {

    private final JButton checkoutButton = new JButton("Checkout");
    private final Shop server;
    private final User loggedInUser;
    private final JTable shoppingCart = new JTable();


    public ShoppingCartPanel(Shop shop, User user) {
        server = shop;
        loggedInUser = user;
        setupUI();
        setupEvents();
    }

    private void setupUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(200, 500));
        this.add(shoppingCart);
        this.add(checkoutButton);
    }

    private void setupEvents() {
        checkoutButton.addActionListener((actionEvent) -> {
            System.out.println("Checkout");
            new CheckOutFrame((Customer) loggedInUser, server);
        });
    }

    @Override
    public void updateCart() {
        Map<Products, Integer> GetCart = server.getCart((Customer) loggedInUser);
        shoppingCart.setModel(new CartModel(GetCart));

    }

    @Override
    public void editProduct(Products p) {
        
    }


    @Override
    public void viewGraph() {

    }

}

