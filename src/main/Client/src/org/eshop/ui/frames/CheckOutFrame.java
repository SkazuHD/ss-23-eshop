package org.eshop.ui.frames;

import org.eshop.entities.Customer;
import org.eshop.entities.Invoice;
import org.eshop.ui.tables.models.CartModel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class CheckOutFrame extends JFrame {
    private final javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
    private final JTable Cart = new JTable();
    private final JLabel Date2 = new JLabel();
    private final JLabel price2 = new JLabel();
    private final JPanel customer2 = new JPanel();
    CartModel cartModel;


    public CheckOutFrame(Invoice invoice) {
        this.add(jPanel1);
        jPanel1.setPreferredSize(new Dimension(300, 300));
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));

        cartModel = new CartModel(invoice.getCart());
        invoice.getCart();
        invoice.getDate();
        invoice.getTotalPrice();
        invoice.getCustomer();

        //getDate = new Date(invoice.getDate());
        Date date = invoice.getDate();
        jPanel1.add(Date2, BorderLayout.PAGE_START);
        Date2.setText(date.toString());


        jPanel1.add(Cart, BorderLayout.CENTER);
        Cart.setModel(cartModel);


        String price = String.format("%.2f", invoice.getTotalPrice());
        jPanel1.add(price2, BorderLayout.LINE_END);
        price2.setText(price);

        Customer customer = invoice.getCustomer();
        jPanel1.add(customer2, BorderLayout.PAGE_END);
        customer2.add(new JLabel(customer.getName()));
        customer2.add(new JLabel(customer.getAddress()));
        customer2.add(new JLabel(customer.getID()));


        this.pack();
        this.setVisible(true);
    }
}
