package org.eshop.ui.panels;

import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.CustomerProductTable;
import org.eshop.ui.EmployeeProductTable;

import javax.swing.*;
import java.util.List;

public class EmployeeCenterPanel extends ProductPanel {

    public EmployeeCenterPanel(Shop shop, CustomerProductTable.tableButtonListener listener, User user) {
        super(shop, listener, user);
        setupUI();
    }

    private void setupUI() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Setup Column names and get all products
        String[] columns = {"Artikelnummer", "Beschreibung", "Preis", "Quantity", " "};
        List<Products> products = shop.getAllProducts().stream().toList();
        //Create Table
        employeeProductTable = new EmployeeProductTable(products, columns, listener, user, shop);
        this.add(new JScrollPane(employeeProductTable));

    }
}
