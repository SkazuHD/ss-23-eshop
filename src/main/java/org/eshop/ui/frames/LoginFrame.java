package org.eshop.ui.frames;

import org.eshop.entities.User;
import org.eshop.exceptions.LoginFailed;
import org.eshop.shop.Shop;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;


public class LoginFrame extends JFrame {
    public interface addLoginListener{
        void onLogin(User user);
    }

    private Shop server;
    private addLoginListener listener;

    private JPanel loginPanel;
    private JPanel formPanel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;


    public LoginFrame(Shop shop, addLoginListener listener){
        server = shop;
        this.listener = listener;
        buildUI();
        setupEvents();
    }

    private void buildUI(){
        Dimension inputMaxSize = new Dimension(300,25);
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        loginPanel.setBorder(new EmptyBorder(10,10,10,10));

        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.PAGE_AXIS));
        formPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        JLabel title = new JLabel("LOGIN");
        title.setFont(new Font("Arial", Font.PLAIN, 24));
        formPanel.add(title);

        usernameLabel = new JLabel("Username:");
        formPanel.add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setMaximumSize(inputMaxSize);
        usernameField.setPreferredSize(inputMaxSize);

        formPanel.add(usernameField);

        loginPanel.add(formPanel);



        passwordLabel = new JLabel("Password");
        formPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        passwordField.setMaximumSize(inputMaxSize);
        passwordField.setPreferredSize(inputMaxSize);


        loginButton = new JButton("LOGIN");
        formPanel.add(loginButton);
        loginPanel.setVisible(true);

        this.add(loginPanel);

        pack();
        this.setVisible(true);
    }
    private void setupEvents(){
        //LOGIN Button
        loginButton.addActionListener((e)->{
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User u = null;
            try {
                u = server.loginUser(username, password);
                listener.onLogin(u);
            }catch (LoginFailed exp){
                JOptionPane.showMessageDialog(new JFrame(), exp.getMessage(), "Login failed!",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
