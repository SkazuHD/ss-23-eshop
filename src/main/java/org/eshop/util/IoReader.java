package org.eshop.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IoReader {

    final BufferedReader reader;

    public IoReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        String input = "";
        do {
            try {
                input = reader.readLine();
            } catch (Exception e) {
                System.err.println("Error reading input:" + e.getMessage());
                readLine(prompt);
            }
        } while (input.equals(""));
        return input;
    }

    public int getNumericInput(String prompt) {
        System.out.print(prompt);
        int input = 0;
        do {
            try {
                input = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                System.err.println("Error reading input:" + e.getMessage());
                getNumericInput(prompt);
            }
        } while (input == 0);
        return input;
    }
}
