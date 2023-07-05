package org.eshop.ui;

import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.components.*;
import org.eshop.ui.models.productEmployeeModel;

import javax.swing.*;
import java.util.List;

public class EmployeeProductTable extends JTable implements TableButtonEventListener {

    Shop shop;
    tableButtonListener listener;
    User user;

    public EmployeeProductTable(List<Products> productsList, String[] coulumns, tableButtonListener listener, User user, Shop shop) {
        super();
        this.listener = listener;
        this.user = user;
        this.shop = shop;
        productEmployeeModel tabelModel = new productEmployeeModel(productsList, coulumns);
        this.setModel(tabelModel);
        this.setRowHeight(40);
        //TODO CREATE OWN RENDERER AND EDITOR

        TableButtonRender renderer = new TableButtonRender();
        renderer.setPanel(new TableButtonPanelEmployee());
        this.getColumnModel().getColumn(4).setCellRenderer(renderer);

        TableCellEditor editor = new TableCellEditor(this);
        editor.setPanel(new TableButtonPanelEmployee());
        this.getColumnModel().getColumn(4).setCellEditor(editor);
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
        System.out.println("Edit from EmployeeProductTable");
    }

    @Override
    public void onDelete(int row) {
        //Maybe implement
    }

    @Override
    public void onView(int row) {
        //TODO show Graph
        System.out.println("View from EmployeeProductTable");
    }

}
