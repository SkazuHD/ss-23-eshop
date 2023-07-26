package org.eshop.ui.gui.panels;

import javax.swing.*;
import java.awt.*;

public class CheckoutPanel extends javax.swing.JPanel {


    public CheckoutPanel() {
        setupUI();
    }

    private void setupUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(200, 500));
    }
}