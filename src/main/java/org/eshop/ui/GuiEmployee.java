package org.eshop.ui;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.shop.Shop;
import org.eshop.ui.panels.addProductPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Import-Anweisung für unser JLabel
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;




public class GuiEmployee extends javax.swing.JFrame implements ActionListener {
    private javax.swing.JPanel jPanel1 = new javax.swing.JPanel();


    Shop shop;

    MenuePanel menuePanel;

    SidePanel sidePanel;
    ProducktPanel producktPanel;
    MitarbeiterPanel mitarbeiterPanel;

    private javax.swing.JScrollPane scrollPane =
            new javax.swing.JScrollPane();
    private javax.swing.JPanel Paneelcenter =
            new javax.swing.JPanel();

    /**
     * Der Konstruktor.
     */
    public GuiEmployee(Shop shop, User loggedInUser) {
        this.shop = shop;
        jPanel1.setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        producktPanel = new ProducktPanel(shop);



              jPanel1.add(new JScrollPane(Paneelcenter), BorderLayout.CENTER);
              Paneelcenter.add(producktPanel);


        mitarbeiterPanel = new MitarbeiterPanel(shop,this);
        sidePanel = new SidePanel(shop, loggedInUser);
        sidePanel.add(new addProductPanel(shop, loggedInUser));

        //jPanel1.add(sidePanel);

        jPanel1.add(sidePanel, BorderLayout.EAST);


        menuePanel = new MenuePanel(shop, producktPanel, this);

        jPanel1.add(menuePanel, BorderLayout.PAGE_START);

        Paneelcenter.add(mitarbeiterPanel);
        mitarbeiterPanel.setVisible(false);



        // Das Panel zum aktiven, sichtbaren Inhalt des JFrame machen:
        this.getContentPane().add(jPanel1);
        // Alle Elemente auf kleinstmögliche Größe bringen
        pack();

        this.setVisible(true);

        ;


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        producktPanel.setVisible(false);
        mitarbeiterPanel.setVisible(false);
        switch(e.getActionCommand()){
            case "Producktpanel":
            producktPanel.setVisible(true);
              mitarbeiterPanel.setVisible(false);


                break;
            case "Mitarbeiterpanel":
                mitarbeiterPanel.setVisible(true);
                producktPanel.setVisible(false);


                break;

        }


    }
}










