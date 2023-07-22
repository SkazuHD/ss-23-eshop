package org.eshop.ui.gui.tables.components;

import org.eshop.ui.gui.tables.TableButtonEventListener;

import javax.swing.*;
import java.awt.*;

public class TableCellEditor extends DefaultCellEditor {

    private final TableButtonEventListener listener;

    public TableCellEditor(TableButtonEventListener listener) {
        super(new JCheckBox());
        this.listener = listener;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        TableButtonPanel panel = new TableButtonPanel();
        panel.initEvents(listener, row);
        return panel;
    }
}
