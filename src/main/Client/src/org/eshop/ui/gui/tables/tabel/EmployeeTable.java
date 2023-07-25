package org.eshop.ui.gui.tables.tabel;

import org.eshop.entities.Employee;
import org.eshop.entities.Product;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.gui.GuiEmployee;
import org.eshop.ui.gui.tables.TableListener;
import org.eshop.ui.gui.tables.TableButtonEventListener;
import org.eshop.ui.gui.tables.components.TableButtonRenderEmployee;
import org.eshop.ui.gui.tables.components.TableCellEditorEmployee;
import org.eshop.ui.gui.tables.models.MitarbeiterModel;
import org.eshop.ui.gui.tables.models.productEmployeeModel;
import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class EmployeeTable extends JTable  {
    ShopFacade shop;
    User user;

    GuiEmployee guiEmployee;



    public EmployeeTable(List<Employee> employeeList, String[] coulumns, User user, ShopFacade shop){
        super();
        this.user = user;
        this.shop = shop;
        MitarbeiterModel tabelModel1 = new MitarbeiterModel(employeeList, coulumns);
        this.setModel(tabelModel1);
        this.setRowHeight(40);

        //TableRowSorter<productEmployeeModel> sorter = new TableRowSorter<>();
        this.setAutoCreateRowSorter(true);

        updateEmployee(employeeList);
    }




    public void updateEmployee(List<Employee> employeeList) {

        //TODO sort here
        MitarbeiterModel tabelModel1 = (MitarbeiterModel) getModel();
        tabelModel1.setEmployeeList(employeeList);
    }

}

