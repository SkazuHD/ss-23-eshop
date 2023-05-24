package org.eshop.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * The type Loger.
 */
public class Loger {
    /**
     * Log.
     *
     * @param logText the log text
     */
    public static void log(String logText) {

        try (PrintWriter pw =
                     new PrintWriter(
                             new FileWriter("log.txt", true))) {

            pw.println(new Date() + " | " + "Änderungen:" + " " + logText);
        } catch (IOException e) {


        }

    }
}


