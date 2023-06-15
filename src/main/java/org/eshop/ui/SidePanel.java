package org.eshop.ui;

import java.awt.*;

public class SidePanel extends javax.swing.JFrame {
    private javax.swing.JPanel pannel =
            new javax.swing.JPanel();
    Gui gui;
    GuiEmployee guiEmployee;
    GuiEmployee jPanel1;

    public SidePanel (GuiEmployee guiEmployee){
        this.guiEmployee = guiEmployee;

    jPanel1.add(pannel,java.awt.BorderLayout.LINE_END);
    pannel.setPreferredSize(new Dimension(400,2));
}
}
