package org.eshop.ui.gui.tables.components;

import org.eshop.ui.gui.tables.TableButtonEventListener;

import javax.swing.*;

public class TableButtonPanel extends JPanel {

    private TabelButton addButton;
    private TabelButton removeButton;

    public TableButtonPanel() {
        initComponents();
    }

    public void initEvents(TableButtonEventListener listener, int row) {
        addButton.addActionListener(actionEvent -> listener.onAdd(row));
        removeButton.addActionListener(actionEvent -> listener.onRemove(row));
    }

    private void initComponents() {
        addButton = new TabelButton();
        addButton.setText("+");
        removeButton = new TabelButton();
        removeButton.setText("-");
        //viewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/eshop/ui/components/view.png")));
        //TODO SET ICON
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
}
