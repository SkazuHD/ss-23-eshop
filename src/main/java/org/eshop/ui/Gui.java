package org.eshop.ui;

import org.eshop.entities.Employee;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.frames.LoginFrame;



import javax.swing.*;

/**
 * The type Gui.
 */
public class Gui extends JFrame implements LoginFrame.addLoginListener {

    Shop server;
    User loggedInUser;
    LoginFrame loginFrame;


    /*TODO General: ADD CUSTOM JNumberField Class */
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
        loggedInUser = user;
        if (user instanceof Employee) {
            loginFrame.dispose();
            JFrame Employee = new GuiEmployee(server, user);
        } else {
            loginFrame.dispose();
            JFrame Customer = new GuiCustomer(server, user);

        }

    }
}
