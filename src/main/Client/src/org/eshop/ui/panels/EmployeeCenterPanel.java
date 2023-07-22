package org.eshop.ui.panels;

import org.eshop.entities.Product;
import org.eshop.entities.User;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.tables.TableListener;
import org.eshop.ui.tables.tabel.EmployeeProductTable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCenterPanel extends ProductPanel {

    public EmployeeCenterPanel(ShopFacade shop, TableListener listener, User user) {
        super(shop, listener, user);
        setupUI();
    }

    private void setupUI() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Setup Column names and get all products
        String[] columns = {"Artikelnummer", "Beschreibung", "Preis", "Quantity", " "};
        List<Product> products = shop.getAllProducts().stream().toList();
        //Create Table
        employeeProductTable = new EmployeeProductTable(products, columns, listener, user, shop);
        this.add(new JScrollPane(employeeProductTable));

    }

    public void onSearch(List<Product> result) {
        if(result == null) result = new ArrayList<Product>();
        employeeProductTable.updateProducts(result);
    }
}
