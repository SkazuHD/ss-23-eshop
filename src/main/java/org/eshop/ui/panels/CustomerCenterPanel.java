package org.eshop.ui.panels;

import org.eshop.entities.Product;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.tables.TableListener;
import org.eshop.ui.tables.tabel.CustomerProductTable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerCenterPanel extends ProductPanel {
    public CustomerCenterPanel(ShopFacade shop, TableListener listener, User user) {
        super(shop, listener, user);
        setupUI();
    }

    private void setupUI() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Setup Column names and get all products
        String[] columns = {"Artikelnummer", "Beschreibung", "Preis", "Packsize", ""};

        Collection<Product> productCollection = shop.getAllProducts();
        List<Product> products = productCollection != null ? productCollection.stream().toList() : new ArrayList<>();

        //TODO SERVER RETURNS ALL
        products = shop.getAllProducts().stream().toList();
        //Create Table
        customerProductTable = new CustomerProductTable(products, columns, listener, user, shop);
        this.add(new JScrollPane(customerProductTable));

    }

    public void onSearch(List<Product> result) {
        customerProductTable.updateProducts(result);
    }
}
