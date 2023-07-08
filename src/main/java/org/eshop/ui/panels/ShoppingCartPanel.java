package org.eshop.ui.panels;

import org.eshop.entities.Customer;
import org.eshop.entities.Invoice;
import org.eshop.entities.Product;
import org.eshop.entities.User;
import org.eshop.exceptions.CheckoutFailed;
import org.eshop.shop.Shop;
import org.eshop.shop.ShopFacade;
import org.eshop.ui.frames.CheckOutFrame;
import org.eshop.ui.tables.TableListener;
import org.eshop.ui.tables.models.CartModel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;


public class ShoppingCartPanel extends JPanel implements TableListener {

    private final JButton checkoutButton = new JButton("Checkout");
    private final ShopFacade server;
    private final User loggedInUser;
    private final JTable shoppingCart = new JTable();
    private final JLabel totalprice = new JLabel();


    public ShoppingCartPanel(ShopFacade shop, User user) {
        server = shop;
        loggedInUser = user;
        setupUI();
        setupEvents();

    }

    private void setupUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(200, 500));
        JLabel title = new JLabel("Warenkorb");
        title.setFont(new Font("Arial", Font.PLAIN, 24));
        this.add(title);
        this.add(shoppingCart);
        this.add(totalprice);
        this.add(Box.createVerticalGlue());
        this.add(checkoutButton);
        this.add(Box.createRigidArea(new Dimension(5, 20)));
        updateCart();
    }

    private void setupEvents() {
        checkoutButton.addActionListener((actionEvent) -> {
            System.out.println("Checkout");
            try {
                Invoice i = server.getInvoice((Customer) loggedInUser);
                server.checkout((Customer) loggedInUser);
                new CheckOutFrame(i);
                updateCart();
            } catch (CheckoutFailed e) {
                JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
            }
        });

    }

    @Override
    public void updateCart() {
        Map<Product, Integer> GetCart = server.getCart((Customer) loggedInUser);
        shoppingCart.setModel(new CartModel(GetCart));
        checkoutButton.setEnabled(!GetCart.isEmpty());

        double total = 0;
        for (Map.Entry<Product, Integer> entry : GetCart.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }

        String price = String.format("%.2f", total);
        totalprice.setText(price);

    }

    @Override
    public void editProduct(Product p) {
        //Not needed
    }


    @Override
    public void viewGraph() {
        //Not needed

    }

}

