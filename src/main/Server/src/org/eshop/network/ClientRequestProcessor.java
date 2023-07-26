package org.eshop.network;


import org.eshop.entities.*;
import org.eshop.exceptions.*;
import org.eshop.shop.Shop;
import org.eshop.shop.UpdateInterface;
import org.eshop.shop.updatable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class ClientRequestProcessor implements Runnable, updatable {
//Wie funktioniert der Client Request Processor nochmal also warum sind hier die sachen die geschikt werden und die antworten dirnn??
    private final Shop server;
    private final Socket clientSocket;// Socket classe von Java nochmal erklären was der Macht und wo sind die Streams???
    private BufferedReader in;      /*Reads text from a character-input stream, buffering
    characters so as to provide for the efficient reading of characters, arrays, and lines. */
    private PrintStream out;
    private final UpdateInterface eshopServer;


    public ClientRequestProcessor(Socket socket, Shop server, UpdateInterface eshopServer) {
        this.clientSocket = socket;
        this.server = server;
        this.eshopServer = eshopServer;

        try {
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            this.out = new PrintStream(this.clientSocket.getOutputStream());
        } catch (IOException var6) {
            try {
                this.clientSocket.close();
            } catch (IOException ignore) {
            }

            System.err.println("Ausnahme bei Bereitstellung des Streams: " + var6);
            return;
        }


        String address = String.valueOf(this.clientSocket.getInetAddress());
        System.out.println("Verbunden mit Client " + address + ":" + this.clientSocket.getPort());
    }

    public void run() {
        String input;
        this.out.println("Server bereit");

        do {
            input = null;
            try {
                input = this.in.readLine();
            } catch (Exception var5) {
                System.out.println("--->Fehler beim Lesen vom Client (Aktion): ");
                System.out.println(var5.getMessage());

            }

            if (input == null) {
                break;
            }
            System.out.println("Request: " + input);
            switch (input) {
                case "registerUser" -> {
                    registerUser();
                    System.out.println("Success");
                }
                case "regEmp" -> {
                    registerEmployee();
                    System.out.println("Success");
                    eshopServer.notifyClients("employee");

                }
                case "login" -> {
                    login();
                    System.out.println("Success");
                }
                case "logout" -> {
                    logout();
                    System.out.println("Success");
                }
                case "createProd" -> {
                    createProduct();
                    System.out.println("Success");
                    eshopServer.notifyClients("products");
                }
                case "createMProd" -> {
                    createMProduct();
                    System.out.println("Success");
                    eshopServer.notifyClients("products");
                }
                case "editProd" -> {
                    editProduct();
                    System.out.println("Success");
                    eshopServer.notifyClients("products");
                }
                case "editMProd" -> {
                    editMProduct();
                    System.out.println("Success");
                    eshopServer.notifyClients("products");
                }
                case "changeQuant" -> {
                    changeQuantity();
                    System.out.println("Success");
                    eshopServer.notifyClients("products");
                    eshopServer.notifyClients("event");
                }
                case "getAll" -> {
                    getAll();
                    System.out.println("Success");
                }
                case "findName" -> {
                    findName();
                    System.out.println("Success");
                }
                case "findId" -> {
                    findId();
                    System.out.println("Success");
                }
                case "prodHistory" -> {
                    getProdHistory();
                    System.out.println("Success");
                }
                case "getUser" -> {
                    getUser();
                    System.out.println("Success");
                }
                case "getInvoice" -> {
                    getInvoice();
                    System.out.println("Success");
                }
                case "checkout" -> {
                    checkout();
                    System.out.println("Success");
                    eshopServer.notifyClients("products");
                    eshopServer.notifyClients("event");
                }
                case "getCart" -> {
                    getCart();
                    System.out.println("Success");
                }
                case "addToCart" -> {
                    addToCart();
                    System.out.println("Success");
                }
                case "removeFromCart" -> {
                    removeFromCart();
                    System.out.println("Success");
                }
                case "getAllEmp" -> {
                    getAllEmp();
                    System.out.println("Success");
                }
                case "getAllEvents" -> {
                    getAllEvents();
                    System.out.println("Success");
                }
                case "clearCart" -> {
                    clearCart();
                    System.out.println("Success");
                }
                case "listen" -> eshopServer.addClient(this, "init");
                case "update" -> eshopServer.notifyClients("update");
                case "save" -> server.save();
                default -> System.out.println("Not valid " + input);
            }
        } while (!input.equals("Logout"));

        System.out.println("Verbindung zu " + this.clientSocket.getInetAddress() + ":" + this.clientSocket.getPort() + " durch Client abgebrochen");
        try {
            this.clientSocket.close();
        } catch (IOException ignore) {
        }
        eshopServer.removeClient(this);
    }

    private void getAllEmp() {
        Collection<Employee> employees = server.getAllEmployees();
        this.out.println(200);
        this.out.println(employees.size());
        for (Employee e : employees) {
            returnEmployee(e);
        }

    }

    public void getUser() {
        //NOT NEEDED FOR COMMUNICATION
    }

    public void registerUser() {
        String username = null;
        String password;
        String name;
        String address;
        try {
            username = this.in.readLine();
            password = this.in.readLine();
            name = this.in.readLine();
            address = this.in.readLine();
            server.registerUser(username, password, name, address);
            this.out.println(200);

        } catch (IOException ignore) {
            //Something fucked up
        } catch (UserExistsException e) {
            this.out.println(400);
            this.out.println(username);
        }
    }

    public void registerEmployee() {
        int id;
        String username = null;
        String password;
        String name;

        try {
            id = Integer.parseInt(this.in.readLine());
            username = this.in.readLine();
            password = this.in.readLine();
            name = this.in.readLine();
            server.registerEmployee(id, username, password, name);
            this.out.println(200);
        } catch (IOException ignore) {

        } catch (UserExistsException e) {
            this.out.println(400);
            this.out.println(username);
        }

    }

    public void login() {
        String username = null;
        String password;
        try {
            username = in.readLine();
            password = in.readLine();
            User user = server.logIn(username, password);
            out.println(200);
            if (user instanceof Customer c) {
                out.println("customer");
                returnCustomer(c);
            } else if (user instanceof Employee e) {
                out.println("employee");
                returnEmployee(e);
            }
        } catch (IOException ignore) {

        } catch (LoginFailed lf) {
            out.println(400);
            out.println(username);
        }
    }

    public void logout() {
        String username;
        try {
            username = in.readLine();
            User user = server.getUser(username);
            server.logOut(user);
            out.println(200);
        } catch (IOException ignore) {

        } catch (IllegalArgumentException e) {
            out.println(400);
        }
    }

    public void createProduct() {
        String name;
        double price;
        int quantity;
        User user;
        try {
            name = in.readLine();
            price = Double.parseDouble(in.readLine());
            quantity = Integer.parseInt(in.readLine());
            user = server.getUser(in.readLine());
            server.createProduct(name, price, quantity, user);
            out.println(200);
        } catch (IOException ignore) {

        }

    }

    public void createMProduct() {
        String name;
        double price;
        int quantity;
        int packSize = 0;
        User user;
        try {
            name = in.readLine();
            price = Double.parseDouble(in.readLine());
            quantity = Integer.parseInt(in.readLine());
            packSize = Integer.parseInt(in.readLine());
            user = server.getUser(in.readLine());
            server.createProduct(name, price, quantity, packSize, user);
            out.println(200);
        } catch (IOException ignore) {

        } catch (PacksizeNotMatching e) {
            out.println(400);
            out.println(packSize);
        }
    }

    public void editProduct() {
        int id;
        String name = null;
        double price;

        try {
            id = Integer.parseInt(in.readLine());
            name = in.readLine();
            price = Double.parseDouble(in.readLine());
            Product p = server.editProductDetails(id, name, price);
            out.println(200);
            returnProd(p);
        } catch (ProductNotFound e) {
            out.println(400);
            out.println(name);
        } catch (IOException ignore) {

        }
    }

    public void editMProduct() {
        int id;
        String name = null;
        double price;
        int packSize;

        try {
            id = Integer.parseInt(in.readLine());
            name = in.readLine();
            price = Double.parseDouble(in.readLine());
            packSize = Integer.parseInt(in.readLine());
            Product p = server.editProductDetails(id, name, price, packSize);
            out.println(200);
            returnProd(p);
        } catch (IOException ignore) {

        } catch (ProductNotFound e) {
            out.println(400);
            out.println(name);
        }

    }

    public void changeQuantity() {
        int id = 0;
        int quantity;
        User user;
        try {
            id = Integer.parseInt(in.readLine());
            quantity = Integer.parseInt(in.readLine());
            user = server.getUser(in.readLine());
            server.changeQuantity(id, quantity, user);
            out.println(200);
        } catch (IOException ignore) {

        } catch (ProductNotFound e) {
            out.println(400);
            out.println(id);
        } catch (PacksizeNotMatching e) {
            out.println(401);
            out.println(e.getPacksize());
        } catch (NotInStockException e) {
            out.println(402);
            out.println(e.getQuantity());
        }
    }

    public void getAll() {
        Collection<Product> products = server.getAllProducts();
        out.println(200);
        out.println(products.size());
        for (Product p : products) {
            returnProd(p);
        }

    }

    public void getAllEvents() {
        List<Event> events = server.getAllEvents().stream().toList();
        out.println(200);
        out.println(events.size());
        for (Event e : events) {
            returnEvent(e);
        }
    }

    public void findName() {
        String query;
        try {
            query = in.readLine();
            Collection<Product> products = server.findProducts(query);
            out.println(200);
            out.println(products.size());
            for (Product p : products) {
                returnProd(p);
            }

        } catch (IOException ignore) {
        }
    }

    public void findId() {
        int id = 0;
        try {
            id = Integer.parseInt(in.readLine());
            Product p = server.findProduct(id);
            out.println(200);
            returnProd(p);
        } catch (IOException ignore) {

        } catch (ProductNotFound e) {
            out.println(400);
            out.println(id);
        }
    }

    public void getProdHistory() {
        int prodId = 0;
        int days = 0;
        try {
            prodId = Integer.parseInt(in.readLine());
            days = Integer.parseInt(in.readLine());
        } catch (IOException ignore) {
        }
        int[] history = server.getProductHistory(prodId, days);
        out.println(200);
        out.println(history.length);
        for (int i : history) {
            out.println(i);
        }
    }

    public void getCart() {
        String customerName = "";
        try {
            customerName = in.readLine();
        } catch (IOException ignore) {
        }
        try {
            Customer c = (Customer) server.getUser(customerName);
            Map<Product, Integer> cart = server.getCart(c);
            out.println(200);
            out.println(cart.size());
            for (Product p : cart.keySet()) {
                int quantity = cart.get(p);
                returnProd(p);
                out.println(quantity);
            }
        } catch (RuntimeException e) {
            out.println(400);
        }
    }

    public void clearCart() {
        String username = "";
        try {
            username = in.readLine();
        } catch (IOException ignore) {
        }
        Customer c = (Customer) server.getUser(username);
        server.clearCart(c);
        out.println(200);
    }

    public void getInvoice() {
        String username = "";
        try {
            username = in.readLine();
        } catch (IOException ignore) {
        }
        Customer c = (Customer) server.getUser(username);
        out.println(200);
        returnCart(c.getUsername());
    }

    public void addToCart() {
        int prodId = 0;
        int quantiy;
        String username;
        Customer c;
        try {
            prodId = Integer.parseInt(in.readLine());
            quantiy = Integer.parseInt(in.readLine());
            username = in.readLine();
            c = (Customer) server.getUser(username);
            server.addToCart(prodId, quantiy, c);
            out.println(200);
        } catch (IOException ignore) {

        } catch (PacksizeNotMatching e) {
            out.println(401);
            out.println(e.getPacksize());
        } catch (ProductNotFound e) {
            out.println(400);
            out.println(prodId);
        } catch (NotInStockException e) {
            out.println(402);
            out.println(e.getQuantity());
        }


    }

    public void removeFromCart() {
        int prodId = 0;
        int quantiy;
        String username;
        Customer c;
        try {
            prodId = Integer.parseInt(in.readLine());
            quantiy = Integer.parseInt(in.readLine());
            username = in.readLine();
            c = (Customer) server.getUser(username);
            server.removeFromCart(prodId, quantiy, c);
            out.println(200);
        } catch (IOException ignore) {

        } catch (PacksizeNotMatching e) {
            out.println(401);
            out.println(e.getPacksize());
        } catch (ProductNotFound e) {
            out.println(400);
            out.println(prodId);
        }
    }

    public void checkout() {
        try {
            String username = in.readLine();
            Customer c = (Customer) server.getUser(username);
            server.checkout(c);
            out.println(200);
        } catch (IOException ignore) {

        } catch (CheckoutFailed checkoutFailed) {
            out.println(400);

        }

    }

    private void returnProd(Product p) {
        out.println(p instanceof MassProduct ? "mp" : "p");
        out.println(p.getId());
        out.println(p.getName());
        out.println(p.getPrice());
        out.println(p.getQuantity());
        if (p instanceof MassProduct mp) {
            out.println(mp.getPacksize());
        }
    }

    private void returnCustomer(Customer c) {
        out.println(c.getID());
        out.println(c.getUsername());
        out.println(c.getPassword());
        out.println(c.getName());
        out.println(c.getAddress());

    }

    private void returnEvent(Event e) {
        out.println(e.getDayInYear());
        out.println(e.getUserId());
        out.println(e.getProductId());
        out.println(e.getQuantity());
    }

    private void returnEmployee(Employee e) {
        out.println(e.getID());
        out.println(e.getUsername());
        out.println(e.getPassword());
        out.println(e.getName());
    }

    private void returnCart(String username) {
        try {
            Customer c = (Customer) server.getUser(username);
            Map<Product, Integer> cart = server.getCart(c);
            out.println(cart.size());
            for (Product p : cart.keySet()) {
                int quantity = cart.get(p);
                returnProd(p);
                out.println(quantity);
            }
        } catch (RuntimeException e) {
            out.println(400);
        }
    }


    @Override
    public void update(String keyword) {
        System.out.println("Update " + clientSocket.getPort());
        out.println(keyword);
    }
}
