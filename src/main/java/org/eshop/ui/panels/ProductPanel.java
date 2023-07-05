package org.eshop.ui.panels;

import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.CustomerProductTable;
import org.eshop.ui.EmployeeProductTable;
import org.eshop.ui.components.SearchWidget;

import javax.swing.*;
import java.util.List;

public abstract class ProductPanel extends javax.swing.JPanel implements SearchWidget.SearchListener {

    protected CustomerProductTable.tableButtonListener listener;
    protected User user;
    protected Shop shop;
    protected CustomerProductTable customerProductTable;
    protected EmployeeProductTable employeeProductTable;


    public ProductPanel(Shop shop, CustomerProductTable.tableButtonListener listener, User user) {

        this.shop = shop;
        this.listener = listener;
        this.user = user;
        setupUI();

    }

    private void setupUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void onSearch(List<Products> result) {
        customerProductTable.updateProducts(result);
    }


}