package org.eshop.ui.gui.tables.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableButtonRender extends DefaultTableCellRenderer {
    public TableButtonRender() {
        super();
    }
   // Wegen den verschiedenen Buttons / employe hat einen Button Renderer und Customer auch

    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        TableButtonPanel action = new TableButtonPanel();
        if (!isSeleted && row % 2 == 0) {
            action.setBackground(Color.WHITE);
        } else {
            action.setBackground(com.getBackground());
        }
        return action;
    }

}
