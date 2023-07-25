package org.eshop.network;

import org.eshop.shop.Shop;
import org.eshop.shop.UpdateInterface;
import org.eshop.shop.updatable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EshopServer implements UpdateInterface {
    public static final int DEFAULT_PORT = 6789;
    private final int port;
    private final Shop server;
    private ServerSocket serverSocket;
    private final List<updatable> activeClients = new ArrayList<>();


    public EshopServer(int optPort) {
        this.port = optPort == 0 ? DEFAULT_PORT : optPort;
        this.server = new Shop();
        try {
            this.serverSocket = new ServerSocket(this.port);
            InetAddress ia = InetAddress.getLocalHost();
            System.out.println("Host: " + ia.getHostName());
            String server = ia.getHostAddress();
            System.out.println("Server *" + server + "* lauscht auf Port " + this.port);
        } catch (IOException ioException) {
            System.err.println("Eine Ausnahme trat beim Anlegen des Server-Sockets auf: " + ioException);
            System.exit(1);
        }


    }

    public static void main(String[] args) {
        int port = 0;
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException var3) {
                port = 0;
            }
        }
        EshopServer server = new EshopServer(port);
        server.acceptClientConnectRequests();


    }

    public void acceptClientConnectRequests() {
        try {
            while (true) {
                Socket clientSocket = this.serverSocket.accept();
                ClientRequestProcessor c = new ClientRequestProcessor(clientSocket, this.server, this);
                Thread t = new Thread(c);
                t.start();
            }
        } catch (IOException ioException) {
            System.err.println("Fehler während des Wartens auf Verbindungen: " + ioException);
            System.exit(1);
        }
    }

    @Override
    public void addClient(updatable client, String keyword) {
        activeClients.add(client);
        System.out.println(activeClients.size());
    }

    @Override
    public void removeClient(updatable client) {

        activeClients.remove(client);
    }

    @Override
    public void notifyClients(String keyword) {
        System.out.println("Notifying clients: "+keyword);
        for (updatable client : activeClients) {
            client.update(keyword);
        }
    }
}
