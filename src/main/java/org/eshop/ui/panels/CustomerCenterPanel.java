package org.eshop.ui.panels;

import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.CustomerProductTable;
import org.eshop.ui.components.tableButtonListener;

import javax.swing.*;
import java.util.List;

public class CustomerCenterPanel extends ProductPanel {
    public CustomerCenterPanel(Shop shop, tableButtonListener listener, User user) {
        super(shop, listener, user);
        setupUI();
    }

    private void setupUI() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Setup Column names and get all products
        String[] columns = {"Artikelnummer", "Beschreibung", "Preis", " "};
        List<Products> products = shop.getAllProducts().stream().toList();
        //Create Table
        customerProductTable = new CustomerProductTable(products, columns, listener, user, shop);
        this.add(new JScrollPane(customerProductTable));

    }
}
