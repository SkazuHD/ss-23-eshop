package org.eshop.ui.gui.listener;

import org.eshop.shop.ShopFacade;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

public class ShopCloseListener extends WindowAdapter implements WindowListener {

    private final ShopFacade server;

    public ShopCloseListener(ShopFacade server) {
        super();
        this.server = server;
    }

    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {

        //Show JDialog YES/NO
        int option = JOptionPane.showOptionDialog(new JFrame(), "Save Data before closing the Application?", "Save Data?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        switch (option) {
            case JOptionPane.YES_OPTION:
                server.save();
                break;
            case JOptionPane.NO_OPTION:
                break;
            default:
                return;
        }

        //Dispose JFrame
        windowEvent.getWindow().setVisible(false);
        windowEvent.getWindow().dispose();
        System.exit(0);
    }
}
