package org.eshop.ui.gui.panels;

import org.eshop.entities.Product;
import org.eshop.entities.User;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.gui.components.SearchWidget;
import org.eshop.ui.gui.tables.TableListener;
import org.eshop.ui.gui.tables.tabel.CustomerProductTable;
import org.eshop.ui.gui.tables.tabel.EmployeeProductTable;

import javax.swing.*;
import java.util.List;

public abstract class ProductPanel extends javax.swing.JPanel implements SearchWidget.SearchListener {

    protected TableListener listener;
    protected User user;
    protected ShopFacade shop;
    protected CustomerProductTable customerProductTable;
    protected EmployeeProductTable employeeProductTable;


    public ProductPanel(ShopFacade shop, TableListener listener, User user) {

        this.shop = shop;
        this.listener = listener;
        this.user = user;
        setupUI();

    }

    private void setupUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void onSearch(List<Product> result) {
        customerProductTable.updateProducts(result);
    }


}