package org.eshop.ui.gui.panels;

import org.eshop.shop.ShopFacade;
import org.eshop.ui.gui.tables.tabel.EventTable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class eventPanel extends JPanel {

    JTextField filterField;
    ShopFacade shop;
    EventTable eventTable;

    public eventPanel(ShopFacade shop) {
        this.shop = shop;
        this.eventTable = new EventTable(shop);
        setupUI();
    }

    private void setupUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("FILTER"));
        filterPanel.setMaximumSize(new Dimension(500, 100));
        filterField = new JTextField();
        filterField.setPreferredSize(new Dimension(200, 50));
        filterPanel.add(filterField);
        filterPanel.setPreferredSize(new Dimension(500, 80));
        this.add(filterPanel);
        this.add(new JScrollPane(eventTable));
        this.setPreferredSize(new Dimension(500, 1000));
        filterField.addActionListener(e -> eventTable.filter(filterField.getText()));
        filterField.getDocument().addDocumentListener(new FilterListener());
    }

    private class FilterListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            eventTable.filter(filterField.getText());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            eventTable.filter(filterField.getText());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            eventTable.filter(filterField.getText());
        }

    }
}
