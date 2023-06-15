package org.eshop.ui;

import org.eshop.entities.Products;
import org.eshop.shop.Shop;

public class ProducktPanel extends javax.swing.JPanel {
    private javax.swing.JList Produkte =
            new javax.swing.JList<Products>();
    Shop shop;
    //GuiEmployee guiEmployee;
    //GuiEmployee jPanel1;

    public ProducktPanel (Shop shop){
        //this.guiEmployee = guiEmployee;


        this.add(Produkte);
        Produkte.setListData(shop.getProductSet().toArray());
}}