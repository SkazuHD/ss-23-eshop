package org.eshop.ui.frames;

import org.eshop.shop.ShopFacade;
import org.eshop.ui.panels.GraphPanel;

import javax.swing.*;

public class GraphFrame extends JFrame {
    ShopFacade shop;
    int productID;
    int interval;
    public GraphFrame(ShopFacade shop, int productID, int interval) {
        super("Product History");

        this.shop = shop;
        this.productID = productID;
        this.interval = interval;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupUI();
        setupEvents();


        this.pack();
        setVisible(true);
    }

    private void setupUI() {
        int[] data = shop.getProductHistory(this.productID, this.interval);
        int max = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        int[] dt = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            dt[i] = i;
        }
        this.add(new GraphPanel(dt, data , max));
    }
    private void setupEvents() {

    }

}
