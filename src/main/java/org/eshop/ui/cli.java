package org.eshop.ui;

import org.eshop.entities.Customer;
import org.eshop.entities.Products;
import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;
import org.eshop.shop.Shop;

import java.util.Map;
import java.util.Scanner;

public class cli {

    Shop server;
    Scanner in = new Scanner(System.in);
    boolean loggedIn = false;
    Customer loggedInCustomer;

    public cli() {
        server = new Shop();
        run();
    }

    public void run() {
        do {
            showStartMenu();
            int input = getNumericInput();
            selectFromMenu(input);
        } while (!loggedIn);

        System.err.println("TODO MAKE DIFFERENT MENUS FOR CUSTOMER AND EMPLOYEE");
        do {
            showMainMenu();
            int input = getNumericInput();
            selectFromMainMenu(input);
        } while (loggedIn);

        run();
    }

    public void showStartMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.flush();
    }

    public void selectFromMenu(int input) {
        switch (input) {
            case 1 -> loginUser();
            case 2 -> registerUser();
            default -> System.err.println("Invalid Selection!");
        }
    }

    public void selectFromMainMenu(int input) {
        switch (input) {
            case 1 -> showProducts();
            case 2 -> buyProducts();
            case 3 -> showCart();
            case 4 -> System.out.println("TODO LOGOUT");
            default -> System.err.println("Invalid Selection!");
        }
    }

    public int getNumericInput() {
        String input;
        int value = 0;
        input = in.nextLine();
        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.err.println("Please enter a Numeric Value");
            System.err.flush();
            getNumericInput();
        }
        return value;

    }

    protected void registerUser() {
        //GET USERNAME
        System.out.print("Enter Username:");
        String username = in.nextLine();
        //GET PWD
        System.out.print("Enter Password:");
        String password = in.nextLine();

        //GET NAME
        System.out.print("Enter Name:");
        String name = in.nextLine();

        //GET ADDRESS
        System.out.print("Enter Address:");
        String address = in.nextLine();

        try {
            server.registerUser(username, password, name, address);
        } catch (CustomerExistsException e) {
            System.err.println(e.getMessage());
            System.err.flush();
        }
    }

    protected void loginUser() {
        //GET USERNAME
        System.out.print("Enter Username:");
        String username = in.nextLine();
        //GET PWD
        System.out.print("Enter Password:");
        String password = in.nextLine();
        try {
            loggedInCustomer = server.loginUser(username, password);
            loggedIn = true;
        } catch (CustomerLoginFailed e) {
            System.err.println(e.getMessage());
            System.err.flush();
        }
    }

    protected void showMainMenu() {
        System.out.println("1. View Products");
        System.out.println("2 Buy Products");
        System.out.println("3. View Cart");
        System.out.println("4. View Orders");
        System.out.println("5. Logout");

    }

    protected void showProducts() {
        server.getProductSet().forEach(System.out::println);
    }

    protected void buyProducts() {
        System.out.println("Prouct Number: ");
        String name = in.nextLine();
        System.out.println("Quantity: ");
        int quantity = getNumericInput();
        server.addProductToCart(name, quantity, loggedInCustomer);
    }

    protected void showCart() {
        Map<Products, Integer> cart = server.getCart(loggedInCustomer);

        cart.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
