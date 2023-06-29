package org.eshop.ui;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

//package de.hsbremen.prog2.net.socket.client;

//import de.hsbremen.prog2.net.socket.Adresse;
import org.eshop.entities.*;
import org.eshop.exceptions.*;
import org.eshop.shop.ShopFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Client  implements ShopFacade {
    private Socket socket = null;
    private BufferedReader in;
    private PrintStream out;

    public Client(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintStream(this.socket.getOutputStream());
        } catch (IOException var7) {
            System.err.println("Fehler beim Öffnen des Sockets/Streams: " + String.valueOf(var7));
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


            this.out.println("p");
            this.out.println("p");
            this.out.println("p");

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
            if(status.equals("400")){
                String ans = this.in.readLine();
                throw new UserExistsException(ans);

            }

        }catch (IOException e){

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

            }
            else if( status.equals("200")){
                String type = in.readLine();

                if (type.equals("customer")){
                    String id = in.readLine();
                    username = in.readLine();
                    password = in.readLine();
                    String name = in.readLine();
                    String adress = in.readLine();
                    return new Customer(username,password,name,adress, id);

                }else {
                    String id = in.readLine();
                    username = in.readLine();
                    password = in.readLine();
                    String name = in.readLine();

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
        try{
            String status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new UserExistsException(ans);

            }else if(status.equals("200")){
                return status.equals("200");
            }

        }catch (IOException | UserExistsException e){}

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
    public Products editProductDetails(int id, String name, double price) throws ProductNotFound {
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
                id = Integer.parseInt(in.readLine());
                name = in.readLine();
                price = Double.parseDouble(in.readLine());
                int quantity = Integer.parseInt(in.readLine());
                return new Products(id, price, name, quantity);


            }

        } catch (IOException e) {

        }
        return null;
    }



    @Override
    public Products editProductDetails(int id, String name, double price, int packSize) throws ProductNotFound {
        out.println("editMProd");
        out.println(id);
        out.println(name);
        out.println(price);
        out.println(packSize);
        try{
            String status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new ProductNotFound(ans);

            }else if(status.equals("200")){
                id = Integer.parseInt(in.readLine());
                name = in.readLine();
                price = Double.parseDouble(in.readLine());
                int quantity = Integer.parseInt(in.readLine());
                packSize = Integer.parseInt(in.readLine());


                return new Products(id, price, name, packSize);


            }

        }catch (IOException e){

        }
        return null;
    }

    @Override
    public void changeQuantity(int id, int quantity, User u) throws ProductNotFound, PacksizeNotMatching, NotInStockException {
    out.println("changeQuant");
    out.println(id);
    out.println(quantity);
    out.println(u);
        try{
            String status = this.in.readLine();
            if (status.equals("400")) {
                String ans = this.in.readLine();
                throw new ProductNotFound(ans);
            } else if (status.equals("401")) {
                int ans = Integer.parseInt( this.in.readLine());
                throw new PacksizeNotMatching(ans);
            } else if (status.equals("402")) {
                int ans = Integer.parseInt(this.in.readLine());
                throw new NotInStockException(ans);

            } else if (status.equals("200")){
        }

        }catch (IOException e){

        }

    }

    @Override
    public Collection<Products> getAllProducts() {
        out.println("getAll");
        String status = "";
        try { status = this.in.readLine();
        }

        catch(IOException ignore) {}

            if (status.equals("400")) {
                IOException IOException;

            } else if (status.equals("200")) {
                readProducktList();


            }
            return null;
        }

    @Override
    public List<Products> findProducts(String name) {
        out.println("findName");
        out.println(name);
        try{
            String status = this.in.readLine();
            if(status.equals("400")){

            } else if (status.equals("200")) {
                 return readProducktList();

            }

        } catch (IOException e) {

        }
        return null;
    }

    @Override
    public Products findProduct(int ID) throws ProductNotFound {
        out.println("findId");
        out.println(ID);
        try{String status = this.in.readLine();
            if(status.equals("400")){
                throw new ProductNotFound(in.readLine());

            } else if (status.equals("200")) {
                String type = in.readLine();
                if (type.equals("p")) {
                    int id = Integer.parseInt(in.readLine());
                    String name = this.in.readLine();
                    double price = Double.valueOf(this.in.readLine());
                    int quantity = Integer.parseInt(this.in.readLine());
                     return new Products(id, price, name, quantity);
                }else if (type.equals("mp")) {
                    int id = Integer.parseInt(in.readLine());
                    String name = this.in.readLine();
                    double price = Double.valueOf(this.in.readLine());
                    int quantity = Integer.parseInt(this.in.readLine());
                    int packsize = Integer.parseInt(this.in.readLine());
                     return new MassProducts(id, price, name, quantity, packsize);
                }



            }


        } catch (Exception e) {


        }
        return null;
    }


    @Override
    public Collection <Employee>getAllEmployees() {
        out.println("getAllEmp");
        String status ="";
        try {
             status = this.in.readLine();
        }catch (IOException ignore){

        }
        if (status.equals("400")) {

        } else if (status.equals("200")) {



        }
        return null;
    }

    @Override
    public String getInvoice(Customer c) {

        return null;
    }

    @Override
    public void checkout(Customer c) {

    }

    @Override
    public Map<Products, Integer> getCart(Customer c) {
        return null;
    }

    @Override
    public void addToCart(int id, int quantity, Customer c) throws PacksizeNotMatching, NotInStockException, ProductNotFound {

    }

    @Override
    public void removeFromCart(int id, int quantity, Customer c) throws PacksizeNotMatching, ProductNotFound {

    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    private List<Products> readProducktList ()  {

        List<Products> products = new ArrayList<>();
        int count;
        try {
            String type = in.readLine();
            count = Integer.parseInt(in.readLine());
            for (int i = 0; i < count; i++) {
                if (type.equals("p")) {
                    int id = Integer.parseInt(in.readLine());
                    String name = this.in.readLine();
                    double price = Double.valueOf(this.in.readLine());
                    int quantity = Integer.parseInt(this.in.readLine());
                    products.add(new Products(id, price, name, quantity));
                }else if (type.equals("mp")) {
                    int id = Integer.parseInt(in.readLine());
                    String name = this.in.readLine();
                    double price = Double.valueOf(this.in.readLine());
                    int quantity = Integer.parseInt(this.in.readLine());
                    int packsize = Integer.parseInt(this.in.readLine());
                    products.add(new MassProducts(id, price, name, quantity, packsize));
                }
            }
        }catch (IOException e){
        }finally {
            return products;
        }

    }
    public User getUser(String username) {
        return null;
    }
}

