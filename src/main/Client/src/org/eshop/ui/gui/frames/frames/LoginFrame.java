package org.eshop.ui.gui.frames.frames;

import org.eshop.entities.User;
import org.eshop.exceptions.LoginFailed;
import org.eshop.exceptions.UserExistsException;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.gui.listener.ShopCloseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class LoginFrame extends JFrame {
    private final ShopFacade server;
    private final addLoginListener listener;
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;
    private JPasswordField registerPasswordField2;
    private JTextField registerNameField;
    private JTextField registerAddressField;

    public LoginFrame(ShopFacade shop, addLoginListener listener) {
        server = shop;
        this.listener = listener;
        buildUI();
        setupEvents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new ShopCloseListener(server));
    }

    private void buildUI() {
        Dimension inputMaxSize = new Dimension(300, 25);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2, 10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        //loginPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        JLabel title = new JLabel("LOGIN");
        title.setFont(new Font("Arial", Font.PLAIN, 24));
        loginPanel.add(title);

        usernameLabel = new JLabel("Username:");
        loginPanel.add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setMaximumSize(inputMaxSize);
        usernameField.setPreferredSize(inputMaxSize);

        loginPanel.add(usernameField);

        mainPanel.add(loginPanel);


        passwordLabel = new JLabel("Password");
        loginPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);
        passwordField.setMaximumSize(inputMaxSize);
        passwordField.setPreferredSize(inputMaxSize);


        loginButton = new JButton("LOGIN");
        loginPanel.add(loginButton);
        this.add(mainPanel);

        //Customer Registration Panel
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.PAGE_AXIS));
        //registerPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        JLabel registerTitle = new JLabel("REGISTER");
        registerTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        registerPanel.add(registerTitle);

        //Customer Registration Form
        JLabel registerUsernameLabel = new JLabel("Username:");
        registerPanel.add(registerUsernameLabel);
        registerUsernameField = new JTextField();
        registerUsernameField.setMaximumSize(inputMaxSize);
        registerUsernameField.setPreferredSize(inputMaxSize);
        registerPanel.add(registerUsernameField);

        JLabel registerPasswordLabel = new JLabel("Password:");
        registerPanel.add(registerPasswordLabel);
        registerPasswordField = new JPasswordField();
        registerPasswordField.setMaximumSize(inputMaxSize);
        registerPasswordField.setPreferredSize(inputMaxSize);
        registerPanel.add(registerPasswordField);

        JLabel registerPasswordConfirmLabel = new JLabel("Confirm Password:");
        registerPanel.add(registerPasswordConfirmLabel);
        registerPasswordField2 = new JPasswordField();
        registerPasswordField2.setMaximumSize(inputMaxSize);
        registerPasswordField2.setPreferredSize(inputMaxSize);
        registerPanel.add(registerPasswordField2);

        JLabel registerNameLabel = new JLabel("Name:");
        registerPanel.add(registerNameLabel);
        registerNameField = new JTextField();
        registerNameField.setMaximumSize(inputMaxSize);
        registerNameField.setPreferredSize(inputMaxSize);
        registerPanel.add(registerNameField);

        JLabel registerAddressLabel = new JLabel("Address:");
        registerPanel.add(registerAddressLabel);
        registerAddressField = new JTextField();
        registerAddressField.setMaximumSize(inputMaxSize);
        registerAddressField.setPreferredSize(inputMaxSize);
        registerPanel.add(registerAddressField);

        registerButton = new JButton("REGISTER");
        registerPanel.add(registerButton);

        mainPanel.add(registerPanel);

        pack();
        this.setVisible(true);
    }

    private void setupEvents() {
        //LOGIN Button
        loginButton.addActionListener((e) -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());// username und passsowrt wird vom server geprüft
            User u;
            try {
                u = server.logIn(username, password); // Wenn das oben geklappt hat bekommt man einen user zurück
                listener.onLogin(u);//schaut ob employee oder Customer
                this.dispose();//Schlißt das Frame
            } catch (LoginFailed exp) { //exeption
                JOptionPane.showMessageDialog(new JFrame(), exp.getMessage(), "Login failed!",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        registerButton.addActionListener((e -> {
            //der input wird betrachtet
            String username = registerUsernameField.getText();
            String password = new String(registerPasswordField.getPassword());
            String password2 = new String(registerPasswordField2.getPassword());//Bis hier
            if (!password.equals(password2)) { //öffnet fehler dialog wenn passwörter nicht übereinstimmen
                JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match!", "Registration failed!",
                        JOptionPane.ERROR_MESSAGE);
                registerPasswordField.setText("");
                registerPasswordField2.setText("");
                return;
            }
            String name = registerNameField.getText();
            String address = registerAddressField.getText();
            try {
                server.registerUser(username, password, name, address);/*User wird versucht zu registrieren/
                 wird geprüft ob es schon gibt*/
                JOptionPane.showMessageDialog(new JFrame(), "Registration successful!", "Registration successful!",
                        JOptionPane.INFORMATION_MESSAGE);
                //Clear fields
                registerUsernameField.setText("");
                registerPasswordField.setText("");
                registerPasswordField2.setText("");
                registerNameField.setText("");
                registerAddressField.setText("");
            } catch (UserExistsException | IllegalArgumentException exp) {
                JOptionPane.showMessageDialog(new JFrame(), exp.getMessage(), "Registration failed!",
                        JOptionPane.ERROR_MESSAGE);
            }

        }));

    }

    public interface addLoginListener {
        void onLogin(User user);
    }

}
