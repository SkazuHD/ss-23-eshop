package org.eshop.entities;

import java.util.Date;

public class Invoice {
    Customer c;
    Date date = new Date();

    public Invoice(Customer c) {
        this.c = c;
    }


}
