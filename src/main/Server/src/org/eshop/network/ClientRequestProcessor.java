package org.eshop.network;


import org.eshop.exceptions.*;
import org.eshop.entities.*;
import org.eshop.shop.Shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

class ClientRequestProcessor implements Runnable{

    private final Shop server;
    private final Socket clientSocket;
    private BufferedReader in;
    private PrintStream out;

    public ClientRequestProcessor(Socket socket, Shop server) {
        this.clientSocket = socket;
        this.server = server;

        try {
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            this.out = new PrintStream(this.clientSocket.getOutputStream());
        } catch (IOException var6) {
            try {
                this.clientSocket.close();
            } catch (IOException var5) {
            }

            System.err.println("Ausnahme bei Bereitstellung des Streams: " + var6);
            return;
        }

        PrintStream var10000 = System.out;
        String var10001 = String.valueOf(this.clientSocket.getInetAddress());
        var10000.println("Verbunden mit Client " + var10001 + ":" + this.clientSocket.getPort());
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
                case "registerUser":
                    regiserUser();
                    System.out.println("Success");
                    break;
                case "regEmp":
                    regiserEmployee();
                    System.out.println("Success");
                    break;
                case "login":
                    login();
                    System.out.println("Success");
                    break;
                case "logout":
                    logout();
                    System.out.println("Success");
                    break;
                case "createProd":
                    createProduct();
                    System.out.println("Success");
                    break;
                case "createMProd":
                    createMProduct();
                    System.out.println("Success");
                    break;
                case "editProd":
                    editProduct();
                    System.out.println("Success");
                    break;
                case "editMProd":
                    editMProduct();
                    System.out.println("Success");
                    break;
                case "changeQuant":
                    changeQuantity();
                    System.out.println("Success");
                    break;
                case "getAll":
                    getAll();
                    System.out.println("Success");
                    break;
                case "findName":
                    findName();
                    System.out.println("Success");
                    break;
                case "findId":
                    findId();
                    System.out.println("Success");
                    break;
                case "prodHistory":
                    getProdHistory();
                    System.out.println("Success");
                    break;
                case "getInvoice":
                    getInvoice();
                    System.out.println("Success");
                    break;
                case "checkout":
                    checkout();
                    System.out.println("Success");
                    break;
                case "getCart":
                    getCart();
                    System.out.println("Success");
                    break;
                case "addToCart":
                    addToCart();
                    System.out.println("Success");
                    break;
                case "removeFromCart":
                    removeFromCart();
                    System.out.println("Success");
                    break;
                case "getAllEmp":
                    getAllEmp();
                    System.out.println("Success");
                    break;
                case "save":
                    server.save();
                    break;
                default:
                    System.out.println("Not valid " + input);
            }
        } while (!input.equals("Logout"));

        PrintStream var10000 = System.out;
        String var10001 = String.valueOf(this.clientSocket.getInetAddress());
        var10000.println("Verbindung zu " + var10001 + ":" + this.clientSocket.getPort() + " durch Client abgebrochen");

        try {
            this.clientSocket.close();
        } catch (IOException var4) {
        }

    }

    private void getAllEmp() {
       Collection<Employee> employees = server.getAllEmployees();
       this.out.println(200);
       this.out.println(employees.size());
       for(Employee e : employees){
           returnEmployee(e);
       }

    }

    public void regiserUser() {
        String username = null;
        String password = null;
        String name = null;
        String address = null;
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

    public void regiserEmployee() {
        int id = 0;
        String username = null;
        String password = null;
        String name = null;

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
        String password = null;
        try {
            username = in.readLine();
            password = in.readLine();
            User user = server.logIn(username, password);
            out.println(200);
            if (user instanceof Customer c) {
                out.println("customer");
                out.println(c.getID());
                out.println(c.getUsername());
                out.println(c.getPassword());
                out.println(c.getName());
                out.println(c.getAddress());
            } else if (user instanceof Employee e) {
                out.println("employee");
                out.println(e.getID());
                out.println(e.getUsername());
                out.println(e.getPassword());
                out.println(e.getName());
            }
        } catch (IOException ignore) {

        } catch (LoginFailed lf) {
            out.println(400);
            out.println(username);
        }
    }

    public void logout() {
        String username = null;
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
        String name = null;
        Double price = null;
        int quantity = 0;
        User user = null;
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
        String name = null;
        Double price = null;
        int quantity = 0;
        int packSize = 0;
        User user = null;
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
        int id = 0;
        String name = null;
        Double price = null;

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
        int id = 0;
        String name = null;
        Double price = null;
        int packSize = 0;

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
        int quantity = 0;
        User user = null;
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

    public void findName() {
        String query = null;
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
            Iterator<Product> it = cart.keySet().iterator();
            while (it.hasNext()) {
                Product p = it.next();
                int quantity = cart.get(p);
                returnProd(p);
                out.println(quantity);
            }
        } catch (RuntimeException e) {
            out.println(400);
        }
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

    // UTIL
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
        out.println(c.getUsername());
        out.println(c.getPassword());
        out.println(c.getAddress());
        out.println(c.getID());
    }

    private void returnEmployee(Employee e) {
        out.println(e.getID());
        out.println(e.getName());
        out.println(e.getUsername());
        out.println(e.getPassword());
    }

    private void returnCart(String username) {
        try {
            Customer c = (Customer) server.getUser(username);
            Map<Product, Integer> cart = server.getCart(c);
            out.println(cart.size());
            Iterator<Product> it = cart.keySet().iterator();
            while (it.hasNext()) {
                Product p = it.next();
                int quantity = cart.get(p);
                returnProd(p);
                out.println(quantity);
            }
        } catch (RuntimeException e) {
            out.println(400);
        }
    }


}
