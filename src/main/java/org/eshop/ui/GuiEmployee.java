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

//            jPanel1.add(Produkte,java.awt.BorderLayout.CENTER);
            menuePanel = new MenuePanel(shop ,producktPanel);
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




