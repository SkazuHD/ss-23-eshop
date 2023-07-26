package org.eshop.ui.gui;

import org.eshop.entities.User;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.gui.components.SearchWidget;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomerMenu extends JPanel {
    private final JMenuBar jMenuBar =
            new JMenuBar();
    private final JFormattedTextField Search = new JFormattedTextField("Search");
    private final JMenuBar Menu = new JMenuBar();

    private final JButton Logout = new JButton("Logout");
    
    GuiCustomer guiCustomer;
    addLogoutListener listener;

    public CustomerMenu(ShopFacade shop, SearchWidget.SearchListener searchListener, GuiCustomer guiCustomer, addLogoutListener LogoutListener, User loggedInUSer) {
        this.guiCustomer = guiCustomer;
        this.add(jMenuBar);
        this.setBackground(new Color(50));
        this.listener = LogoutListener;

        jMenuBar.setBackground(new Color(50));
        jMenuBar.setBorderPainted(true);
        jMenuBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        jMenuBar.add(Menu);

        SearchWidget search = new SearchWidget(shop, searchListener);
        search.setBackground(new Color(50));
        Menu.add(search);

        Menu.setBackground(new Color(50));
        Menu.setBorder(new EmptyBorder(10, 10, 10, 10));


        Menu.add(Logout);
        Logout.addActionListener((e) -> {
            guiCustomer.dispose();
            listener.onLogout(loggedInUSer);
        });

        Search.setPreferredSize(new Dimension(300, 10));
    }

    public interface addLogoutListener {
        void onLogout(User user);
    }
}
