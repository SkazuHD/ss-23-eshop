package org.eshop.ui.gui.tables.tabel;

import org.eshop.shop.ShopFacade;
import org.eshop.ui.gui.tables.models.EventTableModel;

import javax.swing.*;

public class EventTable extends JTable {
    public EventTable(ShopFacade shop) {
        super();
        EventTableModel model = new EventTableModel(shop);
        this.setModel(model);
        this.setRowHeight(40);
        this.setAutoCreateRowSorter(true);

    }
}
