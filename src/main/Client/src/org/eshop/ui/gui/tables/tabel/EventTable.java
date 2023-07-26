package org.eshop.ui.gui.tables.tabel;

import org.eshop.network.Client;
import org.eshop.shop.ShopFacade;
import org.eshop.shop.updatable;
import org.eshop.ui.gui.tables.Filterable;
import org.eshop.ui.gui.tables.models.EventTableModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;

public class EventTable extends JTable implements updatable, Filterable {

    public EventTable(ShopFacade shop) {
        super();
        EventTableModel model = new EventTableModel(shop);
        this.setModel(model);
        this.setRowHeight(40);

        TableRowSorter<EventTableModel> sorter = new TableRowSorter<>(model);
        this.setRowSorter(sorter);
        //Register for live updates
        Client server = (Client) shop;
        server.getUpdateInterface().addClient(this, "event");

    }

    public void filter(String keyword) {
        TableRowSorter<EventTableModel> sorter = (TableRowSorter<EventTableModel>) this.getRowSorter();
        try {
            RowFilter.regexFilter(keyword);
        } catch (Exception e) {
            return;
        }
        sorter.setRowFilter(RowFilter.regexFilter(keyword));
    }

    @Override
    public void update(String keyword) {
        System.out.println("EventTable update");
        if (keyword.equals("event"))
            ((EventTableModel) this.getModel()).update();
    }

}
