package org.eshop.ui;

import org.eshop.exceptions.ProductNotFound;

import javax.swing.*;
import java.awt.*;

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
