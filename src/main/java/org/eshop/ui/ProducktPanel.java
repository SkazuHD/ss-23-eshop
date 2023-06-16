package org.eshop.ui;

import org.eshop.entities.Products;
import org.eshop.shop.Shop;
import org.eshop.ui.components.SearchWidget;

import java.util.List;

public class ProducktPanel extends javax.swing.JPanel implements SearchWidget.SearchListener {
    private javax.swing.JList Produkte =
            new javax.swing.JList<Products>();
    Shop shop;
    //GuiEmployee guiEmployee;
    //GuiEmployee jPanel1;

    public ProducktPanel (Shop shop){
        //this.guiEmployee = guiEmployee;


        this.add(Produkte);
        Produkte.setListData(shop.getProductSet().toArray());

    }
    public void onSearch(List<Products> result){
        Produkte.setListData(result.toArray());
    };
}