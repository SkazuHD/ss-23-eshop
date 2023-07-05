package org.eshop.ui.panels;

import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.Table;
import org.eshop.ui.components.SearchWidget;
import org.eshop.ui.components.TableButtonEventListener;

import javax.swing.table.JTableHeader;
import java.util.List;

public class ProducktPanel extends javax.swing.JPanel implements SearchWidget.SearchListener {

    private final Table table;
    Shop shop;

    public ProducktPanel(Shop shop, Table.tableButtonListener listener, User user) {

        this.shop = shop;

        List<Products> products = shop.getAllProducts().stream().toList();
        String[] columns = {"Artikelnummer", "Beschreibung", "Preis", "Anzahl", " "};
        table = new Table(products, columns, listener, user, shop);
        this.add(table);
        table.setTableHeader(new JTableHeader());
    }

    public void onSearch(List<Products> result) {
        table.updateProducts(result);
    }



}