package org.eshop.ui;


import org.eshop.shop.Shop;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuePanel extends javax.swing.JPanel {


    private javax.swing.JMenuBar jMenuBar =
            new javax.swing.JMenuBar();
    private javax.swing.JButton Mitarbeiter =
            new javax.swing.JButton( "Mitarbeiter" );
    private javax.swing.JFormattedTextField Search =
            new javax.swing.JFormattedTextField("Search");
    private javax.swing.JMenuBar menu =
            new javax.swing.JMenuBar();

    private javax.swing.JButton Produckte =
            new javax.swing.JButton("Produckte");

    private javax.swing.JButton Logout =
            new  javax.swing.JButton("Logout");

        Shop shop;

        public MenuePanel (Shop shop){




            this.add(jMenuBar);
            this.setBackground(new Color(50));

            jMenuBar.setBackground(new Color(50));
            jMenuBar.setBorderPainted(true);
            jMenuBar.setBorder(new EmptyBorder(10,10,10,10));
            jMenuBar.add(menu);



            menu.add(Mitarbeiter,BorderLayout.LINE_START);
            menu.add(Search);
            menu.setBackground(new Color(50));
            menu.setBorder(new EmptyBorder(10,10,10,10));
            menu.add(Logout);
            menu.add(Produckte);

            Search.setPreferredSize(new Dimension(300,10));

        }
}
