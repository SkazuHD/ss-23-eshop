package org.eshop.ui;

import org.eshop.entities.Customer;
import org.eshop.entities.Employee;
import org.eshop.entities.User;
import org.eshop.network.Client;
import org.eshop.shop.Shop;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.frames.LoginFrame;

import javax.swing.*;

/**
 * The type Gui.
 */
public class Gui extends JFrame implements LoginFrame.addLoginListener, CustomerMenu.addLogoutListener {



    ShopFacade server;
    User loggedInUser;
    LoginFrame loginFrame;


    /*TODO General: ADD CUSTOM JNumberField Class */
    public Gui() {
        server = new Client("localhost", 6789);
       // server = new Shop();
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
            JFrame Employee = new GuiEmployee(server, user, this);
        } else if(user instanceof Customer) {
            loginFrame.dispose();
            JFrame Customer = new GuiCustomer(server, user, this);

        }

    }
    public void onLogout(User user){
        server.logOut(user);
        new LoginFrame(server, this);
    }
}
