package org.eshop.ui.panels;

import org.eshop.entities.MassProducts;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.PacksizeNotMatching;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.shop.Shop;
import org.eshop.shop.ShopFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editProductPanel extends JPanel implements ActionListener{
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
    private Products currentProduct;

    public editProductPanel(ShopFacade shop, User loggedInUser){
        this.server = shop;
        this.loggedInUser = loggedInUser;
        setupUI();
        setupEvents();
    }

    private void setupUI(){
        Dimension inputMaxSize = new Dimension(300, 25);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setLayout(new GridLayout(2,1));
        this.setPreferredSize(new Dimension(500, 500));
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container,  BoxLayout.PAGE_AXIS));
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
    private void setupEvents(){
        massProduct.addActionListener(this);
        saveBtn.addActionListener(this);
    }

    public void onChange(Products p){
        currentProduct = p;
        //TODO Create Eventlistener that passes the selected product
        productId.setText(String.valueOf(p.getId()));
        productName.setText(p.getName());
        productQuantity.setText(String.valueOf(p.getQuantity()));
        productPrice.setText(String.valueOf(p.getPrice()));
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
        //* DEBUG STUFF
        System.out.println(productName.getText());


        if (e.getSource().equals(massProduct)) {
            if (massProduct.isSelected()) {
                productPacksize.setEnabled(true);
            } else {
                productPacksize.setEnabled(false);
                productPacksize.setText("");
            }
         }else if(e.getSource().equals(deleteBtn)){
            //Todo add method to remove Product
        }else if(e.getSource().equals(saveBtn)){
            System.out.println("SAVING PROD FROM EDIT");
            int quantityDiffrence = -currentProduct.getQuantity() + Integer.parseInt(productQuantity.getText());
            Products p = null;
            try {
                server.changeQuantity(currentProduct.getId(), quantityDiffrence, loggedInUser);
                if(currentProduct instanceof MassProducts mp && massProduct.isSelected()){
                    p = server.editProductDetails(currentProduct.getId(), productName.getText(),
                            Double.parseDouble(productPrice.getText()),
                            Integer.parseInt(productPacksize.getText()));
                }else if(!(currentProduct instanceof MassProducts mp && massProduct.isSelected())) {
                   p = server.editProductDetails(currentProduct.getId(), productName.getText(),Double.parseDouble(productPrice.getText()));
                }else {
                    //TODO CAST NORMAL TO MASS etc
                }
            }catch (PacksizeNotMatching | NotInStockException | ProductNotFound exp){
                JOptionPane.showMessageDialog(new JFrame(), exp.getMessage());
            }
            System.out.println(p);

        }

    }
}
