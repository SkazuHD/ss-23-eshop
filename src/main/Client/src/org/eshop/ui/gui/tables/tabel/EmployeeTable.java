package org.eshop.ui.gui.tables.tabel;

import org.eshop.entities.Employee;
import org.eshop.entities.User;
import org.eshop.network.Client;
import org.eshop.shop.ShopFacade;
import org.eshop.shop.updatable;
import org.eshop.ui.gui.tables.Filterable;
import org.eshop.ui.gui.tables.models.MitarbeiterModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.util.List;

public class EmployeeTable extends JTable implements updatable, Filterable {
    ShopFacade shop;
    User user;

    public EmployeeTable(List<Employee> employeeList, String[] coulumns, User user, ShopFacade shop) {
        super();
        this.user = user;
        this.shop = shop;
        MitarbeiterModel tabelModel1 = new MitarbeiterModel(employeeList, coulumns);
        this.setModel(tabelModel1);
        this.setRowHeight(40);

        //Register for live updates
        Client server = (Client) shop;
        server.getUpdateInterface().addClient(this, "employee");

        this.setAutoCreateRowSorter(true);

        updateEmployee(employeeList);
    }


    public void updateEmployee(List<Employee> employeeList) {
        MitarbeiterModel tabelModel1 = (MitarbeiterModel) getModel();
        tabelModel1.setEmployeeList(employeeList);
    }

    @Override
    public void update(String keyword) {
        System.out.println("Employee update");
        if (keyword.equals("employee"))
            updateEmployee(shop.getAllEmployees().stream().toList());
    }

    @Override
    public void filter(String keyword) {
        TableRowSorter<MitarbeiterModel> sorter = (TableRowSorter<MitarbeiterModel>) this.getRowSorter();
        try {
            RowFilter.regexFilter(keyword);
        } catch (Exception e) {
            return;
        }
        sorter.setRowFilter(RowFilter.regexFilter(keyword));
    }
}

