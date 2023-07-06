package org.eshop.exceptions;

import org.eshop.entities.Product;

import java.util.List;

public class CheckoutFailed extends Exception{

    List<Product> products;
    public CheckoutFailed(List<Product> products){
        super("CHECKOUT NOT POSSIBLE THE FOLLOWING PRODUCTS NEED DO BE ADJUSTED");
        this.products = products;
    }

    public List<Product> getList(){
        return products;
    }

}
