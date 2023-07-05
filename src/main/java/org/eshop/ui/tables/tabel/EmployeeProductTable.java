package org.eshop.ui.tables.tabel;

import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.tables.TableButtonEventListener;
import org.eshop.ui.tables.components.TableButtonRender;
import org.eshop.ui.tables.components.TableCellEditor;
import org.eshop.ui.tables.models.productEmployeeModel;

import javax.swing.*;
import java.util.List;

public class EmployeeProductTable extends JTable implements TableButtonEventListener {

    Shop shop;
    CustomerProductTable.tableButtonListener listener;
    User user;

    public EmployeeProductTable(List<Products> productsList, String[] coulumns, CustomerProductTable.tableButtonListener listener, User user, Shop shop) {
        super();
        this.listener = listener;
        this.user = user;
        this.shop = shop;
        //TODO CREATE OWN MODEL
        productEmployeeModel tabelModel = new productEmployeeModel(productsList, coulumns);
        this.setModel(tabelModel);
        this.setRowHeight(40);
        //TODO CREATE OWN RENDERER AND EDITOR
        this.getColumnModel().getColumn(4).setCellRenderer(new TableButtonRender());
        this.getColumnModel().getColumn(4).setCellEditor(new TableCellEditor(this));
        updateProducts(productsList);
    }

    public void updateProducts(List<Products> productsList) {

        //TODO sort here
        productEmployeeModel tabelModel = (productEmployeeModel) getModel();
        tabelModel.setProductsList(productsList);
    }

    @Override
    public void onAdd(int row) {
        //Not needed
    }

    @Override
    public void onRemove(int row) {
        //Not needed
    }

    @Override
    public void onEdit(int row) {
        //TODO implement
    }

    @Override
    public void onDelete(int row) {
        //Maybe implement
    }

    @Override
    public void onView(int row) {
        //TODO show Graph
    }
}
