package org.eshop.ui.tables.components;

import org.eshop.ui.tables.TableButtonEventListener;

import javax.swing.*;
import java.awt.*;

public class TableCellEditor extends DefaultCellEditor {

    private final TableButtonEventListener listener;
    private TableButtonPanel editorComponent;

    public TableCellEditor(TableButtonEventListener listener) {
        super(new JCheckBox());
        this.listener = listener;
    }

    public void setPanel(TableButtonPanel panel) {
        this.editorComponent = panel;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        TableButtonPanel panel = this.editorComponent;
        panel.initEvents(listener, row);
        return panel;
    }
}
