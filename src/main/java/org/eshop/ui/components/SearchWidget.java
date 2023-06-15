package org.eshop.ui.components;

import org.eshop.entities.Products;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchWidget extends JPanel implements ActionListener {

    public interface SearchListener {
        void onSearch(List<Products> result);
    }
    private final JLabel searchLabel = new JLabel("Search");
    private JTextField searchField = new JTextField();
    private final JButton searchButton = new JButton("Search");

    public SearchWidget() {
        super();
        setupUI();
        setupEvents();
    }

    private void setupUI() {
        //Setup UI
    }
    private void setupEvents() {
        //Setup Events

        //TODO QOL Add onInutChange event
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       //On click of search button
    }
}
