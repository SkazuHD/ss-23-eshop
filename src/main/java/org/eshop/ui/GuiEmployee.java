package org.eshop.ui;

import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.listener.ShopCloseListener;
import org.eshop.ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GuiEmployee extends javax.swing.JFrame implements ActionListener {
    private final javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
    private final javax.swing.JScrollPane scrollPane =
            new javax.swing.JScrollPane();
    private final javax.swing.JPanel Paneelcenter =
            new javax.swing.JPanel();
    ShopFacade shop;
    MenuePanel menuePanel;
    SidePanel sidePanel;
    ProductPanel productPanel;
    MitarbeiterPanel mitarbeiterPanel;
    addProductPanel addProductPanel;
    editProductPanel editProductPanel;
    registerEmployeePanel registerEmployeePanel;

    /**
     * Der Konstruktor.
     */
    public GuiEmployee(ShopFacade shop, User loggedInUser, CustomerMenu.addLogoutListener logoutListener) {
        this.shop = shop;
        jPanel1.setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new ShopCloseListener(shop));
        addProductPanel = new addProductPanel(shop, loggedInUser);

        editProductPanel = new editProductPanel(shop, loggedInUser, addProductPanel);

        productPanel = new EmployeeCenterPanel(shop, editProductPanel, loggedInUser);


        jPanel1.add(new JScrollPane(Paneelcenter), BorderLayout.CENTER);
        Paneelcenter.add(productPanel);
        Paneelcenter.setLayout(new BoxLayout(Paneelcenter, BoxLayout.PAGE_AXIS));


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

        Paneelcenter.add(mitarbeiterPanel);
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
            case "Producktpanel":
                productPanel.setVisible(true);
                mitarbeiterPanel.setVisible(false);
                addProductPanel.setVisible(true);
                editProductPanel.setVisible(false);
                registerEmployeePanel.setVisible(false);


                break;
            case "Mitarbeiterpanel":
                mitarbeiterPanel.setVisible(true);
                productPanel.setVisible(false);
                registerEmployeePanel.setVisible(true);
                addProductPanel.setVisible(false);
                editProductPanel.setVisible(false);


                break;

        }


    }


}










