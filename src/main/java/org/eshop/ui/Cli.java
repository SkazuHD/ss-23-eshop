package org.eshop.ui;

import org.eshop.entities.Customer;
import org.eshop.entities.MassProducts;
import org.eshop.entities.Products;
import org.eshop.entities.User;
import org.eshop.exceptions.LoginFailed;
import org.eshop.exceptions.NotInStockException;
import org.eshop.exceptions.ProductNotFound;
import org.eshop.exceptions.UserExistsException;
import org.eshop.shop.Shop;
import org.eshop.util.IoReader;

import java.util.List;
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
        String username = reader.readLine("Enter Username:");
        int persoNr = reader.getNumericInput("Enter PersoNr:");
        String name = reader.readLine("Enter Name:");
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
        String name = reader.readLine("Product Name:");
        List<Products> result = server.findProducts(name);
        int id;
        if (result.size() > 1) {
            result.forEach(System.out::println);
            System.out.println("Multiple Products found, please select one by ID");
            id = reader.getNumericInput("Enter ID:");
        } else if (result.size() == 1) {
            id = result.get(0).getId();
        } else {
            System.err.println("No Product found");
            return;
        }
        int quantity = reader.getNumericInput("Quantity:");

        try {
            server.addProductToCart(id, quantity, (Customer) loggedInUser);
        } catch (ProductNotFound | NotInStockException e) {
            System.err.println(e.getMessage());
            System.err.flush();
        }
    }

    /**
     * Remove product from Customers cart.
     *
     * @throws ProductNotFound the product not found
     */
    protected void removeProduct() throws ProductNotFound {
        String name = reader.readLine("Product Name:");
        List<Products> result = server.findProducts(name);
        int id;
        if (result.size() > 1) {
            result.forEach(System.out::println);
            System.out.println("Multiple Products found, please select one by ID");
            id = reader.getNumericInput("Enter ID:");
        } else if (result.size() == 1) {
            id = result.get(0).getId();
        } else {
            System.err.println("No Product found");
            return;
        }
        int quantity = reader.getNumericInput("Quantity:");
        server.removeProductFromCart(id, quantity, (Customer) loggedInUser);
    }

    /**
     * add new product
     */
    protected void addProduct() {
        String name = reader.readLine("Product Name:");
        List<Products> result = server.findProducts(name);
        if (result.size() == 0) {
            createProduct(name);
        } else {
            result.forEach(System.out::println);
            System.out.println("1. Increase Existing");
            System.out.println("2. Create new Product");
            int ans = reader.getNumericInput("Selection:");
            if (ans == 1) {
                int id = reader.getNumericInput("Enter ID:");
                increaseQuantity(id);
            } else {
                createProduct(name);
            }
        }
    }

    /**
     * Increase quantity.
     *
     * @param id the id
     */
    protected void increaseQuantity(int id) {
        int quantity = reader.getNumericInput("Quantity:");
        Products p;
        try {
             p = server.findProduct(id);
        }catch (ProductNotFound e){
            System.out.println(e.getMessage());
            return;
        }
        if(p instanceof MassProducts){
            MassProducts mp = (MassProducts) p;
            if(quantity % ((MassProducts) p).getPacksize() !=0){
                System.err.println("Quantity not Matching Packsize");
                do {
                    quantity = reader.getNumericInput("Quantity");
                    if(quantity % mp.getPacksize() != 0){
                        System.err.println("Packsize not Matching Quantity");
                    }
                }while (quantity % mp.getPacksize() != 0);

            }
        }


        try {
            server.increaseQuantity(id, quantity, loggedInUser);
        } catch (ProductNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create product.
     *
     * @param name the name
     */
    protected void createProduct(String name) {
        String ans;
        do{
            ans = reader.readLine("Is Massproduct (y/n)");
            ans = ans.toLowerCase();
        }while (!ans.equals("y")|| !ans.equals("n"));
        int packsize = 0;
        int quantity;
        if(ans.equals("y")){
            packsize = reader.getNumericInput("Packsize:");
            do {
                quantity = reader.getNumericInput("Quantity");
                if(quantity % packsize != 0){
                    System.err.println("Packsize not Matching Quantity");
                }
            }while (quantity % packsize != 0);

        }else {
            quantity = reader.getNumericInput("Quantity");
        }
        double price = reader.getDoubleInput("Price:");

        if (ans.equals("y")){
            server.createMassProduct(name, price, quantity,packsize,loggedInUser);
        }else {
            server.createProduct(name, price, quantity, loggedInUser);

        }
    }

    /**
     * remove product from stock
     */
    protected void deleteProduct() {
        String name = reader.readLine("Product Name:");
        List<Products> result = server.findProducts(name);
        int id;
        if (result.size() > 1) {
            result.forEach(System.out::println);
            System.out.println("Multiple Products found, please select one by ID");
            id = reader.getNumericInput("Enter ID:");
        } else if (result.size() == 1) {
            id = result.get(0).getId();
        } else {
            System.err.println("No Product found");
            return;
        }
        int quantity = reader.getNumericInput("Quantity:");

        try {
            server.removeProduct(id, quantity, loggedInUser);

        } catch (ProductNotFound e) {
            System.err.println(e.getMessage());
            System.err.flush();
        }
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
