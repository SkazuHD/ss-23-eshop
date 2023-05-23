package org.eshop.persistence;

import org.eshop.entities.Customer;
import org.eshop.entities.Employee;
import org.eshop.entities.Products;

import java.io.*;

/**
 * The type File manager.
 */
public class FileManager implements ShopPersistence {
    /**
     * The Reader.
     */
    BufferedReader reader = null;
    /**
     * The Writer.
     */
    PrintWriter writer = null;

    /**
     * Instantiates a new File manager.
     */
    public FileManager() {
    }

    public boolean openForReading(String datei) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(datei));
        return reader != null;
    }

    public boolean openForWriting(String datei, boolean append) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei, append)));
        return writer != null;
    }

    @Override
    public void writeCustomer(Customer customer) {
        writer.print(customer.getUsername() + ";");
        writer.print(customer.getPassword() + ";");
        writer.print(customer.getName() + ";");
        writer.print(customer.getAddress() + ";");
        writer.println();
    }

    @Override
    public Customer readCustomer() throws IOException {
        String serial = reader.readLine();
        if (serial == null) {
            return null;
        }
        String[] parts = serial.split(";");
        return new Customer(parts[0], parts[1], parts[2], parts[3]);
    }

    @Override
    public void writeEmployee(Employee employee) {
        writer.print(employee.getName() + ";");
        writer.print(employee.getPersoNr() + ";");
        writer.print(employee.getUsername() + ";");
        writer.print(employee.getPassword() + ";");
        writer.println();
    }

    @Override
    public Employee readEmployee() throws IOException {
        String serial = reader.readLine();
        if (serial == null) {
            return null;
        }
        String[] parts = serial.split(";");
        return new Employee(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]);
    }

    @Override
    public void writeProducts(Products products) {
        writer.print(products.getProductnumber() + ";");
        writer.print(products.getPrice() + ";");
        writer.print(products.getName() + ";");
        writer.print(products.getQuantity() + ";");
        writer.println();
    }

    @Override
    public Products readProducts() throws IOException {
        String serial = reader.readLine();
        if (serial == null) {
            return null;
        }
        String[] parts = serial.split(";");
        return new Products(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]), parts[2], Integer.parseInt(parts[3]));
    }

    public void close() {
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Error while closing the file");
        }

    }
}
