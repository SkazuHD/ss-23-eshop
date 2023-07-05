package org.eshop.ui.panels;
import org.eshop.entities.Products;
import org.eshop.shop.Shop;
import org.eshop.ui.Table;
import org.eshop.ui.components.SearchWidget;

import javax.swing.table.JTableHeader;
import java.util.List;

public class CustomerProducts extends javax.swing.JPanel implements SearchWidget.SearchListener {


    private final Table table;
    Shop shop;

    public CustomerProducts(Shop shop) {

        this.shop = shop;

        List<Products> products = shop.getAllProducts().stream().toList();
        String[] columns = {"Beschreibung", "Preis", " "};
        table = new Table(products, columns);
        this.add(table);
        table.setTableHeader(new JTableHeader());
    }

    public void onSearch(List<Products> result) {
        table.updateProducts(result);
    }


}
