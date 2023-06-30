package org.eshop.ui;

import org.eshop.entities.Employee;
import org.eshop.entities.MassProducts;
import org.eshop.entities.Products;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.shop.Shop;
import org.eshop.ui.panels.addProductPanel;
import org.eshop.ui.panels.editProductPanel;
import org.eshop.ui.panels.registerEmployeePanel;

import javax.swing.*;
import java.awt.*;

public class TestFrame extends JFrame {
    public TestFrame(Component component) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(500, 500);

        //Create Vertical Spacer
        this.add(Box.createRigidArea(new Dimension(0, 50)));

        this.add(component);
        this.setVisible(true);

    }

    public static void main(String[] args) throws ProductNotFound {

        //MOCK DATA
        Shop shop = new Shop();
        Employee employee = new Employee(69, "TESTFRAME", "TESTFRAME", "TESTFRAME");
        Products p = shop.findProduct(1000);
        MassProducts mp = new MassProducts(1, 69, "TESTPROD", 50, 50);

        int[] test = shop.getProductHistory(1001, 30);
        for (int i = 0; i < test.length; i++)
            System.out.println(test[i]);

        //TEST LOGIN SEARCH PANEL
      /*  new TestFrame(new SearchWidget(shop, result -> {
           System.out.println(result);
        }));*/

        //TEST ADD PANEL
        new TestFrame(new addProductPanel(shop, employee));

        //TEST EDIT PANEL
        editProductPanel edit = new editProductPanel(shop, employee);
        edit.onChange(p);
        new TestFrame(edit);

        new TestFrame(new registerEmployeePanel(shop, employee));


    }
}
