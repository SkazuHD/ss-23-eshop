package org.eshop.ui;


import org.eshop.entities.Employee;
import org.eshop.entities.Products;
import org.eshop.shop.EmployeeManager;
import org.eshop.shop.Shop;
import org.eshop.ui.components.SearchWidget;

import java.util.List;

public class MitarbeiterPanel extends javax.swing.JPanel implements SearchWidget.SearchListener {
    private final javax.swing.JList Mitarbeiter =
            new javax.swing.JList<Employee>();
    private final javax.swing.JPanel panel =
            new javax.swing.JPanel();
    EmployeeManager employeeManager;
    Shop shop;

    public MitarbeiterPanel(Shop shop, GuiEmployee guiEmployee) {
        this.add(Mitarbeiter);


        Mitarbeiter.setListData(shop.getAllEmployees().toArray());


    }


    @Override
    public void onSearch(List<Products> result) {

    }


}
