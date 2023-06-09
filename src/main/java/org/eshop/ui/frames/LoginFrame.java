package org.eshop.ui.frames;

import org.eshop.entities.User;
import org.eshop.shop.Shop;

import javax.swing.*;
import java.awt.*;



public class LoginFrame extends JFrame {
    public interface addLoginListener{
        void onLogin(User user);
    }

    private Shop server;
    private addLoginListener listener;

    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;


    public LoginFrame(Shop shop, addLoginListener listener){
        server = shop;
        this.listener = listener;
        buildUI();
    }

    private void buildUI(){
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        loginPanel.setPreferredSize(new Dimension(640,480));

        JPanel usernamePanel = new JPanel();
        usernameLabel = new JLabel("Username:");
        usernamePanel.add(usernameLabel);
        usernameField = new JTextField();
        usernamePanel.add(usernameField);

        loginPanel.add(usernamePanel);


        JPanel passwordPanel = new JPanel();
        passwordLabel = new JLabel("Password");
        passwordPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordPanel.add(passwordField);

        loginPanel.add(passwordPanel);

        loginPanel.setVisible(true);

        this.add(loginPanel);
        this.setSize(640, 480);
        this.setVisible(true);
    }
    private void setupEvents(){}

}
