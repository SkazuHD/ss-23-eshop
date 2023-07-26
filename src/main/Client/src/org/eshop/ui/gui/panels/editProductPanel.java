package org.eshop.ui.gui.panels;

import org.eshop.entities.MassProduct;
import org.eshop.entities.Product;
import org.eshop.entities.User;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.PacksizeNotMatching;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.gui.tables.TableListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editProductPanel extends JPanel implements ActionListener, TableListener {
    private final ShopFacade server;
    private final User loggedInUser;
    private final JButton saveBtn = new JButton("SAVE");
    private final JButton deleteBtn = new JButton("DELETE");

    private final JTextField productId = new JTextField();
    private final JTextField productName = new JTextField();
    private final JTextField productQuantity = new JTextField();
    private final JTextField productPrice = new JTextField();

    private final JCheckBox massProduct = new JCheckBox("Mass Product");
    private final JTextField productPacksize = new JTextField();
    private final addProductPanel addProductPanel;
    private Product currentProduct;

    public editProductPanel(ShopFacade shop, User loggedInUser, addProductPanel addProductPanel) {
        this.server = shop;
        this.loggedInUser = loggedInUser;
        this.addProductPanel = addProductPanel;
        setupUI();
        setupEvents();
    }

    private void setupUI() {
        Dimension inputMaxSize = new Dimension(300, 25);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //this.setLayout(new GridLayout(2,1));
        this.setPreferredSize(new Dimension(300, 500));
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        this.add(new JLabel("ID"));
        this.add(productId);
        productId.setEnabled(false);
        productId.setMaximumSize(inputMaxSize);
        productId.setPreferredSize(inputMaxSize);
        this.add(new JLabel("NAME"));
        this.add(productName);
        productName.setMaximumSize(inputMaxSize);
        this.add(new JLabel("QUANTIY"));
        this.add(productQuantity);
        productQuantity.setMaximumSize(inputMaxSize);
        this.add(new JLabel("PRICE"));
        this.add(productPrice);
        productPrice.setMaximumSize(inputMaxSize);
        this.add(massProduct);
        massProduct.setEnabled(false);
        this.add(new JLabel("PACKSIZE"));
        this.add(productPacksize);
        productPacksize.setEnabled(false);
        productPacksize.setMaximumSize(inputMaxSize);
        JPanel btns = new JPanel();
        btns.setLayout(new FlowLayout(FlowLayout.CENTER));
        btns.add(saveBtn);
        btns.add(deleteBtn);
        //this.add(container);
        this.add(btns);
    }

    private void setupEvents() {
        massProduct.addActionListener(this);
        saveBtn.addActionListener(this);
    }

    public void onChange(Product p) {
        currentProduct = p;
        //TODO Create Eventlistener that passes the selected product
        productId.setText(String.valueOf(p.getId()));
        productName.setText(p.getName());
        productQuantity.setText(String.valueOf(p.getQuantity()));
        productPrice.setText(String.valueOf(p.getPrice()));
        if (p instanceof MassProduct mp) {
            if (!massProduct.isSelected()) massProduct.doClick();
            productPacksize.setText(String.valueOf(mp.getPacksize()));
        } else {
            productPacksize.setText("");
            if (massProduct.isSelected()) massProduct.doClick();
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
        } else if (e.getSource().equals(deleteBtn)) {
            //TODO QOL add method to remove Product
        } else if (e.getSource().equals(saveBtn)) {
            int quantityDiffrence = -currentProduct.getQuantity() + Integer.parseInt(productQuantity.getText());
            Product p;
            try {
                server.changeQuantity(currentProduct.getId(), quantityDiffrence, loggedInUser);
                if (currentProduct instanceof MassProduct mp && massProduct.isSelected()) {
                    p = server.editProductDetails(currentProduct.getId(), productName.getText(),
                            Double.parseDouble(productPrice.getText()),
                            Integer.parseInt(productPacksize.getText()));
                } else if (!(currentProduct instanceof MassProduct mp && massProduct.isSelected())) {
                    p = server.editProductDetails(currentProduct.getId(), productName.getText(), Double.parseDouble(productPrice.getText()));
                } else {
                    //TODO QOL CAST NORMAL TO MASS etc
                }
            } catch (PacksizeNotMatching | NotInStockException | ProductNotFound exp) {
                JOptionPane.showMessageDialog(new JFrame(), exp.getMessage());
            }
            this.setVisible(false);
            addProductPanel.setVisible(true);

        }

    }

    @Override
    public void updateCart() {

    }

    @Override
    public void editProduct(Product p) {
        onChange(p);
        addProductPanel.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void viewGraph() {

    }
}
