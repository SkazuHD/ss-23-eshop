package org.eshop.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        try (PrintWriter pw = new PrintWriter(new FileWriter("log.txt", true))) {
            pw.println(getDayOfYear() + "|" + timestamp() + "|" + "Changes: " + logText);
        } catch (IOException ignored) {

        }
    }

    public static String timestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    public static String getDayOfYear() {
        return String.valueOf(LocalDate.now().getDayOfYear());
    }
}


