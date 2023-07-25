package org.eshop.ui.gui.tables.models;

import org.eshop.entities.Event;
import org.eshop.shop.ShopFacade;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EventTableModel extends AbstractTableModel {
    private final List<Event> eventList;
    private final String[] columns;
    private final ShopFacade shop;
    public EventTableModel(ShopFacade shop) {
        super();
        this.shop = shop;
        this.columns = new String[]{"Day", "User", "Product", "Quantity"};
        this.eventList = shop.getAllEvents().stream().toList();
        fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return eventList.size();
    }
    public void update() {
        this.eventList.clear();
        this.eventList.addAll(shop.getAllEvents().stream().toList());
        fireTableDataChanged();
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    public String getColumnName(int column) {
        return columns[column];
    }
    @Override
    public Object getValueAt(int row, int col) {
        Event event = eventList.get(row);
        return switch (col) {
            case 0 -> event.getDayInYear();
            case 1 -> event.getUserId();
            case 2 -> event.getProductId();
            case 3 -> event.getQuantity();
            default -> null;
        };
    }

}
