package org.eshop.ui;

import org.eshop.entities.Customer;
import org.eshop.entities.Products;
import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;
import org.eshop.shop.Shop;
import org.eshop.util.IoReader;

import java.util.Map;

/**
 * The type Cli.
 */
public class cli {

    /**
     * The Server.
     */
    Shop server;

    /**
     * The Reader custom IoReader.
     */
    IoReader reader = new IoReader();
    /**
     * The Logged in Status.
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
     * Show products.
     */
    protected void showProducts() {
        server.getProductSet().forEach(System.out::println);
    }

    /**
     * Buy products.
     */
    protected void buyProducts() {
        String name = reader.readLine("Prouct Name: ");
        System.out.print("Quantity: ");
        int quantity = reader.getNumericInput("");
        server.addProductToCart(name, quantity, loggedInCustomer);
    }

    /**
     * Remove product.
     */
    protected void removeProduct() {
        String name = reader.readLine("Prouct Name: ");
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

    /**
     * Start menu.
     */
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

    /**
     * Customer menu.
     */
    protected void customerMenu() {
        System.out.println("1. View Products");
        System.out.println("2. Buy Products");
        System.out.println("3. View Cart");

        System.out.println("4. Logout");

        int input = reader.getNumericInput("Enter Selection: ");

        switch (input) {
            case 1 -> showProducts();
            case 2 -> buyProducts();
            case 3 -> {
                showCart();
                productMenu();
            }


            case 4 -> {
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

    /**
     * Employee menu.
     */
    protected void employeeMenu() {
        //TODO: Implement Employee Menu
        System.out.println("EMPLOYEE MENU");
        System.out.println("1. Register new Employee");
        System.out.println("2. View Products");
        System.out.println("3. Add Product");
        System.out.println("4. Remove Product");
        System.out.println("5. Logout");

        int input = reader.getNumericInput("Enter Selection: ");
        //TODO IMPLEMENT EMPLOYEE MENU OPTIONS
        switch (input) {
            case 1 -> {
                //registerUser();
            }
            case 2 -> showProducts();
            case 3 -> {
                //addProduct();
            }
            case 4 -> {
                //removeProduct();
            }
            case 5 -> {
                loggedIn = false;
                //TODO USE GENERIC USER CLASS
                loggedInCustomer = null;
                startMenu();
            }
            default -> {
                System.err.println("Invalid Selection!");
                employeeMenu();
            }
        }

        employeeMenu();

    }

    protected void productMenu() {


        System.out.println("1. Remove Product from Cart");
        System.out.println("2. Exit ");
        System.out.println("3. Logout ");


        int input = reader.getNumericInput("Enter Selection: ");

        switch (input) {


            case 1 -> removeProduct();
            case 2 -> customerMenu();
            case 3 -> {
                loggedIn = false;
                loggedInCustomer = null;
                startMenu();
            }

            default -> {
                System.err.println("Invalid Selection!");
                customerMenu();
            }
        }

        productMenu();
    }

}
