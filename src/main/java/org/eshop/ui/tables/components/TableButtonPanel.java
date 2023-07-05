package org.eshop.ui.tables.components;

import org.eshop.ui.tables.TableButtonEventListener;

import javax.swing.*;

public abstract class TableButtonPanel extends JPanel {


    public TableButtonPanel() {
        initComponents();
    }

    public void initEvents(TableButtonEventListener listener, int row) {

    }

    private void initComponents() {
    }
}
