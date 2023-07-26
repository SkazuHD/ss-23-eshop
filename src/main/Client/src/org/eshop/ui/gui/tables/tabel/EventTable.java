package org.eshop.ui.gui.tables.tabel;

import org.eshop.network.Client;
import org.eshop.shop.ShopFacade;
import org.eshop.shop.updatable;
import org.eshop.ui.gui.tables.models.EventTableModel;

import javax.swing.*;

public class EventTable extends JTable implements updatable {

    public EventTable(ShopFacade shop) {
        super();
        EventTableModel model = new EventTableModel(shop);
        this.setModel(model);
        this.setRowHeight(40);
        this.setAutoCreateRowSorter(true);
        //Register for live updates
        Client server = (Client) shop;
        server.getUpdateInterface().addClient(this, "event");

    }

    @Override
    public void update(String keyword) {
        System.out.println("EventTable update");
        if (keyword.equals("event"))
            ((EventTableModel) this.getModel()).update();
    }
}
