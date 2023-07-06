package org.eshop.ui.panels;

import org.eshop.entities.User;
import org.eshop.shop.Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class addProductPanel extends JPanel implements ActionListener {

    private final JTextField productName = new JTextField();
    //TODO SHOULD BE REPLACED WITH CUSTOM JNumberField for Input Validation
    private final JTextField productQuantity = new JTextField();
    private final JTextField productPrice = new JTextField();
    private final JCheckBox massProduct = new JCheckBox("MassProduct");
    //Set visible when massProduct is checked
    //TODO SHOULD BE REPLACED WITH CUSTOM JNumberField for Input Validation
    private final JTextField productPacksize = new JTextField();
    private final JButton createButton = new JButton("Create");
    private final Shop server;
    private final User loggedInUser;

    public addProductPanel(Shop shop, User user) {
        server = shop;
        loggedInUser = user;
        setupUI();
        setupEvents();
    }

    private void setupUI() {
        Dimension inputMaxSize = new Dimension(300, 25);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(300, 500));
        productName.setPreferredSize(inputMaxSize);
        productName.setMaximumSize(inputMaxSize);
        this.add(new JLabel("Name:"));
        this.add(productName);
        productQuantity.setPreferredSize(inputMaxSize);
        productQuantity.setMaximumSize(inputMaxSize);
        this.add(new JLabel("Quantity"));
        this.add(productQuantity);
        productPrice.setPreferredSize(inputMaxSize);
        productPrice.setMaximumSize(inputMaxSize);
        this.add(new JLabel("Price:"));
        this.add(productPrice);
        this.add(massProduct);
        productPacksize.setPreferredSize(inputMaxSize);
        productPacksize.setMaximumSize(inputMaxSize);
        this.add(new JLabel("Packsize"));
        this.add(productPacksize);
        productPacksize.setEnabled(false);
        this.add(createButton);

        this.setVisible(true);
    }

    private void setupEvents() {
        massProduct.addActionListener(this);
        createButton.addActionListener(this);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(massProduct)) {
            if (massProduct.isSelected()) {
                productPacksize.setEnabled(true);
            } else {
                productPacksize.setEnabled(false);
                productPacksize.setText("");
            }
        } else if (e.getSource().equals(createButton)) {
            boolean success = false;

            try {
                String name = productName.getText();
                int quantity = Integer.parseInt(productQuantity.getText());
                double price = Double.parseDouble(productPrice.getText());
                if (!massProduct.isSelected()) {
                    server.createProduct(name, price, quantity, loggedInUser);
                } else {
                    int packSize = Integer.parseInt(productPacksize.getText());
                    server.createProduct(name, price, quantity, packSize, loggedInUser);
                }
                success = true;
            } catch (Exception ignored) {

            } finally {
                JOptionPane.showMessageDialog(new JFrame(), success ? "HURRAY" : "YOU FUCKED IT UP!", success ? "Succes!" : "FAILURE!", success ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);

            }


        }
    }
}
