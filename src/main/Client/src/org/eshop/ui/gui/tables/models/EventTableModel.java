package org.eshop.ui.gui.tables.models;

import org.eshop.shop.ShopFacade;

import javax.swing.table.AbstractTableModel;

public class EventTableModel extends AbstractTableModel {

    public EventTableModel(ShopFacade shop) {
        super();

    }
    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }
    @Override
    public Object getValueAt(int i, int i1) {
        return null;
    }

}
