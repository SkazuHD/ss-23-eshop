package org.eshop.network;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

//package de.hsbremen.prog2.net.socket.client;

//import de.hsbremen.prog2.net.socket.Adresse;

import org.eshop.entities.*;
import org.eshop.exceptions.*;
import org.eshop.shop.ShopFacade;
import org.eshop.shop.updateEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.*;

public class Client implements ShopFacade {
    private Socket socket = null;
    private BufferedReader in;
    private PrintStream out;

    public Client(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintStream(this.socket.getOutputStream());
        } catch (IOException var7) {
            System.err.println("Fehler beim Öffnen des Sockets/Streams: " + var7);
            if (this.socket != null) {
                try {
                    this.socket.close();
                    System.err.println("Socket geschlossen");
                } catch (IOException var6) {
                }
            }

            System.exit(1);
        }

        PrintStream var10000 = System.err;
        String var10001 = String.valueOf(this.socket.getInetAddress());
        var10000.println("Verbunden mit Server " + var10001 + ":" + this.socket.getPort());

        try {
            String message = this.in.readLine();
            System.out.println(message);
        } catch (IOException var5) {

        }

    }


    public static void main(String[] args) {
        String host = "localhost";
        int port = 6789;
        if (args.length == 2) {
            host = args[0];

            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException var6) {
                port = 0;
            }
        }

        Client client = new Client(host, port);

    }

    @Override
    public void registerUser(String username, String password, String name, String address) throws UserExistsException {
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

        } catch (IOException e) {

        }


    }

    @Override
    public void registerEmployee(int id, String username, String password, String name) throws UserExistsException {
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

        } catch (IOException e) {
        }
    }

    @Override
    public User logIn(String username, String password) throws LoginFailed {
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

        } catch (IOException e) {
        }
        return null;
    }

    @Override
    public boolean logOut(User user) {
        out.println("logout");
        out.println(user.getUsername());
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new UserExistsException(ans);

            } else if (status.equals("200")) {
                return status.equals("200");
            }

        } catch (IOException | UserExistsException e) {
        }

        return false;
    }

    @Override
    public void createProduct(String name, double price, int quantity, User user) {
        out.println("createProd");
        out.println(name);
        out.println(price);
        out.println(quantity);
        out.println(user.getUsername());
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new UserExistsException(ans);

            }

        } catch (IOException | UserExistsException e) {
        }


    }

    @Override
    public void createProduct(String name, double price, int quantity, int packSize, User user) throws PacksizeNotMatching {
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

        } catch (IOException e) {

        }
    }

    @Override
    public void deleteProduct(int id) throws ProductNotFound {

    }

    @Override
    public Product editProductDetails(int id, String name, double price) throws ProductNotFound {
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
                Product p = prod();
                return p;
            }

        } catch (IOException ignored) {

        }
        return null;
    }


    @Override
    public Product editProductDetails(int id, String name, double price, int packSize) throws ProductNotFound {
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
                id = Integer.parseInt(in.readLine());
                name = in.readLine();
                price = Double.parseDouble(in.readLine());
                int quantity = Integer.parseInt(in.readLine());
                packSize = Integer.parseInt(in.readLine());


                return new Product(id, price, name, packSize);


            }

        } catch (IOException e) {

        }
        return null;
    }

    @Override
    public void changeQuantity(int id, int quantity, User u) throws ProductNotFound, PacksizeNotMatching, NotInStockException {
        out.println("changeQuant");
        out.println(id);
        out.println(quantity);
        out.println(u.getUsername());
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new ProductNotFound(ans);
            } else if (status.equals("401")) {
                int ans = Integer.parseInt(this.in.readLine());
                throw new PacksizeNotMatching(ans);
            } else if (status.equals("402")) {
                int ans = Integer.parseInt(this.in.readLine());
                throw new NotInStockException(ans);

            } else if (status.equals("200")) {
            }

        } catch (IOException e) {

        }

    }

    @Override
    public Collection<Product> getAllProducts() {
        out.println("getAll");
        String status = "";
        try {
            status = this.in.readLine();
        } catch (IOException ignore) {
        }

        if (status.equals("400")) {
            IOException IOException;

        } else if (status.equals("200")) {
           return readProducktList();
        }
        return new ArrayList<Product>();
    }

    @Override
    public List<Product> findProducts(String name) {
        out.println("findName");
        out.println(name);
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {

            } else if (status.equals("200")) {
                return readProducktList();

            }

        } catch (IOException e) {

        }
        return null;
    }

    @Override
    public Product findProduct(int ID) throws ProductNotFound {
        out.println("findId");
        out.println(ID);
        try {
            String status = this.in.readLine();
            if (status.equals("400")) {
                throw new ProductNotFound(in.readLine());

            } else if (status.equals("200")) {
                String type = in.readLine();
                if (type.equals("p")) {
                    int id = Integer.parseInt(in.readLine());
                    String name = this.in.readLine();
                    double price = Double.valueOf(this.in.readLine());
                    int quantity = Integer.parseInt(this.in.readLine());
                    return new Product(id, price, name, quantity);
                } else if (type.equals("mp")) {
                    int id = Integer.parseInt(in.readLine());
                    String name = this.in.readLine();
                    double price = Double.valueOf(this.in.readLine());
                    int quantity = Integer.parseInt(this.in.readLine());
                    int packsize = Integer.parseInt(this.in.readLine());
                    return new MassProduct(id, price, name, quantity, packsize);
                }


            }


        } catch (Exception e) {


        }
        return null;
    }

    @Override
    public int[] getProductHistory(int productId, int days) {
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

               for (int i= 0; i <length; i++){
                     zahlen[i] = Integer.parseInt(in.readLine());
               }
            }

        } catch (IOException e) {

        }
        return zahlen;
    }




    @Override
    public Collection<Employee> getAllEmployees() {
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

            }catch (IOException e){

            }


        }
        return new ArrayList<Employee>();
    }

    @Override
    public Invoice getInvoice(Customer c) {
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
                    Product p = prod();
                    int quantity = Integer.parseInt(in.readLine());
                    c.addToCart(p, quantity);
                }

            }

        } catch (IOException e) {

        }
        return new Invoice(c);

    }

    @Override
    public void checkout(Customer c) throws CheckoutFailed {
        out.println("checkout");
        out.println(c.getUsername());
        String status;
        List<Product> prod = new ArrayList<>();
        try {
            status = this.in.readLine();
            if (status.equals("400")) {
                int size = Integer.parseInt(in.readLine());
                for (int i = 0; i < size; i++) {
                    Product p = prod();
                    prod.add(p);
                }
                throw new CheckoutFailed(prod);

                } else if (status.equals("200")) {
                    c.clearCart();

                }


        }
        catch(IOException e){

            }

    }
    @Override
    public Map<Product, Integer> getCart(Customer c) {
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
                    Product p = prod();
                    int quantity = Integer.parseInt(in.readLine());
                    cart.put(p, quantity);

                }
            }

        } catch (IOException e) {

        }
        return cart;
    }

    @Override
    public void addToCart(int id, int quantity, Customer c) throws PacksizeNotMatching, NotInStockException, ProductNotFound {
        out.println("addToCart");
        out.println(id);
        out.println(quantity);
        out.println(c.getUsername());
        String status;
        try {
             status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new ProductNotFound(ans);
            } else if (status.equals("401")) {
                int ans = Integer.parseInt(this.in.readLine());
                throw new PacksizeNotMatching(ans);
            } else if (status.equals("402")) {
                int ans = Integer.parseInt(this.in.readLine());
                throw new NotInStockException(ans);

            } else if (status.equals("200")) {


            }

        } catch (IOException e) {

        }



    }

    @Override
    public void removeFromCart(int id, int quantity, Customer c) throws PacksizeNotMatching, ProductNotFound {
        out.println("removeFromCart");
        out.println(id);
        out.println(quantity);
        out.println(c.getUsername());
        String status;
        try {
            status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new ProductNotFound(ans);
            } else if (status.equals("401")) {
                int ans = Integer.parseInt(this.in.readLine());
                throw new PacksizeNotMatching(ans);
            } else if (status.equals("200")) {


            }

        } catch (IOException e) {

        }


    }

    @Override
    public void save() {
        out.println("save");
    }

    @Override
    public void addUpdateListener(updateEventListener listener) {

    }


    private List<Product> readProducktList() {

        List<Product> products = new ArrayList<>();
        int count;
        try {
            count = Integer.parseInt(in.readLine());
            for (int i = 0; i < count; i++) {
                String type = in.readLine();
                if (type.equals("p")) {
                    int id = Integer.parseInt(in.readLine());
                    String name = this.in.readLine();
                    double price = Double.valueOf(this.in.readLine());
                    int quantity = Integer.parseInt(this.in.readLine());
                    products.add(new Product(id, price, name, quantity));
                } else if (type.equals("mp")) {
                    int id = Integer.parseInt(in.readLine());
                    String name = this.in.readLine();
                    double price = Double.valueOf(this.in.readLine());
                    int quantity = Integer.parseInt(this.in.readLine());
                    int packsize = Integer.parseInt(this.in.readLine());
                    products.add(new MassProduct(id, price, name, quantity, packsize));
                }
            }
        } catch (IOException e) {

        }
        return products;
    }
    private Employee readEmployee(){
        try {
            int id = Integer.parseInt(in.readLine());
            String name = this.in.readLine();
            String username = this.in.readLine();
            String password = this.in.readLine();
            return new Employee(id, name, username, password);
        }catch (IOException e){

        }
        return null;
    }

    public User getUser(String username) {
        return null;
    }

    private Product prod (){
        String status;
        try {
            String type = in.readLine();

                if (type.equals("p")) {
                    int id = Integer.parseInt(in.readLine());
                    String name = this.in.readLine();
                    double price = Double.valueOf(this.in.readLine());
                    int quantity = Integer.parseInt(this.in.readLine());
                    return new Product(id,price, name, quantity);

                } else if (type.equals("mp")) {
                    int id = Integer.parseInt(in.readLine());
                    String name = this.in.readLine();
                    double price = Double.valueOf(this.in.readLine());
                    int quantity = Integer.parseInt(this.in.readLine());
                    int packsize = Integer.parseInt(this.in.readLine());
                    return new MassProduct(id,price,name,quantity, packsize);
                }

        } catch (IOException e) {

        } return  null;
    }

}

