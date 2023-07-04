package org.eshop.ui.components;

import javax.swing.*;
import java.awt.*;

public class TableCellEditor extends DefaultCellEditor {

    private final TableButtonEventListener listener;

    public TableCellEditor(TableButtonEventListener listener) {
        super(new JCheckBox());
        this.listener = listener;
    }

    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        TableButtonPanel panel = new TableButtonPanel();
        panel.initEvents(listener, row);
        return panel;
    }
}
