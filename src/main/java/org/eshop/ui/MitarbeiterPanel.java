package org.eshop.ui;


import org.eshop.entities.Employee;
import org.eshop.entities.Product;
import org.eshop.shop.Shop;
import org.eshop.ui.components.SearchWidget;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class MitarbeiterPanel extends javax.swing.JPanel implements SearchWidget.SearchListener {

    Shop shop;

    public MitarbeiterPanel(Shop shop, GuiEmployee guiEmployee) {
        this.shop = shop;
        JList<Employee> employees = new JList<>();
        this.add(employees);


        employees.setListData(new Vector<>(shop.getAllEmployees()));


    }


    @Override
    public void onSearch(List<Product> result) {

    }


}
