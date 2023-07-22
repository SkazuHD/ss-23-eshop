package org.eshop.ui.gui.components;

public interface tableButtonListener {
    void onEdit(int row);

    void onDelete(int row);

    void onView(int row);

    void updateCart();
}
