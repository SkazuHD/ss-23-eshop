package org.eshop.ui.panels;

import org.eshop.entities.MassProducts;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.shop.Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editProductPanel extends JPanel implements ActionListener{
    private final Shop server;
    private final User loggedInUser;
    private final JButton saveBtn = new JButton("SAVE");
    private final JButton deleteBtn = new JButton("DELETE");

    private final JTextField productId = new JTextField();
    private final JTextField productName = new JTextField();
    private final JTextField productQuantity = new JTextField();

    private final JCheckBox massProduct = new JCheckBox("Mass Product");
    private final JTextField productPacksize = new JTextField();

    public editProductPanel(Shop shop, User loggedInUser){
        this.server = shop;
        this.loggedInUser = loggedInUser;
        setupUI();
        setupEvents();
    }

    private void setupUI(){
        Dimension inputMaxSize = new Dimension(300, 25);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(500, 500));

        this.add(new JLabel("ID"));
        this.add(productId);
        productId.setMaximumSize(inputMaxSize);
        this.add(new JLabel("NAME"));
        this.add(productName);
        productName.setMaximumSize(inputMaxSize);
        this.add(new JLabel("QUANTIY"));
        this.add(productQuantity);
        productQuantity.setMaximumSize(inputMaxSize);
        this.add(massProduct);
        this.add(new JLabel("PACKSIZE"));
        this.add(productPacksize);
        productPacksize.setMaximumSize(inputMaxSize);
        JPanel btns = new JPanel();
        btns.setLayout(new FlowLayout(FlowLayout.LEFT));
        btns.add(saveBtn);
        btns.add(deleteBtn);
        this.add(btns);
    }
    private void setupEvents(){
        massProduct.addActionListener(this);
    }

    public void onChange(Products p){
        //TODO Create Eventlistener that passes the selected product
        productId.setText(String.valueOf(p.getId()));
        productName.setText(p.getName());
        productQuantity.setText(String.valueOf(p.getQuantity()));
        if (p instanceof MassProducts mp){
            if (!massProduct.isSelected()) massProduct.doClick();
            productPacksize.setText(String.valueOf(mp.getPacksize()));
        }else {
            productPacksize.setText("");
            if(massProduct.isSelected())massProduct.doClick();
        }
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
        }
    }
}
