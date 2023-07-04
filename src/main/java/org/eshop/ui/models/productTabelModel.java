package org.eshop.ui.models;

import org.eshop.entities.Products;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class productTabelModel extends AbstractTableModel {

    private final List<Products> productsList;
    private final String[] columns;
    boolean[] canEdit = new boolean[]{
            false, false, false, false, true
    };

    public productTabelModel(List<Products> productsList, String[] columns) {
        super();
        this.columns = columns;
        this.productsList = new Vector<>();
        this.productsList.addAll(productsList);
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList.clear();
        this.productsList.addAll(productsList);
        fireTableDataChanged();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return canEdit[columnIndex];
    }


    @Override
    public int getRowCount() {
        return this.productsList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Products products = productsList.get(row);
        switch (col) {
            case 0:
                return products.getId();
            case 1:
                return products.getName();
            case 2:
                return products.getPrice();
            case 3:
                return products.getQuantity();
            case 4:
                return null;
            default:
                return null;
        }
    }
}
