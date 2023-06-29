//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.eshop.ui;

//import de.hsbremen.prog2.net.socket.Adresse;
import org.eshop.entities.*;
import org.eshop.exceptions.*;
import org.eshop.shop.Shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;

class ClientAddressRequestProcessor{

    private Socket clientSocket;
    private BufferedReader in;
    private PrintStream out;
    private final Shop server;

    public ClientAddressRequestProcessor(Socket socket, Shop server){
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

            System.err.println("Ausnahme bei Bereitstellung des Streams: " + String.valueOf(var6));
            return;
        }

        PrintStream var10000 = System.out;
        String var10001 = String.valueOf(this.clientSocket.getInetAddress());
        var10000.println("Verbunden mit Client " + var10001 + ":" + this.clientSocket.getPort());
    }

    public void verarbeiteAnfragen() {
        String input;
        this.out.println("Server bereit");

        do {
            input = "";
            try {
                input = this.in.readLine();
            } catch (Exception var5) {
                System.out.println("--->Fehler beim Lesen vom Client (Aktion): ");
                System.out.println(var5.getMessage());
                continue;
            }
            System.out.println(input);

            if (input == null) {
                input = "quit";
            }

            switch (input) {
                case "regUser":
                    regiserUser();
                    break;
                case "regEmp":
                    regiserEmployee();
                    break;
                case "login":
                    login();
                    break;
                case "logout":
                    logout();
                    break;
                case "createProd":
                    createProduct();
                    break;
                case "createMProd":
                    createMProduct();
                    break;
                case "editProd":
                    editProduct();
                    break;
                case "editMProd":
                    editMProduct();
                        break;
                case "changeQuant":
                    changeQuantity();
                    break;
                case "getAll":
                    getAll();
                    break;
                case "findName":
                    findName();
                    break;
                case "findId":
                    findId();
                        break;
                default:
                    System.out.println(input);
            }
        } while(!input.equals("Logout"));

        PrintStream var10000 = System.out;
        String var10001 = String.valueOf(this.clientSocket.getInetAddress());
        var10000.println("Verbindung zu " + var10001 + ":" + this.clientSocket.getPort() + " durch Client abgebrochen");

        try {
            this.clientSocket.close();
        } catch (IOException var4) {
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

        } catch (IOException e) {
            //Something fucked up
        } catch (UserExistsException e) {
            this.out.println(400);
            this.out.println(username);
        }
    }
    public void regiserEmployee(){
        int id = 0;
        String username = null;
        String password = null;
        String name = null;

        try {
            id = Integer.parseInt(this.in.readLine());
            username = this.in.readLine();
            password = this.in.readLine();
            name = this.in.readLine();
            server.registerEmployee(id,username,password, name);
            this.out.println(200);
        }catch (IOException e){

        } catch (UserExistsException e) {
            this.out.println(400);
            this.out.println(username);
        }

    }
    public void login(){
        String username = null;
        String password = null;
        try {
            username = in.readLine();
            password = in.readLine();
            User user = server.logIn(username, password);
            out.println(200);
            if(user instanceof Customer c){
                out.println("customer");
                out.println(c.getID());
                out.println(c.getUsername());
                out.println(c.getPassword());
                out.println(c.getName());
                out.println(c.getAddress());
            }else if(user instanceof Employee e){
                out.println("employee");
                out.println(e.getID());
                out.println(e.getUsername());
                out.println(e.getPassword());
                out.println(e.getName());
            }
        }catch (IOException e){

        }catch (LoginFailed lf){
            out.println(400);
            out.println(username);
        }
    }
    public void logout(){
        String username = null;
        try {
            username = in.readLine();
            User user = server.getUser(username);
            server.logOut(user);
            out.println(200);
        }catch (IOException e){

        }catch (IllegalArgumentException e){
            out.println(400);
        }
    }
    public void createProduct(){
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
        }catch (IOException e){

        }

    }
    public void createMProduct(){
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
        }catch (IOException e){

        } catch (PacksizeNotMatching e) {
            out.println(400);
            out.println(packSize);
        }
    }
    public void editProduct(){
        int id = 0;
        String name = null;
        Double price = null;

        try {
            id = Integer.parseInt(in.readLine());
            name = in.readLine();
            price = Double.parseDouble(in.readLine());
            Products p = server.editProductDetails(id, name, price);
            out.println(200);
            returnProd(p);
        } catch (ProductNotFound e) {
            out.println(400);
            out.println(name);
        } catch (IOException e) {

        }
    }
    public void editMProduct(){
        int id = 0;
        String name = null;
        Double price = null;
        int packSize = 0;

        try {
            id = Integer.parseInt(in.readLine());
            name = in.readLine();
            price = Double.parseDouble(in.readLine());
            packSize = Integer.parseInt(in.readLine());
            Products p = server.editProductDetails(id, name, price, packSize);
            out.println(200);
            returnProd(p);
        }catch (IOException e){

        } catch (ProductNotFound e) {
            out.println(400);
            out.println(name);
        }

    }
    private void returnProd(Products p){
        out.println(p instanceof MassProducts? "p": "mp");
        out.println(p.getId());
        out.println(p.getName());
        out.println(p.getPrice());
        out.println(p.getQuantity());
        if(p instanceof MassProducts mp){
            out.println(mp.getPacksize());
        }
    }
    public void changeQuantity(){
        int id = 0;
        int quantity = 0;
        User user = null;
        try {
            id = Integer.parseInt(in.readLine());
            quantity = Integer.parseInt(in.readLine());
            user = server.getUser(in.readLine());
            server.changeQuantity(id, quantity, user);
            out.println(200);
        }catch (IOException e){

        }catch (ProductNotFound e){
            out.println(400);
            out.println(id);
        }catch (PacksizeNotMatching e){
            out.println(401);
            out.println(e.getPacksize());
        }catch (NotInStockException e){
            out.println(402);
            out.println(e.getQuantity());
        }
    }
    public void getAll(){
        Collection<Products> products = server.getAllProducts();
        out.println(200);
        out.println(products.size());
        for(Products p: products){
            returnProd(p);
        }

    }
    public void findName(){
        String query = null;
        try {
            query = in.readLine();
            Collection<Products> products = server.findProducts(query);
            out.println(200);
            out.println(products.size());
            for(Products p: products){
                returnProd(p);
            }

    }catch (IOException e){
        }
    }
    public void findId(){
        int id = 0;
        try {
            id = Integer.parseInt(in.readLine());
            Products p = server.findProduct(id);
            out.println(200);
            returnProd(p);
    }catch (IOException e){

        }catch (ProductNotFound e){
            out.println(400);
            out.println(id);
        }
    }



}
