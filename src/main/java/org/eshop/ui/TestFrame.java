package org.eshop.ui;

import org.eshop.shop.Shop;
import org.eshop.ui.components.SearchWidget;

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

    public static void main(String[] args) {

        TestFrame frame = new TestFrame(new SearchWidget(new Shop(), result -> {
            System.out.println(result);
        }));


    }
}
