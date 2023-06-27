package org.eshop.ui;

import org.eshop.entities.Employee;
import org.eshop.entities.Products;
import org.eshop.shop.Shop;
import org.eshop.ui.components.SearchWidget;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProducktPanel extends javax.swing.JPanel implements SearchWidget.SearchListener {

    private javax.swing.JList Produkte =
            new javax.swing.JList<Products>();

    private  javax.swing.JPanel pannel =
            new javax.swing.JPanel();

    private javax.swing.JScrollPane scroll =
            new JScrollPane();


    Shop shop;
    //GuiEmployee guiEmployee;
    //GuiEmployee jPanel1;

    public ProducktPanel (Shop shop){



         this.add(pannel);




          pannel.add(Produkte);

        Produkte.setListData(shop.getAllProducts().toArray());

    }
    public void onSearch(List<Products> result){
        Produkte.setListData(result.toArray());
    }



}