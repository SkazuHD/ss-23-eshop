package org.eshop.network;

import org.eshop.shop.UpdateInterface;
import org.eshop.shop.updatable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerRequestProcessor implements Runnable, UpdateInterface {
    private BufferedReader in;
    private PrintStream out;
    private final Socket socket;
    private final Map<String, List<updatable>> listeners;

    public ServerRequestProcessor(Socket socket) {
        this.listeners = new HashMap<>();
        this.socket = socket;
        try {
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintStream(this.socket.getOutputStream());
            this.out.println("listen");
        }catch (IOException e){
            System.err.println("ERROR: " + e.getMessage());
            System.exit(1);
        }

    }
    @Override
    public void run() {
        String input;
        do{
            input = null;
            try {
                input = this.in.readLine();
            } catch (Exception exc) {
                System.out.println("--->Fehler beim Lesen vom Server (Aktion): ");
                System.out.println(exc.getMessage());
            }
            if (input == null) {
                break;
            }
            System.out.println("Server: " + input);
            switch (input){
                case "init" -> {System.out.println("Server Active and running | waiting for keywords");}
                case "products" -> {
                    notifyClients("products");
                }
                case "employee" -> {
                    notifyClients("employee");
                }
                case "customer" -> {
                    notifyClients("customer");
                }
                case "event" -> {
                    notifyClients("event");
                }
                case "close" -> {
                    notifyClients("close");
                    return;
                }
                default -> {
                    System.out.println("ERROR: Unknown command: " + input);
                }
            }
        }while (true);
    }

    @Override
    public void addClient(updatable client, String keyword) {
        List<updatable> list = this.listeners.get(keyword);
        if (list != null) {
            list.add(client);
        } else {
            List<updatable> clients = new ArrayList<>();
            clients.add(client);
            this.listeners.put(keyword, clients);
        }
    }

    @Override
    public void removeClient(updatable client) {
        this.listeners.values().forEach(list -> list.remove(client));
    }

    @Override
    public void notifyClients(String keyword) {
        List<updatable> list = this.listeners.get(keyword);

        if (list != null) {
            System.out.println("Server: Notifying " + list.size() + " clients with keyword " + keyword);
            for(updatable client : list){
                client.update(keyword);
            }
        }else {
            System.out.println("Server: No clients with keyword " + keyword);
        }
    }

}

