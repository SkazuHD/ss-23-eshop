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
    //zweite verbindung die Aufgebaut wird die auf Updates wartet vom Server
    private final Socket socket;
    private final Map<String, List<updatable>> listeners;
    private BufferedReader in;
    private PrintStream out;

    public ServerRequestProcessor(Socket socket) {
        this.listeners = new HashMap<>();
        this.socket = socket;
        try {
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintStream(this.socket.getOutputStream());
            this.out.println("listen");
        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());
            System.exit(1);
        }

    }

    @Override
    public void run() { // Wartet auf die Keywords die der Server schickt
        String input;
        do {
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
            switch (input) {//Keyword ist angekommen und es wird geschaut welche Tabelle dazugehört
                case "init" -> {
                    System.out.println("Server Active and running | waiting for keywords");
                }
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
        } while (true);
    }

    @Override
    public void addClient(updatable client, String keyword) {
        //HashMap = Es wird geschaut ob ein Client das Keyword schon nutzt = wenn ja dann wird der neue Client der vorhanden Liste hinzugefügt
        //Ansonsten wird ein neuer eintrag in der Hashmap mit einer neuen Array Liste zu dem Keyword erstellt
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
    //Jede Liste wird geprüft ob der Client da drinn ist und dann wird der Client von jeder Liste entfernt

    @Override
    public void notifyClients(String keyword) {
        List<updatable> list = this.listeners.get(keyword);
      // Array Liste die zu dem Keyword passt wird genommen und alle Clients die in der Lste stehen werden benachrichtig
        if (list != null) {
            System.out.println("Server: Notifying " + list.size() + " clients with keyword " + keyword);
            for (updatable client : list) {
                client.update(keyword);
            }
        } else {
            System.out.println("Server: No clients with keyword " + keyword);
        }
    }

}

