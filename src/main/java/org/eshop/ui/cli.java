package org.eshop.ui;

import org.eshop.exceptions.CustomerExistsException;
import org.eshop.exceptions.CustomerLoginFailed;
import org.eshop.shop.Shop;
import java.util.Scanner;

public class cli {

    Shop server;
    Scanner in = new Scanner(System.in);
    boolean loggedIn = false;
    public cli(){
        server = new Shop();
        run();
    }
    public void run(){
        do {
            showStartMenu();
            int input = getNumericInput();
            selectFromMenu(input);
        }while (!loggedIn);


    }
    public void showStartMenu(){
        System.out.println("1. Login");
        System.out.println("2. Register");
    }
    public void selectFromMenu(int input){
        switch (input){
            case 1 -> {
                loginUser();
            }
            case 2 -> {
                registerUser();
            }
            default -> {
                System.err.println("Invalid Selection!");
            }
        }
    }
    public int getNumericInput(){
        String input;
        int value = 0;
        input = in.nextLine();
        try {
            value = Integer.parseInt(input);
        }catch (NumberFormatException e){
            System.err.println("Please enter a Numeric Value");
            getNumericInput();
        }
        return value;

    }
    protected void registerUser(){
        //GET USERNAME
        System.out.print("Enter Username:");
        String username = in.nextLine();
        //GET PWD
        System.out.print("Enter Password:");
        String password = in.nextLine();

        //GET NAME
        System.out.print("Enter Name:");
        String name = in.nextLine();

        //GET ADDRESS
        System.out.print("Enter Address:");
        String address = in.nextLine();

        try {
            server.registerUser(username, password, name, address);
        }catch (CustomerExistsException e){
            System.err.println(e.getMessage());
        }
    }
    protected void loginUser(){
        //GET USERNAME
        System.out.print("Enter Username:");
        String username = in.nextLine();
        //GET PWD
        System.out.print("Enter Password:");
        String password = in.nextLine();
        try {
            server.loginUser(username, password);
            loggedIn = true;
        }catch (CustomerLoginFailed e){
            System.err.println(e.getMessage());
            System.out.flush();
        }
    }

}
