package org.eshop.ui.gui.tables.components;

import org.eshop.ui.gui.tables.TableButtonEventListener;

import javax.swing.*;
import java.awt.*;

public class TableCellEditorEmployee extends DefaultCellEditor {

    private final TableButtonEventListener listener;

    public TableCellEditorEmployee(TableButtonEventListener listener) {
        super(new JCheckBox());
        this.listener = listener;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        TableButtonPanelEmployee panel = new TableButtonPanelEmployee();
        panel.initEvents(listener, row);
        return panel;
    }
}
