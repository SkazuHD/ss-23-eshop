package org.eshop.ui.frames;

import org.eshop.entities.Customer;
import org.eshop.exceptions.CheckoutFailed;
import org.eshop.shop.Shop;
import org.eshop.ui.models.CartModel;
import org.eshop.ui.models.productEmployeeModel;

import javax.swing.*;
import java.awt.*;

public class CheckOutFrame extends JFrame {
    private JPanel mainPanel;


    private final Shop server;

    public CheckOutFrame(Customer c, Shop shop) {
        server = shop;
        buildUI();

    }
    private void buildUI() {

        mainPanel = new JPanel();
        this.add(mainPanel);
        mainPanel.setBackground(new Color(40));
        pack();
        this.setVisible(true);}
}

