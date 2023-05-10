package org.eshop.ui;

import org.eshop.entities.Customer;
import org.eshop.entities.Products;
import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;
import org.eshop.shop.Shop;

import java.util.Map;
import java.util.Scanner;

/**
 * The type Cli.
 */
public class cli {

    /**
     * The Server.
     */
    Shop server;
    /**
     * The In.
     */
    Scanner in = new Scanner(System.in);
    /**
     * The Logged in.
     */
    boolean loggedIn = false;
    /**
     * The Logged in customer.
     */
    Customer loggedInCustomer;

    /**
     * Instantiates a new Cli.
     */
    public cli() {
        server = new Shop();
        run();
    }

    /**
     * Run.
     */
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

    /**
     * Show start menu.
     */
    public void showStartMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.flush();
    }

    /**
     * Select from menu.
     *
     * @param input the input
     */
    public void selectFromMenu(int input) {
        switch (input) {
            case 1 -> loginUser();
            case 2 -> registerUser();
            default -> System.err.println("Invalid Selection!");
        }
    }

    /**
     * Select from main menu.
     *
     * @param input the input
     */
    public void selectFromMainMenu(int input) {
        switch (input) {
            case 1 -> showProducts();
            case 2 -> buyProducts();
            case 3 -> showCart();
            case 4 -> removeProduct();
            default -> System.err.println("Invalid Selection!");
        }
    }

    /**
     * Gets numeric input.
     *
     * @return the numeric input
     */
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

    /**
     * Register user.
     */
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

    /**
     * Login user.
     */
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

    /**
     * Show main menu.
     */
    protected void showMainMenu() {
        System.out.println("1. View Products");
        System.out.println("2 Buy Products");
        System.out.println("3. View Cart");
        System.out.println("4. Remove Product from Cart");
        System.out.println("5. Logout");

    }

    /**
     * Show products.
     */
    protected void showProducts() {
        server.getProductSet().forEach(System.out::println);
    }

    /**
     * Buy products.
     */
    protected void buyProducts() {
        System.out.print("Prouct Name: ");
        String name = in.nextLine();
        System.out.print("Quantity: ");
        int quantity = getNumericInput();
        server.addProductToCart(name, quantity, loggedInCustomer);
    }

    /**
     * Remove product.
     */
    protected void removeProduct() {
        System.out.print("Prouct Name: ");
        String name = in.nextLine();
        System.out.print("Quantity: ");
        int quantity = getNumericInput();
        server.removeProductFromCart(name, quantity, loggedInCustomer);
    }

    /**
     * Show cart.
     */
    protected void showCart() {
        Map<Products, Integer> cart = server.getCart(loggedInCustomer);
        if (cart.size() == 0) {
            System.out.println("Cart is Empty!");
            return;
        }
        cart.forEach((k, v) -> System.out.println(k + " " + v));
    }

}
