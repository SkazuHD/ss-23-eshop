package org.eshop.ui.gui.panels;

import org.eshop.exceptions.UserExistsException;
import org.eshop.shop.ShopFacade;

import javax.swing.*;
import java.awt.*;

public class registerEmployeePanel extends JPanel {

    private final ShopFacade server;
    private JButton registerButton;
    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;
    private JPasswordField registerPasswordField2;
    private JTextField registerNameField;
    private JTextField registerIDField;

    public registerEmployeePanel(ShopFacade shop) {
        this.server = shop;
        setupUI();
        setupEvents();

    }

    private void setupUI() {
        //Employee Registration Panel
        Dimension inputMaxSize = new Dimension(300, 25);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.PAGE_AXIS));
        //registerPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        JLabel registerTitle = new JLabel("REGISTER");
        registerTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        registerPanel.add(registerTitle);

        //Employee Registration Form
        JLabel registerAddressLabel = new JLabel("ID:");
        registerPanel.add(registerAddressLabel);
        registerIDField = new JTextField();
        registerIDField.setMaximumSize(inputMaxSize);
        registerIDField.setPreferredSize(inputMaxSize);
        registerPanel.add(registerIDField);

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

        registerButton = new JButton("REGISTER");
        registerPanel.add(registerButton);

        this.add(registerPanel);
    }

    private void setupEvents() {
        registerButton.addActionListener((e -> {
            int id = Integer.parseInt(registerIDField.getText());
            String username = registerUsernameField.getText();
            String password = new String(registerPasswordField.getPassword());
            String password2 = new String(registerPasswordField2.getPassword());
            if (!password.equals(password2)) {
                JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match!", "Registration failed!",
                        JOptionPane.ERROR_MESSAGE);
                registerPasswordField.setText("");
                registerPasswordField2.setText("");
                return;
            }
            String name = registerNameField.getText();
            try {
                server.registerEmployee(id, name, username, password);
                JOptionPane.showMessageDialog(new JFrame(), "Registration successful!", "Registration successful!",
                        JOptionPane.INFORMATION_MESSAGE);
                //Clear fields
                registerUsernameField.setText("");
                registerPasswordField.setText("");
                registerPasswordField2.setText("");
                registerNameField.setText("");
                registerIDField.setText("");
            } catch (UserExistsException | IllegalArgumentException exp) {
                JOptionPane.showMessageDialog(new JFrame(), exp.getMessage(), "Registration failed!",
                        JOptionPane.ERROR_MESSAGE);
            }

        }));
    }

}
