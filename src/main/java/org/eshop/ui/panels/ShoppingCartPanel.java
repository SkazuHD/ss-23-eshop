package org.eshop.ui.panels;

import org.eshop.entities.Customer;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.shop.CustomerManager;
import org.eshop.shop.Shop;
import org.eshop.ui.CustomerProductTable;
import org.eshop.ui.GuiCustomer;
import org.eshop.ui.models.CartModel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;


public class ShoppingCartPanel extends JPanel implements CustomerProductTable.tableButtonListener {

    private final JButton checkoutButton = new JButton("Checkout");
    private final Shop server;
    private final User loggedInUser;
    private final JTable shoppingCart = new JTable();

    CustomerManager customerManager;
    Customer c;
    GuiCustomer guiCustomer;

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
            JFrame frame = new JFrame("CHECK ME OUT");
            frame.setVisible(true);
        });
    }

    @Override
    public void updateCart() {
        Map<Products, Integer> GetCart = server.getCart((Customer) loggedInUser);
        shoppingCart.setModel(new CartModel(GetCart));

    }

}

