package org.eshop.network;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

//package de.hsbremen.prog2.net.socket.server;

//import de.hsbremen.prog2.net.socket.Adresse;

import org.eshop.shop.Shop;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EshopServer {
    public static final int DEFAULT_PORT = 6789;
    private final int port;
    private final Shop server;
    private ServerSocket serverSocket;


    public EshopServer(int optPort) {
        this.port = optPort == 0 ? 6789 : optPort;
        this.server = new Shop();
        try {
            this.serverSocket = new ServerSocket(this.port);
            InetAddress ia = InetAddress.getLocalHost();
            System.out.println("Host: " + ia.getHostName());
            PrintStream var10000 = System.out;
            String var10001 = ia.getHostAddress();
            var10000.println("Server *" + var10001 + "* lauscht auf Port " + this.port);
        } catch (IOException var3) {
            System.err.println("Eine Ausnahme trat beim Anlegen des Server-Sockets auf: " + var3);
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
                ClientRequestProcessor c = new ClientRequestProcessor(clientSocket, this.server);
                Thread t = new Thread(c);
                t.start();
            }
        } catch (IOException var3) {
            System.err.println("Fehler während des Wartens auf Verbindungen: " + var3);
            System.exit(1);
        }
    }
}
