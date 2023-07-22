package org.eshop.ui.tables.tabel;

import org.eshop.entities.Customer;
import org.eshop.entities.MassProduct;
import org.eshop.entities.Product;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.PacksizeNotMatching;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.entities.User;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.tables.TableButtonEventListener;
import org.eshop.ui.tables.TableListener;
import org.eshop.ui.tables.components.TableButtonRender;
import org.eshop.ui.tables.components.TableCellEditor;
import org.eshop.ui.tables.models.productTabelModel;

import javax.swing.*;
import java.util.List;

public class CustomerProductTable extends javax.swing.JTable implements TableButtonEventListener {
    ShopFacade shop;
    TableListener listener;
    User user;

    public CustomerProductTable(List<Product> productList, String[] columns, TableListener listener, User user, ShopFacade shop) {
        super();
        this.listener = listener;
        this.user = user;
        this.shop = shop;
        productTabelModel tableModel = new productTabelModel(productList, columns);
        this.setModel(tableModel);
        this.setRowHeight(40);
        this.getColumnModel().getColumn(4).setCellRenderer(new TableButtonRender());
        this.getColumnModel().getColumn(4).setCellEditor(new TableCellEditor(this));
        this.setAutoCreateRowSorter(true);

        updateProducts(productList);
    }

    public void updateProducts(List<Product> productList) {
        productTabelModel tableModel = (productTabelModel) getModel();
        tableModel.setProductsList(productList);
    }

    @Override
    public void onAdd(int row) {
        int prodID = (int) getValueAt(row, 0);
        Product p;
        try {
            p = shop.findProduct(prodID);
            shop.addToCart(prodID, p instanceof MassProduct mp ? mp.getPacksize() : 1, (Customer) user);

        } catch (ProductNotFound | PacksizeNotMatching | NotInStockException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }

        listener.updateCart();
    }

    @Override
    public void onRemove(int row) {
        int prodID = (int) getValueAt(row, 0);
        Product p;
        try {
            p = shop.findProduct(prodID);
            shop.removeFromCart(prodID, p instanceof MassProduct mp ? mp.getPacksize() : 1, (Customer) user);

        } catch (ProductNotFound | PacksizeNotMatching e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }

        listener.updateCart();
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
