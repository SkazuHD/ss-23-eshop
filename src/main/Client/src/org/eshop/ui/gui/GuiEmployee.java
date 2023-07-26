package org.eshop.ui.gui;

import org.eshop.entities.User;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.gui.listener.ShopCloseListener;
import org.eshop.ui.gui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GuiEmployee extends javax.swing.JFrame implements ActionListener {
    ShopFacade shop;
    MenuePanel menuePanel;
    SidePanel sidePanel;
    ProductPanel productPanel;
    org.eshop.ui.gui.panels.MitarbeiterPanel mitarbeiterPanel;
    org.eshop.ui.gui.panels.addProductPanel addProductPanel;
    org.eshop.ui.gui.panels.editProductPanel editProductPanel;
    org.eshop.ui.gui.panels.registerEmployeePanel registerEmployeePanel;
    JPanel eventPanel;


    /**
     * Der Konstruktor.
     */
    public GuiEmployee(ShopFacade shop, User loggedInUser, CustomerMenu.addLogoutListener logoutListener) {
        this.shop = shop;
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new ShopCloseListener(shop));
        addProductPanel = new addProductPanel(shop, loggedInUser);

        editProductPanel = new editProductPanel(shop, loggedInUser, addProductPanel);

        productPanel = new EmployeeCenterPanel(shop, editProductPanel, loggedInUser);

        eventPanel = new eventPanel(shop);
        eventPanel.setVisible(false);
        JPanel paneelcenter = new JPanel();
        this.add(paneelcenter, BorderLayout.CENTER);
        paneelcenter.add(productPanel);
        paneelcenter.add(eventPanel);
        paneelcenter.setLayout(new BoxLayout(paneelcenter, BoxLayout.PAGE_AXIS));


        String[] columns = {"ID", "Name", "Username"};
        mitarbeiterPanel = new MitarbeiterPanel(this.shop, loggedInUser, columns);
        sidePanel = new SidePanel();
        registerEmployeePanel = new registerEmployeePanel(shop);
        sidePanel.add(addProductPanel);
        sidePanel.add(editProductPanel);
        sidePanel.add(registerEmployeePanel);
        addProductPanel.setVisible(true);
        editProductPanel.setVisible(false);
        registerEmployeePanel.setVisible(false);


        this.add(sidePanel, BorderLayout.EAST);


        menuePanel = new MenuePanel(shop, productPanel, this, logoutListener, loggedInUser);

        this.add(menuePanel, BorderLayout.PAGE_START);

        paneelcenter.add(mitarbeiterPanel);
        mitarbeiterPanel.setVisible(false);


        // Das Panel zum aktiven, sichtbaren Inhalt des JFrame machen:

        // Alle Elemente auf kleinstmögliche Größe bringen
        pack();

        this.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        productPanel.setVisible(false);
        mitarbeiterPanel.setVisible(false);
        switch (e.getActionCommand()) {
            case "Producktpanel" -> {
                productPanel.setVisible(true);
                mitarbeiterPanel.setVisible(false);
                addProductPanel.setVisible(true);
                editProductPanel.setVisible(false);
                registerEmployeePanel.setVisible(false);
                eventPanel.setVisible(false);

            }
            case "Mitarbeiterpanel" -> {
                mitarbeiterPanel.setVisible(true);
                productPanel.setVisible(false);
                registerEmployeePanel.setVisible(true);
                addProductPanel.setVisible(false);
                editProductPanel.setVisible(false);
                eventPanel.setVisible(false);

            }
            case "Eventpanel" -> {
                mitarbeiterPanel.setVisible(false);
                productPanel.setVisible(false);
                registerEmployeePanel.setVisible(false);
                addProductPanel.setVisible(false);
                editProductPanel.setVisible(false);
                eventPanel.setVisible(true);
            }
        }


    }

}










