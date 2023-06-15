package org.eshop.ui;
import org.eshop.entities.Products;
import org.eshop.shop.Shop;

import javax.swing.*;
import java.awt.*;
// Import-Anweisung für unser JLabel

public class GuiEmployee extends javax.swing.JFrame {
        private javax.swing.JPanel jPanel1 = new javax.swing.JPanel();

        Shop shop;

       MenuePanel menuePanel;

       SidePanel sidePanel;
       ProducktPanel producktPanel;

   private javax.swing.JScrollPane scrollPane =
           new javax.swing.JScrollPane();

    /**
         * Der Konstruktor.
         */
        public GuiEmployee(Shop shop) {
            this.shop = shop;
            jPanel1.setLayout( new BorderLayout() );
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            producktPanel = new ProducktPanel(shop);
            jPanel1.add(producktPanel, BorderLayout.CENTER);

            sidePanel = new SidePanel(shop);
            //jPanel1.add(sidePanel);

            jPanel1.add(sidePanel, BorderLayout.EAST);


                jPanel1.add(pannel,java.awt.BorderLayout.LINE_END);
                pannel.setPreferredSize(new Dimension(400,2));

                 jPanel1.add(pannel1, java.awt.BorderLayout.PAGE_START);
                 pannel1.add(jMenuBar);


                pannel1.setBackground(new Color(50));

                jMenuBar.setBackground(
                        new Color(50)
                );
                jMenuBar.setBorderPainted(true);
                jMenuBar.setBorder(new EmptyBorder(10,10,10,10));
               jMenuBar.add(menu);

                menu.add(Mitarbeiter,BorderLayout.LINE_START);
                menu.add(Search);
                menu.setBackground(new Color(50));
                //menu.setBorderPainted(true);
                menu.setBorder(new EmptyBorder(10,10,10,10));
                Search.setPreferredSize(new Dimension(300,10));
                menu.add(Logout);
                menu.add(Produckte);

                Produkte.setListData(shop.getProductSet().toArray());
//            jPanel1.add(Produkte,java.awt.BorderLayout.CENTER);
            menuePanel = new MenuePanel(shop);
            jPanel1.add(menuePanel, BorderLayout.PAGE_START);
                // Das Panel zum aktiven, sichtbaren Inhalt des JFrame machen:
                this.getContentPane().add ( jPanel1 ) ;
                // Alle Elemente auf kleinstmögliche Größe bringen
                pack();

                this.setVisible(true);

         // menuePanel = new MenuePanel(menuePanel.guiEmployee);
          //sidePanel = new SidePanel(sidePanel.guiEmployee);


        }











}




