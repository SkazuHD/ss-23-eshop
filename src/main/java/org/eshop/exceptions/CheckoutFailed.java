package org.eshop.exceptions;

import org.eshop.entities.Products;

import java.util.List;

public class CheckoutFailed extends Exception{

    List<Products> products;
    public CheckoutFailed(List<Products> products){
        super("CHECKOUT NOT POSSIBLE THE FOLLOWING PRODUCTS NEED DO BE ADJUSTED");
        this.products = products;
    }

    public List<Products> getList(){
        return products;
    }

}
