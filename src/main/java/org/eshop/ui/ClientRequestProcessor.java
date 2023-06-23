//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.eshop.ui;

//import de.hsbremen.prog2.net.socket.Adresse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

class ClientAddressRequestProcessor {

    private final Socket clientSocket;
    private BufferedReader in;
    private PrintStream out;

    public ClientAddressRequestProcessor(Socket socket) {
        this.clientSocket = socket;


        try {
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            this.out = new PrintStream(this.clientSocket.getOutputStream());
        } catch (IOException var6) {
            try {
                this.clientSocket.close();
            } catch (IOException var5) {
            }

            System.err.println("Ausnahme bei Bereitstellung des Streams: " + var6);
            return;
        }

        PrintStream var10000 = System.out;
        String var10001 = String.valueOf(this.clientSocket.getInetAddress());
        var10000.println("Verbunden mit Client " + var10001 + ":" + this.clientSocket.getPort());
    }

    public void verarbeiteAnfragen() {
        String input = "";
        out.println("Server bereit");

        do {
            input = null;
            try {
                input = in.readLine();
            } catch (Exception e) {
                System.out.println("--->Fehler beim Lesen vom Client (Aktion): ");
                System.out.println(e.getMessage());
            }
            System.out.println(input);

            if (input == null) {
                input = "quit";
            }

            switch (input) {
                case "search":
                    System.out.println("search");
                case "quit":
                    System.out.println("quit");
            }
        } while (!input.equals("quit"));

        PrintStream var10000 = System.out;
        String var10001 = String.valueOf(this.clientSocket.getInetAddress());
        var10000.println("Verbindung zu " + var10001 + ":" + this.clientSocket.getPort() + " durch Client abgebrochen");

        try {
            this.clientSocket.close();
        } catch (IOException var4) {
        }

    }


}
