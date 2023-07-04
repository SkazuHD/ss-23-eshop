package org.eshop.ui;

import org.eshop.entities.Products;
import org.eshop.shop.Shop;
import org.eshop.ui.models.productTabelModel;

import java.util.List;

public class Table extends javax.swing.JTable {
    Shop shop;

    public Table(List<Products> productsList, String[] coulumns) {
        super();
        productTabelModel tabelModel = new productTabelModel(productsList, coulumns);
        this.setModel(tabelModel);
        updateProducts(productsList);

    }

    public void updateProducts(List<Products> productsList) {

        //TODO sort here

        productTabelModel tabelModel = (productTabelModel) getModel();
        tabelModel.setProductsList(productsList);
    }
}
