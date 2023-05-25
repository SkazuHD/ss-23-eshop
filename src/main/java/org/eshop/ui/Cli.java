package org.eshop.ui;

import org.eshop.entities.Customer;
import org.eshop.entities.Employee;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.exceptions.LoginFailed;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.exceptions.UserExistsException;
import org.eshop.shop.Shop;
import org.eshop.util.IoReader;

import java.util.Map;

/**
 * The type Cli.
 */
public class Cli {

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
    User loggedInUser;

    /**
     * Instantiates a new Cli.
     */
    public Cli() {
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
        } catch (UserExistsException e) {
            System.err.println(e.getMessage());
            System.err.flush();
        }
    }

    /**
     * register employee
     */
    protected void registerEmployee() {
        //GET USERNAME
        String username = reader.readLine("Enter Username:");
        //GET PERSONR
        int persoNr = reader.getNumericInput("Enter PersoNr:");
        //GET NAME
        String name = reader.readLine("Enter Name:");
        //GET PWD
        String password = reader.readLine("Enter Password:");

        try {
            server.registerEmployee(username, persoNr, name, password);
        } catch (UserExistsException e) {
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
        // Login Customer First else try Employee login
        // throws LoginFailed if no user is found with the given username
        // Potential ERROR: if a customer and an employee have the same username -> Customer is logged in
        try {
            loggedInUser = server.loginUser(username, password);
            loggedIn = loggedInUser.isLoggedIn();
        } catch (LoginFailed e) {
            System.err.println(e.getMessage());
            System.err.flush();
        }
    }

    /**
     * Show products.
     */
    protected void showProducts() {
        System.out.println("ID    Preis      Name            Bestand");
        System.out.println("----------------------------------------");
        server.getProductSet().forEach(System.out::println);
    }

    /**
     * Buy products.
     */
    protected void buyProducts() {
        String name = reader.readLine("Product Name: ");
        System.out.print("Quantity: ");
        int quantity = reader.getNumericInput("");
        try {
            server.addProductToCart(name, quantity, (Customer) loggedInUser);
        } catch (ProductNotFound | NotInStockException e) {
            System.err.println(e.getMessage());
            System.err.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Remove product.
     *
     * @throws ProductNotFound the product not found
     */
    protected void removeProduct() throws ProductNotFound {
        String name = reader.readLine("Prouct Name: ");
        System.out.print("Quantity: ");
        int quantity = reader.getNumericInput("");
        server.removeProductFromCart(name, quantity, (Customer) loggedInUser);
    }

    /**
     * add new product
     */
    protected void addProduct() {
        int packsize = 0;
        String name = reader.readLine("Product Name:");
        int quantity = reader.getNumericInput("Quantity:");
        double price = server.getProduct(name) == null ? reader.getDoubleInput("Price:") : server.getProduct(name).getPrice();
        char Massenprodukt;

        do {
             Massenprodukt = reader.readLine("Massenprodukt (j/n)? ").charAt(0);

        } while (Massenprodukt != 'j' && Massenprodukt != 'n');
        if (Massenprodukt == 'j') {
            packsize = reader.getNumericInput("packsize: ");
        } else if (Massenprodukt == 'n') {
            packsize = 0;
        }
        server.addProduct(name, price, quantity, (Employee) loggedInUser, packsize);
    }

    /**
     * remove product from stock
     */
    protected void deleteProduct() {
        String name = reader.readLine("Product Name: ");
        System.out.print("Quantity: ");
        int quantity = reader.getNumericInput("");
        server.removeProduct(name, quantity, loggedInUser);
    }


    /**
     * Show cart.
     */
    protected void showCart() {
        Map<Products, Integer> cart = server.getCart((Customer) loggedInUser);
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
        if (loggedInUser instanceof Customer) {
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
                shoppingCartMenu();
            }
            case 4 -> {
                loggedIn = server.logOutUser(loggedInUser);
                loggedInUser = null;
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
        System.out.println("EMPLOYEE MENU");
        System.out.println("-------------");
        System.out.println("1. Register new Employee");
        System.out.println("2. View Products");
        System.out.println("3. Add Product");
        System.out.println("4. Remove Product");
        System.out.println("5. Logout");

        int input = reader.getNumericInput("Enter Selection: ");
        switch (input) {
            case 1 -> registerEmployee();
            case 2 -> showProducts();
            case 3 -> addProduct();
            case 4 -> deleteProduct();
            case 5 -> {
                loggedIn = server.logOutUser(loggedInUser);
                loggedInUser = null;
                startMenu();
            }
            default -> {
                System.err.println("Invalid Selection!");
                employeeMenu();
            }
        }

        employeeMenu();

    }


    /**
     * Shopping cart menu.
     */
    protected void shoppingCartMenu() {


        System.out.println("1. Remove Product from Cart");
        System.out.println("2. Checkout ");
        System.out.println("3. Exit");


        int input = reader.getNumericInput("Enter Selection: ");

        switch (input) {


            case 1 -> {
                try {
                    removeProduct();
                } catch (ProductNotFound e) {
                    System.err.println(e.getMessage());
                    System.err.flush();
                }
            }
            case 2 -> {
                String invoice = server.getInvoice((Customer) loggedInUser);
                System.out.println(invoice);
                server.checkout((Customer) loggedInUser);
                customerMenu();
            }
            case 3 -> {
                return;
            }

            default -> {
                System.err.println("Invalid Selection!");
                customerMenu();
            }
        }

        shoppingCartMenu();
    }

}
