package org.eshop.persistence;

import org.eshop.entities.*;

import java.io.*;

/**
 * The type File manager.
 */
// Was macht der File manager????
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

    public void openForReading(String file) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
    }

    public void openForWriting(String datei, boolean append) throws IOException {
        /*bereitet Datei vor damit da rein geschrieben werden kann*/
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei, append)));
    }

    /**
     * Read line string.
     *
     * @return the string
     */
    protected String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    @Override //Customer wird in die Datei reingeschrieben ! Reinfolge ist wichtig> muss im weiteren Code beachtet werden
    public void writeCustomer(Customer customer) {
        writer.print(customer.getUsername() + ";");
        writer.print(customer.getPassword() + ";");
        writer.print(customer.getName() + ";");
        writer.print(customer.getAddress() + ";");
        writer.print(customer.getID());
        writer.println();
    }

    @Override // ließt eine Zeile und erstellt Customer Objekt
    public Customer readCustomer() {
        String serial = readLine();
        if (serial == null) {
            return null;
        }
        String[] parts = serial.split(";");
        return new Customer(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }

    @Override
    public void writeEmployee(Employee employee) {
        writer.print(employee.getID() + ";");
        writer.print(employee.getName() + ";");
        writer.print(employee.getUsername() + ";");
        writer.print(employee.getPassword());
        writer.println();
    }

    @Override
    public Employee readEmployee() {
        String serial = readLine();
        if (serial == null) {
            return null;
        }
        String[] parts = serial.split(";");
        return new Employee(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]);
    }

    @Override
    public void writeProducts(Product product) {
        writer.print(product.getId() + ";");
        writer.print(product.getPrice() + ";");
        writer.print(product.getName() + ";");
        writer.print(product.getQuantity() + ";");
        writer.print(product instanceof MassProduct mp ? mp.getPacksize() : 0);
        writer.println();
    }

    @Override
    public Product readProducts() {
        String serial = readLine();
        if (serial == null) {
            return null;
        }
        String[] parts = serial.split(";");
        if (parts[4].equals("0")) {
            return new Product(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]), parts[2], Integer.parseInt(parts[3]));

        } else {
            return new MassProduct(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
        }
    }

    public void writeEvent(Event event) {
        writer.print(event.getDayInYear() + ";");
        writer.print(event.getUserId() + ";");
        writer.print(event.getProductId() + ";");
        writer.print(event.getQuantity());
        writer.println();
    }

    public Event readEvent() {
        String serial = readLine();
        if (serial == null) {
            return null;
        }
        String[] parts = serial.split(";");
        return new Event(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
    }

    public void close() {//schließt reader und Writer damit die Datei wieder Freigegeben werden kann
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
