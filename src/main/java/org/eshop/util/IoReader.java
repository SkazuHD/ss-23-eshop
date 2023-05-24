package org.eshop.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The type Io reader.
 */
public class IoReader {

    /**
     * The Reader.
     */
    final BufferedReader reader;

    /**
     * Instantiates a new Io reader.
     */
    public IoReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Read line string.
     *
     * @param prompt the prompt
     * @return the string
     */
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
        return input.trim();
    }

    /**
     * Gets numeric input.
     *
     * @param prompt the prompt
     * @return the numeric input as int
     */
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

    /**
     * Gets double input.
     *
     * @param prompt the prompt
     * @return the double input
     */
    public double getDoubleInput(String prompt) {
        System.out.print(prompt);
        double d = 0;
        do {
            try {
                d = Double.parseDouble(reader.readLine());
            } catch (Exception e) {
                System.err.println("Error reading input:" + e.getMessage());
                getDoubleInput(prompt);
            }
        } while (d == 0);
        return d;
    }
}
