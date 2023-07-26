package org.eshop.ui.gui.tables.components;

import org.eshop.ui.gui.tables.TableButtonEventListener;

import javax.swing.*;

public class TableButtonPanelEmployee extends JPanel {

    private TabelButton viewButton;

    private TabelButton editButton;

    public TableButtonPanelEmployee() {
        initComponents();
    }

    public void initEvents(TableButtonEventListener listener, int row) {
        viewButton.addActionListener(actionEvent -> listener.onView(row));
        editButton.addActionListener(actionEvent -> listener.onEdit(row));
    }

    private void initComponents() {
        viewButton = new TabelButton();
        viewButton.setText("V");
        editButton = new TabelButton();
        editButton.setText("E");
        //TODO SET ICON
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(viewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(viewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))

                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
}
