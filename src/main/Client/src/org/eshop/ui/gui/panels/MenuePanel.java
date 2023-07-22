package org.eshop.ui.gui.panels;


import org.eshop.entities.User;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.gui.CustomerMenu;
import org.eshop.ui.gui.GuiEmployee;
import org.eshop.ui.gui.MitarbeiterPanel;
import org.eshop.ui.gui.components.SearchWidget;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuePanel extends javax.swing.JPanel {


    private final javax.swing.JMenuBar jMenuBar =
            new javax.swing.JMenuBar();
    private final javax.swing.JButton Mitarbeiter =
            new javax.swing.JButton("Mitarbeiter");
    private final javax.swing.JFormattedTextField Search =
            new javax.swing.JFormattedTextField("Search");
    private final javax.swing.JMenuBar menu =
            new javax.swing.JMenuBar();
    private final javax.swing.JButton Produckte =
            new javax.swing.JButton("Produckte");
    private final javax.swing.JButton Logout =
            new javax.swing.JButton("Logout");
    ShopFacade shop;
    ProductPanel productPanel;
    MitarbeiterPanel mitarbeiterPanel;
    GuiEmployee guiEmployee;
    CustomerMenu.addLogoutListener listener;
    User loggedInUser;

    public MenuePanel(ShopFacade shop, SearchWidget.SearchListener searchListener, GuiEmployee guiEmployee, CustomerMenu.addLogoutListener logoutListener, User loggedInUSer) {

        this.guiEmployee = guiEmployee;
        this.listener = logoutListener;
        this.loggedInUser = loggedInUSer;
        this.add(jMenuBar);
        this.setBackground(new Color(50));

        jMenuBar.setBackground(new Color(50));
        jMenuBar.setBorderPainted(true);
        jMenuBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        jMenuBar.add(menu);

        SearchWidget search = new SearchWidget(shop, searchListener);
        search.setBackground(new Color(50));
        menu.add(search);
        menu.add(Produckte);
        menu.add(Mitarbeiter, BorderLayout.LINE_START);
        Mitarbeiter.setActionCommand("Mitarbeiterpanel");
        Mitarbeiter.addActionListener(guiEmployee);


        //menu.add(Search);
        menu.setBackground(new Color(50));
        menu.setBorder(new EmptyBorder(10, 10, 10, 10));
        menu.add(Logout);

        Produckte.setActionCommand("Producktpanel");
        Produckte.addActionListener(guiEmployee);


        Logout.addActionListener((e)->{
            guiEmployee.dispose();
            listener.onLogout(loggedInUSer);
        });

        Search.setPreferredSize(new Dimension(300, 10));

    }


}
