package org.eshop.network;

import org.eshop.exceptions.*;
import org.eshop.entities.*;
import org.eshop.shop.ShopFacade;
import org.eshop.shop.UpdateInterface;
import org.eshop.shop.updatable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.*;

public class Client implements ShopFacade {
    private Socket socket = null;
    private ServerRequestProcessor updateSocket = null;
    private BufferedReader in;
    private PrintStream out;

    public Client(String host, int port) {
        while (this.socket == null) {
            try {
                this.socket = new Socket(host, port);
                this.updateSocket = new ServerRequestProcessor(new Socket(host, port));
                Thread t = new Thread(this.updateSocket);
                t.start();
                this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                this.out = new PrintStream(this.socket.getOutputStream());
            } catch (IOException e) {
                System.err.println("Fehler beim Öffnen des Sockets/Streams: " + e);
                if (this.socket != null) {
                    try {
                        this.socket.close();
                        System.err.println("Socket geschlossen");
                    } catch (IOException ignore) {
                    }
                }
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException ignore) {

                }


            }
        }


        String server = String.valueOf(this.socket.getInetAddress());
        System.out.println("Verbunden mit Server " + server + ":" + this.socket.getPort());

        try {
            String message = this.in.readLine();
            System.out.println(message);
        } catch (IOException ignore) {

        }

    }


    @Override
    public synchronized void registerUser(String username, String password, String name, String address) throws UserExistsException {
        out.println("registerUser");
        out.println(username);
        out.println(password);
        out.println(name);
        out.println(address);
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new UserExistsException(ans);
            }

        } catch (IOException ignore) {

        }

    }

    @Override
    public synchronized void registerEmployee(int id, String username, String password, String name) throws UserExistsException {
        out.println("regEmp");
        out.println(id);
        out.println(username);
        out.println(password);
        out.println(name);
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new UserExistsException(ans);
            }

        } catch (IOException ignore) {
        }
    }

    @Override
    public synchronized User logIn(String username, String password) throws LoginFailed {
        out.println("login");
        out.println(username);
        out.println(password);
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new LoginFailed(ans);

            } else if (status.equals("200")) {
                String type = in.readLine();
                if (type.equals("customer")) {
                    String id = in.readLine();
                    username = in.readLine();
                    password = in.readLine();
                    String name = in.readLine();
                    String adress = in.readLine();
                    return new Customer(username, password, name, adress, id);
                } else {
                    int id = Integer.parseInt(in.readLine());
                    username = in.readLine();
                    password = in.readLine();
                    String name = in.readLine();
                    return new Employee(id, name, username, password);
                }
            }

        } catch (IOException ignore) {
        }
        return null;
    }

    @Override
    public synchronized boolean logOut(User user) {
        out.println("logout");
        out.println(user.getUsername());
        try {
            String status = this.in.readLine();
            return status.equals("200");
        } catch (IOException ignore) {
        }
        return false;
    }

    @Override
    public synchronized void createProduct(String name, double price, int quantity, User user) {
        out.println("createProd");
        out.println(name);
        out.println(price);
        out.println(quantity);
        out.println(user.getUsername());
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {
                throw new IllegalArgumentException("Values not Valid");
            }
        } catch (IOException ignore) {
        }


    }

    @Override
    public synchronized void createProduct(String name, double price, int quantity, int packSize, User user) throws PacksizeNotMatching {
        out.println("createMProd");
        out.println(name);
        out.println(price);
        out.println(quantity);
        out.println(packSize);
        out.println(user.getUsername());
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {
                int ans = Integer.parseInt(this.in.readLine());
                throw new PacksizeNotMatching(ans);
            }

        } catch (IOException ignore) {

        }
    }

    @Override
    public synchronized void deleteProduct(int id) {

    }

    @Override
    public synchronized Product editProductDetails(int id, String name, double price) throws ProductNotFound {
        out.println("editProd");
        out.println(id);
        out.println(name);
        out.println(price);
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new ProductNotFound(ans);

            } else if (status.equals("200")) {
                return readProduct();
            }

        } catch (IOException ignored) {

        }
        return null;
    }


    @Override
    public synchronized Product editProductDetails(int id, String name, double price, int packSize) throws ProductNotFound {
        out.println("editMProd");
        out.println(id);
        out.println(name);
        out.println(price);
        out.println(packSize);
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new ProductNotFound(ans);

            } else if (status.equals("200")) {
                return readProduct();
            }

        } catch (IOException ignore) {

        }
        return null;
    }

    @Override
    public synchronized void changeQuantity(int id, int quantity, User u) throws ProductNotFound, PacksizeNotMatching, NotInStockException {
        out.println("changeQuant");
        out.println(id);
        out.println(quantity);
        out.println(u.getUsername());
        try {
            String status = this.in.readLine();
            switch (status) {
                case "400": {
                    String ans = this.in.readLine();
                    throw new ProductNotFound(ans);
                }
                case "401": {
                    int ans = Integer.parseInt(this.in.readLine());
                    throw new PacksizeNotMatching(ans);
                }
                case "402": {
                    int ans = Integer.parseInt(this.in.readLine());
                    throw new NotInStockException(ans);
                }
                case "200":
                    break;
            }

        } catch (IOException ignore) {

        }

    }

    @Override
    public synchronized Collection<Product> getAllProducts() {
        out.println("getAll");
        String status = "";
        try {
            status = this.in.readLine();
        } catch (IOException ignore) {
        }

        if (status.equals("400")) {

        } else if (status.equals("200")) {
            return readProducktList();
        }
        return new ArrayList<>();
    }

    @Override
    public synchronized Collection<Event> getAllEvents() {
        out.println("getAllEvents");
        String status = "";
        try {
            status = this.in.readLine();
            if (status.equals("400")) {

            } else if (status.equals("200")) {
                int size = Integer.parseInt(in.readLine());
                ArrayList<Event> events = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    int day = Integer.parseInt(in.readLine());
                    String User = in.readLine();
                    int productID = Integer.parseInt(in.readLine());
                    int quantity = Integer.parseInt(in.readLine());
                    events.add(new Event(day, User, productID, quantity));
                }
                return events;
            }
        } catch (IOException ignore) {

        }
        return new ArrayList<>();
    }

    @Override
    public synchronized List<Product> findProducts(String name) {
        out.println("findName");
        out.println(name);
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {

            } else if (status.equals("200")) {
                return readProducktList();
            }

        } catch (IOException ignore) {

        }
        return new ArrayList<>();
    }

    @Override
    public synchronized Product findProduct(int ID) throws ProductNotFound {
        out.println("findId");
        out.println(ID);
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {
                throw new ProductNotFound(in.readLine());

            } else if (status.equals("200")) {
                return readProduct();

            }
        } catch (IOException ignore) {


        }
        return null;
    }

    @Override
    public synchronized int[] getProductHistory(int productId, int days) {
        out.println("prodHistory");
        out.println(productId);
        out.println(days);
        String status;
        int[] zahlen = new int[days];
        try {
            status = this.in.readLine();
            if (status.equals("400")) {

            } else if (status.equals("200")) {
                int length = Integer.parseInt(in.readLine());
                zahlen = new int[length];

                for (int i = 0; i < length; i++) {
                    zahlen[i] = Integer.parseInt(in.readLine());
                }
            }

        } catch (IOException ignore) {

        }
        return zahlen;
    }


    @Override
    public synchronized Collection<Employee> getAllEmployees() {
        out.println("getAllEmp");
        String status = "";
        try {
            status = this.in.readLine();
        } catch (IOException ignore) {

        }
        if (status.equals("400")) {

        } else if (status.equals("200")) {
            try {
                int size = Integer.parseInt(in.readLine());
                List<Employee> employees = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    employees.add(readEmployee());
                }
                return employees;

            } catch (IOException ignore) {

            }


        }
        return new ArrayList<>();
    }

    @Override
    public synchronized Invoice getInvoice(Customer c) {
        out.println("getInvoice");
        out.println(c.getUsername());
        String status;
        try {
            status = this.in.readLine();
            if (status.equals("400")) {

            } else if (status.equals("200")) {
                c.clearCart();
                int size = Integer.parseInt(in.readLine());
                for (int i = 0; i < size; i++) {
                    Product p = readProduct();
                    int quantity = Integer.parseInt(in.readLine());
                    c.addToCart(p, quantity);
                }

            }

        } catch (IOException ignore) {

        }
        return new Invoice(c);

    }

    @Override
    public synchronized void checkout(Customer c) throws CheckoutFailed {
        out.println("checkout");
        out.println(c.getUsername());
        String status;
        List<Product> prod = new ArrayList<>();
        try {
            status = this.in.readLine();
            if (status.equals("400")) {
                int size = Integer.parseInt(in.readLine());
                for (int i = 0; i < size; i++) {
                    Product p = readProduct();
                    prod.add(p);
                }
                throw new CheckoutFailed(prod);

            } else if (status.equals("200")) {
                c.clearCart();
            }


        } catch (IOException ignore) {

        }
    }

    @Override
    public synchronized Map<Product, Integer> getCart(Customer c) {
        out.println("getCart");
        out.println(c.getUsername());
        Map<Product, Integer> cart = new HashMap<>();
        String status;
        try {
            status = this.in.readLine();
            if (status.equals("400")) {

            } else if (status.equals("200")) {
                int size = Integer.parseInt(in.readLine());
                for (int i = 0; i < size; i++) {
                    Product p = readProduct();
                    int quantity = Integer.parseInt(in.readLine());
                    cart.put(p, quantity);
                }
            }

        } catch (IOException ignore) {

        }
        return cart;
    }

    @Override
    public synchronized void addToCart(int id, int quantity, Customer c) throws PacksizeNotMatching, NotInStockException, ProductNotFound {
        out.println("addToCart");
        out.println(id);
        out.println(quantity);
        out.println(c.getUsername());
        try {
            String status = this.in.readLine();
            switch (status) {
                case "400": {
                    String ans = this.in.readLine();
                    throw new ProductNotFound(ans);
                }
                case "401": {
                    int ans = Integer.parseInt(this.in.readLine());
                    throw new PacksizeNotMatching(ans);
                }
                case "402": {
                    int ans = Integer.parseInt(this.in.readLine());
                    throw new NotInStockException(ans);
                }
                case "200":
                    break;
            }

        } catch (IOException ignore) {

        }
    }

    @Override
    public synchronized void removeFromCart(int id, int quantity, Customer c) throws PacksizeNotMatching, ProductNotFound {
        out.println("removeFromCart");
        out.println(id);
        out.println(quantity);
        out.println(c.getUsername());
        String status;
        try {
            status = this.in.readLine();
            switch (status) {
                case "400": {
                    String ans = this.in.readLine();
                    throw new ProductNotFound(ans);
                }
                case "401": {
                    int ans = Integer.parseInt(this.in.readLine());
                    throw new PacksizeNotMatching(ans);
                }
                case "200":
                    break;
            }

        } catch (IOException ignore) {

        }


    }

    @Override
    public synchronized void clearCart(Customer c) {
        out.println("clearCart");
        out.println(c.getUsername());
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {

            } else if (status.equals("200")) {
                c.clearCart();
            }

        } catch (IOException ignore) {

        }
    }

    @Override
    public void save() {
        out.println("save");
    }


    private synchronized List<Product> readProducktList() {

        List<Product> products = new ArrayList<>();
        int count;
        try {
            count = Integer.parseInt(in.readLine());
            for (int i = 0; i < count; i++) {
                Product p = readProduct();
                products.add(p);
            }

        } catch (IOException ignore) {

        }
        return products;
    }

    private synchronized Employee readEmployee() {
        try {
            int id = Integer.parseInt(in.readLine());
            String name = this.in.readLine();
            String username = this.in.readLine();
            String password = this.in.readLine();
            return new Employee(id, name, username, password);
        } catch (IOException ignore) {

        }
        return null;
    }

    public synchronized User getUser(String username) {
        return null;
    }

    private synchronized Product readProduct() {
        try {
            String type = in.readLine();

            if (type.equals("p")) {
                int id = Integer.parseInt(in.readLine());
                String name = this.in.readLine();
                double price = Double.parseDouble(this.in.readLine());
                int quantity = Integer.parseInt(this.in.readLine());
                return new Product(id, price, name, quantity);

            } else if (type.equals("mp")) {
                int id = Integer.parseInt(in.readLine());
                String name = this.in.readLine();
                double price = Double.parseDouble(this.in.readLine());
                int quantity = Integer.parseInt(this.in.readLine());
                int packsize = Integer.parseInt(this.in.readLine());
                return new MassProduct(id, price, name, quantity, packsize);
            }

        } catch (IOException ignore) {

        }
        return null;
    }
    public UpdateInterface getUpdateInterface(){
        return updateSocket;
    }
}



