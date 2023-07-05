package org.eshop.ui.tables.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableButtonRenderEmployee extends DefaultTableCellRenderer {
    public TableButtonRenderEmployee() {
        super();
    }

    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        TableButtonPanelEmployee action = new TableButtonPanelEmployee();
        if (!isSeleted && row % 2 == 0) {
            action.setBackground(Color.WHITE);
        } else {
            action.setBackground(com.getBackground());
        }
        return action;
    }

}
