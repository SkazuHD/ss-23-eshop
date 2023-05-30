package org.eshop.ui;

//Import Swing

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The type Gui.
 */
public class Gui extends WindowAdapter {
    JFrame frame;

    public Gui() {

    }

    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.initialize();
    }

    public void initialize() {
        frame = new JFrame("Eshop");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);

        //Add a window listener for the close button

        frame.addWindowListener(this);

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.err.println("Closing");
        frame.dispose();
    }
}
