package org.eshop.ui;

import org.eshop.entities.Customer;
import org.eshop.entities.Products;
import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;
import org.eshop.shop.Shop;
import org.eshop.util.IoReader;

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
    IoReader reader = new IoReader();
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

        startMenu();
    }

    /**
     * Show start menu.
     */


    /**
     * Select from menu.
     *
     * @param input the input
     */


    /**
     * Select from main menu.
     *
     * @param input the input
     */


    /**
     * Gets numeric input.
     *
     * @return the numeric input
     */


    /**
     * Register user.
     */
    protected void registerUser() {
        //GET USERNAME
        String username = reader.readLine("Enter Username:");
        //GET PWD
        String password = reader.readLine("Enter Password:");
        //GET NAME
        String name = reader.readLine("Enter Name:");
        //GET ADDRESS
        String address = reader.readLine("Enter Address:");

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
        String username = reader.readLine("Enter Username:");
        //GET PWD
        String password = reader.readLine("Enter Password:");
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
        int quantity = reader.getNumericInput("");
        server.addProductToCart(name, quantity, loggedInCustomer);
    }

    /**
     * Remove product.
     */
    protected void removeProduct() {
        System.out.print("Prouct Name: ");
        String name = in.nextLine();
        System.out.print("Quantity: ");
        int quantity = reader.getNumericInput("");
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

    protected void startMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.flush();

        int input = reader.getNumericInput("Enter Selection: ");
        switch (input) {
            case 1 -> loginUser();
            case 2 -> registerUser();
            case 3 -> System.exit(0);
            default -> {
                System.err.println("Invalid Selection!");
                startMenu();
            }
        }
        if (!loggedIn) {
            startMenu();
        }
        if (loggedInCustomer instanceof Customer) {
            customerMenu();
        } else {
            employeeMenu();
        }

    }

    protected void customerMenu() {
        System.out.println("1. View Products");
        System.out.println("2. Buy Products");
        System.out.println("3. View Cart");
        System.out.println("4. Remove Product from Cart");
        System.out.println("5. Logout");

        int input = reader.getNumericInput("Enter Selection: ");

        switch (input) {
            case 1 -> showProducts();
            case 2 -> buyProducts();
            case 3 -> showCart();
            case 4 -> removeProduct();
            case 5 -> {
                loggedIn = false;
                loggedInCustomer = null;
                startMenu();
            }
            default -> {
                System.err.println("Invalid Selection!");
                customerMenu();
            }
        }

        customerMenu();

    }

    protected void employeeMenu() {

    }

}
