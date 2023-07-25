package org.eshop.ui.gui.panels;


import org.eshop.entities.Employee;
import org.eshop.entities.Product;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.gui.components.SearchWidget;
import org.eshop.ui.gui.GuiEmployee;
import org.eshop.ui.gui.tables.TableListener;
import org.eshop.ui.gui.tables.tabel.EmployeeTable;

import javax.swing.*;
import java.util.List;


    public class MitarbeiterPanel extends javax.swing.JPanel implements SearchWidget.SearchListener {

        public TableListener listener;

        protected User user;
        protected ShopFacade shop;
        protected EmployeeTable employeeTable;



        public MitarbeiterPanel (ShopFacade shop, GuiEmployee guiEmployee, User user, String[] coulumns) {
            this.shop = shop;
            List<Employee> employeeList = shop.getAllEmployees().stream().toList();
            employeeTable = new EmployeeTable( employeeList, coulumns,user, shop);

            this.listener = listener;

            this.user = user;
            setupUI();

            this.add(employeeTable);
            employeeTable.updateEmployee(employeeList);



        }

        private void setupUI() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }











        @Override
        public void onSearch(List<Product> result) {

        }
    }

