package org.eshop.ui.gui.tables;

public interface TableButtonEventListener {
    //Kurz erklären warim das Grün ist und was ein Listener tut

    void onAdd(int row);

    void onRemove(int row);

    void onEdit(int row);

    void onDelete(int row);

    void onView(int row);

}
