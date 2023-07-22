package org.eshop.ui;

import org.eshop.entities.Employee;
import org.eshop.entities.MassProduct;
import org.eshop.entities.Product;
import org.eshop.exceptions.ProductNotFound;

import org.eshop.ui.components.SearchWidget;
import org.eshop.ui.frames.GraphFrame;
import org.eshop.ui.panels.addProductPanel;
import org.eshop.ui.panels.editProductPanel;
import org.eshop.ui.panels.registerEmployeePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestFrame extends JFrame {
    public TestFrame(Component component) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        //Create Vertical Spacer
        this.add(Box.createRigidArea(new Dimension(0, 50)));

        this.add(component);
        this.setVisible(true);

    }

    public static void main(String[] args) throws ProductNotFound {

        //MOCK DATA
    }
}
