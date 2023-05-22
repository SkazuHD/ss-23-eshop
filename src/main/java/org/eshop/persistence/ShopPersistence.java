package org.eshop.persistence;

import org.eshop.entities.Customer;
import org.eshop.entities.Employee;
import org.eshop.entities.Products;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The interface Shop persistence.
 */
public interface ShopPersistence {

    /**
     * Open for reading boolean.
     *
     * @param datei the datei
     * @return the boolean
     * @throws FileNotFoundException the file not found exception
     */
    boolean openForReading(String datei) throws FileNotFoundException;

    /**
     * Open for writing boolean.
     *
     * @param datei  the datei
     * @param append the append
     * @return the boolean
     * @throws IOException the io exception
     */
    boolean openForWriting(String datei, boolean append) throws IOException;

    /**
     * Read customer customer.
     *
     * @return the customer
     * @throws IOException the io exception
     */
    Customer readCustomer() throws IOException;

    /**
     * Write customer.
     *
     * @param customer the customer
     */
    void writeCustomer(Customer customer);

    /**
     * Read employee employee.
     *
     * @return the employee
     */
    Employee readEmployee();

    /**
     * Write employee.
     *
     * @param employee the employee
     */
    void writeEmployee(Employee employee);

    /**
     * Read products products.
     *
     * @return the products
     * @throws IOException the io exception
     */
    Products readProducts() throws IOException;

    /**
     * Write products.
     *
     * @param products the products
     */
    void writeProducts(Products products);

    /**
     * Close.
     */
    void close();
}
