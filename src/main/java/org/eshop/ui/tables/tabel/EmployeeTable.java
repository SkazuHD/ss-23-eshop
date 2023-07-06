package org.eshop.ui.tables.tabel;

import org.eshop.entities.Employee;
import org.eshop.entities.Product;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.tables.TableButtonEventListener;
import org.eshop.ui.tables.TableListener;
import org.eshop.ui.tables.components.TableButtonRenderEmployee;
import org.eshop.ui.tables.components.TableCellEditorEmployee;
import org.eshop.ui.tables.models.MitarbeiterModel;
import org.eshop.ui.tables.models.productEmployeeModel;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class EmployeeTable extends JTable implements TableButtonEventListener {
    Shop shop;
    TableListener listener;
    User user;



    public EmployeeTable(List<Employee> employeeList, String[] coulumns, TableListener listener, User user, Shop shop){
        super();
        this.listener = listener;
        this.user = user;
        this.shop = shop;
        MitarbeiterModel tabelModel1 = new MitarbeiterModel(employeeList, coulumns);
        this.setModel(tabelModel1);
        this.setRowHeight(40);
        this.getColumnModel().getColumn(4).setCellRenderer(new TableButtonRenderEmployee());
        this.getColumnModel().getColumn(4).setCellEditor(new TableCellEditorEmployee(this));
        //TableRowSorter<productEmployeeModel> sorter = new TableRowSorter<>();
        this.setAutoCreateRowSorter(true);

        updateEmployee(employeeList);
    }



    public void updateEmployee(List<Employee> employeeList) {

        //TODO sort here
     MitarbeiterModel tabelModel1 = (MitarbeiterModel) getModel();
        tabelModel1.setEmployeeList(employeeList);
    }
    @Override
    public void onAdd(int row) {

    }

    @Override
    public void onRemove(int row) {

    }

    @Override
    public void onEdit(int row) {

    }

    @Override
    public void onDelete(int row) {


    }

    @Override
    public void onView(int row) {

    }
}
