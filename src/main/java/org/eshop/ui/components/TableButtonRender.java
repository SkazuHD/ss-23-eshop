package org.eshop.ui.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableButtonRender extends DefaultTableCellRenderer {
    private TableButtonPanel editorComponent;

    public TableButtonRender() {
        super();
    }

    public void setPanel(TableButtonPanel panel) {
        this.editorComponent = panel;
    }

    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        TableButtonPanel action = editorComponent;
        if (!isSeleted && row % 2 == 0) {
            action.setBackground(Color.WHITE);
        } else {
            action.setBackground(com.getBackground());
        }
        return action;
    }

}
