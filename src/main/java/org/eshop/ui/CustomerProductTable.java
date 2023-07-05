package org.eshop.ui;

import org.eshop.entities.Customer;
import org.eshop.entities.MassProducts;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.PacksizeNotMatching;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.shop.Shop;
import org.eshop.ui.components.*;
import org.eshop.ui.models.productTabelModel;

import java.util.List;

public class CustomerProductTable extends javax.swing.JTable implements TableButtonEventListener {
    Shop shop;
    tableButtonListener listener;
    User user;

    public CustomerProductTable(List<Products> productsList, String[] coulumns, tableButtonListener listener, User user, Shop shop) {
        super();
        this.listener = listener;
        this.user = user;
        this.shop = shop;
        productTabelModel tabelModel = new productTabelModel(productsList, coulumns);
        this.setModel(tabelModel);
        this.setRowHeight(40);

        TableButtonRender renderer = new TableButtonRender();
        renderer.setPanel(new TableButtonPanelCustomer());
        this.getColumnModel().getColumn(3).setCellRenderer(renderer);

        TableCellEditor editor = new TableCellEditor(this);
        editor.setPanel(new TableButtonPanelCustomer());
        this.getColumnModel().getColumn(3).setCellEditor(editor);

        updateProducts(productsList);
    }

    public void updateProducts(List<Products> productsList) {

        //TODO sort here

        productTabelModel tabelModel = (productTabelModel) getModel();
        tabelModel.setProductsList(productsList);
    }

    @Override
    public void onAdd(int row) {
        int prodID = (int) getValueAt(row, 0);
        Products p = null;
        try {
            p = shop.findProduct(prodID);
            shop.addToCart(prodID, p instanceof MassProducts mp ? mp.getPacksize() : 1, (Customer) user);

        } catch (ProductNotFound | PacksizeNotMatching | NotInStockException e) {

        }

        listener.updateCart();
    }

    @Override
    public void onRemove(int row) {
        int prodID = (int) getValueAt(row, 0);
        Products p = null;
        try {
            p = shop.findProduct(prodID);
            shop.removeFromCart(prodID, p instanceof MassProducts mp ? mp.getPacksize() : 1, (Customer) user);

        } catch (ProductNotFound | PacksizeNotMatching e) {

        }

        //TODO ADD PRODUCT TO CART

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
