package org.eshop.ui.gui;

import org.eshop.entities.User;
import org.eshop.shop.ShopFacade;
import org.eshop.shop.updateEventListener;
import org.eshop.ui.gui.listener.ShopCloseListener;
import org.eshop.ui.gui.panels.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GuiEmployee extends javax.swing.JFrame implements ActionListener, updateEventListener {
    ShopFacade shop;
    MenuePanel menuePanel;
    SidePanel sidePanel;
    ProductPanel productPanel;
    MitarbeiterPanel mitarbeiterPanel;
    org.eshop.ui.gui.panels.addProductPanel addProductPanel;
    org.eshop.ui.gui.panels.editProductPanel editProductPanel;
    org.eshop.ui.gui.panels.registerEmployeePanel registerEmployeePanel;

    /**
     * Der Konstruktor.
     */
    public GuiEmployee(ShopFacade shop, User loggedInUser, CustomerMenu.addLogoutListener logoutListener) {
        this.shop = shop;
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new ShopCloseListener(shop));
        addProductPanel = new addProductPanel(shop, loggedInUser);

        editProductPanel = new editProductPanel(shop, loggedInUser, addProductPanel);

        productPanel = new EmployeeCenterPanel(shop, editProductPanel, loggedInUser);


        JPanel paneelcenter = new JPanel();
        jPanel1.add(new JScrollPane(paneelcenter), BorderLayout.CENTER);
        paneelcenter.add(productPanel);
        paneelcenter.setLayout(new BoxLayout(paneelcenter, BoxLayout.PAGE_AXIS));


        mitarbeiterPanel = new MitarbeiterPanel(shop, this);
        sidePanel = new SidePanel(shop, loggedInUser);
        registerEmployeePanel = new registerEmployeePanel(shop, loggedInUser);
        sidePanel.add(addProductPanel);
        sidePanel.add(editProductPanel);
        sidePanel.add(registerEmployeePanel);
        addProductPanel.setVisible(true);
        editProductPanel.setVisible(false);
        registerEmployeePanel.setVisible(false);

        //jPanel1.add(sidePanel);

        jPanel1.add(sidePanel, BorderLayout.EAST);


        menuePanel = new MenuePanel(shop, productPanel, this, logoutListener, loggedInUser);

        jPanel1.add(menuePanel, BorderLayout.PAGE_START);

        paneelcenter.add(mitarbeiterPanel);
        mitarbeiterPanel.setVisible(false);


        // Das Panel zum aktiven, sichtbaren Inhalt des JFrame machen:
        this.getContentPane().add(jPanel1);
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
            }
            case "Mitarbeiterpanel" -> {
                mitarbeiterPanel.setVisible(true);
                productPanel.setVisible(false);
                registerEmployeePanel.setVisible(true);
                addProductPanel.setVisible(false);
                editProductPanel.setVisible(false);
            }
        }


    }


    @Override
    public void handleUpdate() {
        System.out.println("UPDATE!!!");
    }
}










