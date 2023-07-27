package org.eshop.ui.gui.tables.models;

import org.eshop.entities.Employee;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;


public class MitarbeiterModel extends AbstractTableModel {
    private final List<Employee> employeeList;
    private final String[] columns;
    boolean[] canEdit = new boolean[]{
            false, false, false, false, false
    };


    public MitarbeiterModel(List<Employee> employeeList, String[] columns) {
        super();
        this.columns = columns;
        this.employeeList = new Vector<>();
        this.employeeList.addAll(employeeList);
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList.clear();
        this.employeeList.addAll(employeeList);
        fireTableDataChanged();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    @Override
    public int getRowCount() {
        return this.employeeList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Employee employee = employeeList.get(row);
        return switch (col) {
            case 0 -> employee.getID();
            case 1 -> employee.getName();
            case 2 -> employee.getUsername();
            default -> null;
        };
    }


}


