package org.eshop.util;

import org.eshop.entities.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * The type Logger.
 */
public class Logger {
    /**
     * Log.
     * Adds a log entry to the log file where a user is involved
     *
     * @param u       the u
     * @param logText the log text
     */
    public static void log(User u, String logText) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("log.txt", true))) {
            pw.println(getDayOfYear() + "|" + timestamp() + "|User:" + u.toString() + "|Changes: " + logText);
        } catch (IOException ignored) {

        }
    }

    /**
     * Log.
     * Adds a log entry to the log file where no user is involved
     *
     * @param logText the log text
     */
    public static void log(String logText) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("log.txt", true))) {
            pw.println(getDayOfYear() + "|" + timestamp() + "|Changes: " + logText);
        } catch (IOException ignored) {

        }
    }

    /**
     * Timestamp string.
     *
     * @return the string
     */
    public static String timestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    /**
     * Gets day of year.
     *
     * @return the day of year
     */
    public static String getDayOfYear() {
        return String.valueOf(LocalDate.now().getDayOfYear());
    }
}


