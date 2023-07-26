package org.eshop.ui.gui.tables;

public interface TableButtonEventListener {
    //Hören auf events wenn der Button gedrückt wird.

    void onAdd(int row);

    void onRemove(int row);

    void onEdit(int row);

    void onDelete(int row);

    void onView(int row);

}
