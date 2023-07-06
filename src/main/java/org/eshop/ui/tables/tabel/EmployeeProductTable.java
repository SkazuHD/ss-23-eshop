package org.eshop.ui.tables.tabel;

import org.eshop.entities.Product;
import org.eshop.entities.User;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.shop.Shop;
import org.eshop.ui.tables.TableButtonEventListener;
import org.eshop.ui.tables.TableListener;
import org.eshop.ui.tables.components.TableButtonRenderEmployee;
import org.eshop.ui.tables.components.TableCellEditorEmployee;
import org.eshop.ui.tables.models.productEmployeeModel;

import javax.swing.*;
import java.util.List;

public class EmployeeProductTable extends JTable implements TableButtonEventListener {

    Shop shop;
    TableListener listener;
    User user;

    public EmployeeProductTable(List<Product> productList, String[] coulumns, TableListener listener, User user, Shop shop) {
        super();
        this.listener = listener;
        this.user = user;
        this.shop = shop;
        productEmployeeModel tabelModel = new productEmployeeModel(productList, coulumns);
        this.setModel(tabelModel);
        this.setRowHeight(40);
        this.getColumnModel().getColumn(4).setCellRenderer(new TableButtonRenderEmployee());
        this.getColumnModel().getColumn(4).setCellEditor(new TableCellEditorEmployee(this));
        //TableRowSorter<productEmployeeModel> sorter = new TableRowSorter<>();
        this.setAutoCreateRowSorter(true);

        updateProducts(productList);
    }

    public void updateProducts(List<Product> productList) {

        //TODO sort here
        productEmployeeModel tabelModel = (productEmployeeModel) getModel();
        tabelModel.setProductsList(productList);
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
        System.out.println("Edit: " + row);
        try {
            Product p = shop.findProduct((int) getValueAt(row, 0));
            System.out.println(p);
            listener.editProduct(p);
        } catch (ProductNotFound ignore) {
        }


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
