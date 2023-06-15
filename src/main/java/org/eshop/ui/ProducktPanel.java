package org.eshop.ui;

import org.eshop.entities.Products;
import org.eshop.shop.Shop;

public class ProducktPanel extends javax.swing.JFrame {
    private javax.swing.JList Produkte =
            new javax.swing.JList<Products>();
    Shop shop;
GuiEmployee guiEmployee;
GuiEmployee jPanel1;

    public ProducktPanel (GuiEmployee guiEmployee){
        this.guiEmployee = guiEmployee;

        jPanel1.add(Produkte,java.awt.BorderLayout.CENTER);
        Produkte.setListData(shop.getProductSet().toArray());
}}