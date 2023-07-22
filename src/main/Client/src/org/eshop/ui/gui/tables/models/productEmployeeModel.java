package org.eshop.ui.gui.tables.models;

import org.eshop.entities.Product;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class productEmployeeModel extends AbstractTableModel {
    private final List<Product> productList;
    private final String[] columns;
    boolean[] canEdit = new boolean[]{
            false, false, false, false, true
    };

    public productEmployeeModel(List<Product> productList, String[] columns) {
        super();
        this.columns = columns;
        this.productList = new Vector<>();
        this.productList.addAll(productList);
    }

    public void setProductsList(List<Product> productList) {
        this.productList.clear();
        this.productList.addAll(productList);
        fireTableDataChanged();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }


    @Override
    public int getRowCount() {
        return this.productList.size();
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
        Product product = productList.get(row);
        switch (col) {
            case 0:
                return product.getId();
            case 1:
                return product.getName();
            case 2:
                return product.getPrice();
            case 3:
                return product.getQuantity();
            default:
                return null;
        }
    }
}


