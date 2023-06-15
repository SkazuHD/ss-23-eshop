package org.eshop.ui;

import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.frames.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The type Gui.
 */
public class Gui extends JFrame implements LoginFrame.addLoginListener{

    Shop server;
    JPanel search;
    JPanel output;
    User loggedInUser;
    LoginFrame loginFrame;

    public Gui() {
        server = new Shop();
                loginFrame = new LoginFrame(server, this);

    }

    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.initialize();
    }

    public void initialize() {

    }

    @Override
    public void onLogin(User user) {
        //TODO LOGIN SHOW EMPLOYEE/CUSTOMER FRAME
        System.out.println(user);
                JFrame Employee = new GuiEmployee(server);

    }
}
