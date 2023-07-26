package org.eshop.ui.gui.tables.models;

import org.eshop.entities.MassProduct;
import org.eshop.entities.Product;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class productTabelModel extends AbstractTableModel {

    private final List<Product> productList;
    private final String[] columns;
    boolean[] canEdit = new boolean[]{
            false, false, false, false, true
    };

    public productTabelModel(List<Product> productList, String[] columns) {
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
        return switch (col) {
            case 0 -> product.getId();
            case 1 -> product.getName();
            case 2 -> product.getPrice();
            case 3 -> product instanceof MassProduct mp ? mp.getPacksize() : 1;
            default -> null;
        };
    }
}
