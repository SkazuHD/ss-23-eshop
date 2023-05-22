package org.eshop.persistence;

import org.eshop.entities.Customer;
import org.eshop.entities.Employee;
import org.eshop.entities.Products;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ShopPersistence {

    boolean openForReading(String datei) throws FileNotFoundException;

    boolean openForWriting(String datei, boolean append) throws IOException;

    Customer readCustomer() throws IOException;

    void writeCustomer(Customer customer);

    Employee readEmployee();

    void writeEmployee(Employee employee);

    Products readProducts() throws IOException;

    void writeProducts(Products products);

    void close();
}
