package org.eshop.ui.gui.components;

import org.eshop.entities.Employee;
import org.eshop.entities.Product;
import org.eshop.network.Client;
import org.eshop.shop.ShopFacade;
import org.eshop.shop.updatable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

public class SearchWidget extends JPanel implements ActionListener, updatable {



    private final ShopFacade shop;
    private final SearchListener listener;
    private final JLabel searchLabel = new JLabel("Search");
    private final JTextField searchField = new JTextField();
    private final JButton searchButton = new JButton("Search");

    public SearchWidget(ShopFacade shop, SearchListener listener) {
        super();
        this.shop = shop;
        this.listener = listener;
        Client server = (Client) shop;
        server.getUpdateInterface().addClient(this, "products");
        setupUI();
        setupEvents();
    }

    private void setupUI() {
        //Setup UI
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(500, 50));

        this.add(searchLabel);
        this.add(searchField);
        this.setMaximumSize(new Dimension(500, 30));
        this.add(searchButton);

        
    }

    private void setupEvents() {
        //Setup Events
        searchButton.addActionListener(this);
        searchField.getDocument().addDocumentListener(new SearchFieldListener());

    }

    /* Input aus dem Feld wird betrachtete
    *  Ist der Input  kleiner 3 oder leer = ja = alle Produkte werden gezeigt / Ansosnten wird nach dem Prod. gesucht */
    private void search() {
        String query = searchField.getText();
        if (query.isEmpty() || query.isBlank() || query.length() < 3) {
            Collection<Product> result = shop.getAllProducts();
            listener.onSearch(result.stream().toList());
        } else {
            List<Product> result = shop.findProducts(query);
            listener.onSearch(result);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //On click of search button
        search();
    }

    @Override
    public void update(String keyword) {
        if (keyword.equals("products")) {
            search();
        }
    }

    public interface SearchListener {
        void onSearch(List<Product> result);

    }

    private class SearchFieldListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            search();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            search();

        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            //Does not trigger
            search();
        }
    }
}
