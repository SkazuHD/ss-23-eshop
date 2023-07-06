package org.eshop.persistence;

import org.eshop.entities.Customer;
import org.eshop.entities.Employee;
import org.eshop.entities.Event;
import org.eshop.entities.Product;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The interface Shop persistence.
 */
public interface ShopPersistence {

    /**
     * Open for reading boolean.
     *
     * @param file the file
     * @throws FileNotFoundException the file not found exception
     */
    void openForReading(String file) throws FileNotFoundException;

    /**
     * Open for writing boolean.
     *
     * @param file   the file
     * @param append if true content will be appended to the file, otherwise the file will be overwritten
     * @throws IOException the io exception
     */
    void openForWriting(String file, boolean append) throws IOException;

    /**
     * Read customer customer.
     *
     * @return the customer
     */
    Customer readCustomer();

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
     */
    Product readProducts();

    /**
     * Write product.
     *
     * @param product the product
     */
    void writeProducts(Product product);

    void writeEvent(Event event);

    Event readEvent();

    /**
     * Close.
     */
    void close();
}
