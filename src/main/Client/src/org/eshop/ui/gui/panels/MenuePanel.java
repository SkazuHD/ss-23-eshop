package org.eshop.ui.gui.panels;


import org.eshop.entities.User;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.gui.CustomerMenu;
import org.eshop.ui.gui.GuiEmployee;
import org.eshop.ui.gui.components.SearchWidget;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuePanel extends JPanel {


    private final JMenuBar jMenuBar = new JMenuBar();
    private final JButton Mitarbeiter = new JButton("Mitarbeiter");
    private final JFormattedTextField Search = new JFormattedTextField("Search");
    private final JMenuBar menu = new JMenuBar();
    private final JButton Produckte = new JButton("Produckte");
    private final JButton Events = new JButton("Events");
    private final JButton Logout = new JButton("Logout");
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
        menu.add(Events);
        menu.add(Mitarbeiter, BorderLayout.LINE_START);
        Mitarbeiter.setActionCommand("Mitarbeiterpanel");
        Mitarbeiter.addActionListener(guiEmployee);


        //menu.add(Search);
        menu.setBackground(new Color(50));
        menu.setBorder(new EmptyBorder(10, 10, 10, 10));
        menu.add(Logout);

        Produckte.setActionCommand("Producktpanel");
        Produckte.addActionListener(guiEmployee);

        Events.setActionCommand("Eventpanel");
        Events.addActionListener(guiEmployee);

        Logout.addActionListener((e) -> {
            guiEmployee.dispose();
            listener.onLogout(loggedInUSer);
        });

        Search.setPreferredSize(new Dimension(300, 10));

    }


}
