package org.eshop.ui;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

//package de.hsbremen.prog2.net.socket.client;

//import de.hsbremen.prog2.net.socket.Adresse;

import org.eshop.util.IoReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
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
        this.inputLoop();
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

    public void inputLoop() {
        String input = "";
        IoReader ioReader = new IoReader();
        do {
            input = ioReader.readLine("INPUT: ");
            this.out.println(input);
        } while (!input.equals("quit"));
        try {
            quit();
        } catch (IOException e) {
        }
    }

    private void suchen(String name) throws IOException {
        this.out.println("suchen");
        this.out.println(name);
        String strasse = this.in.readLine();
        if (!strasse.equals("Fehler")) {
            int plz = Integer.parseInt(this.in.readLine());
            String ort = this.in.readLine();


        } else {
            System.out.println("Kein Eintrag unter diesem Namen.");
        }

    }

    private void quit() throws IOException {
        this.out.println("Logout");
        this.socket.close();
    }
}
