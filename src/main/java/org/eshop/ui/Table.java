package org.eshop.ui;

import org.eshop.entities.Products;
import org.eshop.shop.Shop;
import org.eshop.ui.components.TableButtonEventListener;
import org.eshop.ui.components.TableButtonRender;
import org.eshop.ui.components.TableCellEditor;
import org.eshop.ui.models.productTabelModel;

import java.util.List;

public class Table extends javax.swing.JTable implements TableButtonEventListener {
    Shop shop;

    public Table(List<Products> productsList, String[] coulumns) {
        super();
        productTabelModel tabelModel = new productTabelModel(productsList, coulumns);
        this.setModel(tabelModel);
        this.setRowHeight(40);
        this.getColumnModel().getColumn(4).setCellRenderer(new TableButtonRender());
        this.getColumnModel().getColumn(4).setCellEditor(new TableCellEditor(this));
        updateProducts(productsList);


    }

    public void updateProducts(List<Products> productsList) {

        //TODO sort here

        productTabelModel tabelModel = (productTabelModel) getModel();
        tabelModel.setProductsList(productsList);
    }

    @Override
    public void onAdd(int row) {
        System.out.println("ADD");
    }

    @Override
    public void onRemove(int row) {
        System.out.println("REMOVE");
    }

    @Override
    public void onEdit(int row) {
        System.out.println("EDIT");
    }

    @Override
    public void onDelete(int row) {
        System.out.println("DELETE");
    }

    @Override
    public void onView(int row) {
        System.out.println("VIEW");
    }
}
