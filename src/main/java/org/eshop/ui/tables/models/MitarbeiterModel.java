package org.eshop.ui.tables.models;

import org.eshop.entities.Employee;
import org.eshop.entities.MassProduct;
import org.eshop.entities.Product;
import org.eshop.entities.User;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class MitarbeiterModel extends AbstractTableModel {
    private final List<Employee> employeeList;
    private final String[] columns;
    boolean[] canEdit = new boolean[]{
            false, false, false, false, true
    };




    public MitarbeiterModel(List<Employee> employeeList, String[] columns){
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
    public Object getValueAt(int row, int col) {
        Employee employee = employeeList.get(row);
        switch (col) {
            case 0:
                return employee.getID();
            case 1:
                return employee.getName();
            case 2:
                return employee.getUsername();
            default:
                return null;
        }
    }




}
