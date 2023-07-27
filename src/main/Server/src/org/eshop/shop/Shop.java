package org.eshop.shop;

import org.eshop.entities.*;
import org.eshop.exceptions.*;
import org.eshop.persistence.FileManager;
import org.eshop.persistence.ShopPersistence;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * The type Shop.
 */
// Wenn ich nach login gefragt werde wo fange ich dann am besten an im Shop oder in der Gui???
public class Shop implements ShopFacade {

    /**
     * The Customer manager.
     */
    CustomerManager customerManager = new CustomerManager();
    /**
     * The Product manager.
     */
    ProductManager productManager = new ProductManager();
    /**
     * The Employee manager.
     */
    EmployeeManager employeeManager = new EmployeeManager();

    /**
     * The Event manager.
     */
    EventManager eventManager = new EventManager();
    /**
     * The Persistence.
     */
    ShopPersistence persistence = new FileManager();


    /**
     * Instantiates a new Shop.
     */
    public Shop() {
        load();
    }

    /**
     * Save async.
     */
    public synchronized void saveAsync() {
        // Parallel Process
        new Thread(() -> saveProducts()).start();
    }

    /**
     * Save products.
     */
    public synchronized void saveProducts() {
        //Test if file is in use
        File file = new File("products.csv");
        while (!file.renameTo(file)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            persistence.openForWriting("products.csv", false);
            Collection<Product> products = productManager.getProducts();
            for (Product p : products) {
                persistence.writeProducts(p);
            }
            persistence.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        saveProducts();
    }


    public void load() {
        try {
            persistence.openForReading("customers.csv");
            Customer c;
            do {
                c = persistence.readCustomer();
                if (c != null) {
                    customerManager.loadCustomer(c);
                }
            } while (c != null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            persistence.close();
        }
        try {
            persistence.openForReading("employees.csv");
            Employee e;
            do {
                e = persistence.readEmployee();
                if (e != null) {
                    employeeManager.register(Integer.parseInt(e.getID()), e.getName(), e.getUsername(), e.getPassword());
                }
            } while (e != null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            persistence.close();
        }
        try {
            persistence.openForReading("products.csv");
            Product p;
            do {
                p = persistence.readProducts();
                if (p != null) {
                    productManager.loadProduct(p);
                }
            } while (p != null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            persistence.close();
        }
    }

    public User getUser(String username) {
        User u = customerManager.getCustomer(username);
        if (u == null) {
            u = employeeManager.getEmployee(username);
        }
        if (u == null) {
            throw new IllegalArgumentException("User not found");
        }
        return u;
    }

    public synchronized void registerUser(String username, String password, String name, String address) throws UserExistsException {
        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || address.isEmpty()) {
            throw new IllegalArgumentException("Empty Fields");
        }

        if (!customerManager.register(username, password, name, address)) {
            throw new UserExistsException(username);
        }
        //Try Saving to File
        try {
            persistence.openForWriting("customers.csv", true);
            Customer c = customerManager.getCustomer(username);//Costumer holt den neu angelegten Customer
            persistence.writeCustomer(c);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            persistence.close();
        }

    }

    public User logIn(String username, String password) throws LoginFailed {
        try {
            return customerManager.login(username, password);
        } catch (LoginFailed e) {
            return employeeManager.login(username, password);
        }
    }


    public boolean logOut(User u) {
        u.logout();
        return u.isLoggedIn();
    }

    //Product
    public Collection<Product> getAllProducts() {
        return productManager.getProducts();
    }

    @Override
    public Collection<Event> getAllEvents() {
        return eventManager.getAllEvents();
    }

    //CUSTOMER ONLY
    public Collection<Employee> getAllEmployees() {
        return employeeManager.getEmployee();
    }

    public void addToCart(int id, int quantity, Customer c) throws ProductNotFound, PacksizeNotMatching, NotInStockException {
        Product p = productManager.getProductById(id);

        if (p instanceof MassProduct mp) {
            if (quantity % mp.getPacksize() != 0) {
                throw new PacksizeNotMatching((mp.getPacksize()));
            }
        }
        if (p != null) {
            customerManager.buyProduct(p, quantity, c);//Wird geprüft ob Produkt vohanden und
        } else {
            throw new ProductNotFound(id);
        }
    }

    public void removeFromCart(int id, int quantity, Customer c) throws ProductNotFound, PacksizeNotMatching {
        Product p = productManager.getProductById(id);
        if (p != null) {
            customerManager.removeProduct(p, quantity, c);
        } else {
            throw new ProductNotFound(id);
        }
    }

    @Override
    public void clearCart(Customer c) {
        c.clearCart();
    }


    public Map<Product, Integer> getCart(Customer c) {
        return customerManager.getCart(c);
    }

    public synchronized void checkout(Customer c) throws CheckoutFailed {
        Map<Product, Integer> cart = c.getCart();
        List<Product> productToBeFixed = new ArrayList<>();
        for (Product key : cart.keySet()) {
            if (key.getQuantity() < cart.get(key)) {
                productToBeFixed.add(key);
            }
        }
        if (productToBeFixed.size() > 0) {
            throw new CheckoutFailed(productToBeFixed);
        }
        for (Product key : cart.keySet()) {
            try {
                eventManager.addEvent(c, key, -cart.get(key));
                productManager.decreaseQuantity(key, -cart.get(key));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }


        }
        saveAsync();
        cart.clear();
    }

    public Invoice getInvoice(Customer c) {
        return customerManager.checkout(c);
    }
    //EMPLOYEE ONLY


    public synchronized void createProduct(String name, double price, int quantity, User u) {
        Product p = productManager.createProduct(name, price, quantity, 0);
        saveAsync();
        eventManager.addEvent(u, p, quantity);

    }

    public synchronized void createProduct(String name, double price, int quantity, int packsize, User u) throws PacksizeNotMatching {
        if (quantity % packsize != 0) {
            throw new PacksizeNotMatching(packsize);
        }
        Product p = productManager.createProduct(name, price, quantity, packsize);
        saveAsync();
        eventManager.addEvent(u, p, quantity);
    }

    @Override
    public void deleteProduct(int id) {
        //QOL add method to remove Product
    }

    @Override
    public synchronized Product editProductDetails(int id, String name, double price) throws ProductNotFound {
        Product p = productManager.getProductById(id);
        p.setName(name);
        p.setPrice(price);
        saveAsync();
        return p;
    }

    @Override
    public synchronized Product editProductDetails(int id, String name, double price, int packSize) throws ProductNotFound {
        MassProduct mp = (MassProduct) productManager.getProductById(id);
        mp.setName(name);
        mp.setPrice(price);
        mp.setPacksize(packSize);
        saveAsync();
        return mp;
    }

    public List<Product> findProducts(String name) {
        return productManager.getProductByName(name);
    }

    public Product findProduct(int id) throws ProductNotFound {
        return productManager.getProductById(id);
    }

    @Override
    public int[] getProductHistory(int productId, int days) {
        try {
            return eventManager.getProductHistory(productId, days, productManager.getProductById(productId).getQuantity());
        } catch (ProductNotFound exp) {
            return new int[days];
        }
    }


    public synchronized void changeQuantity(int id, int quantity, User u) throws ProductNotFound, PacksizeNotMatching, NotInStockException, NegativeQuantityException {
        if (quantity > 0) {
            productManager.increaseQuantity(id, quantity);
        } else if (quantity < 0) {
            productManager.decreaseQuantity(id, quantity);
        } else {
            //Returns when quantity isnt effected -> 0
            return;
        }
        saveAsync();
        eventManager.addEvent(u, productManager.getProductById(id), quantity);

    }

    //Employees
    public synchronized void registerEmployee(int id, String name, String username, String password) throws UserExistsException {
        if (!employeeManager.register(id, name, username, password)) {
            throw new UserExistsException(username);
        }
        //Try Saving to File
        try {
            persistence.openForWriting("employees.csv", true);
            Employee e = employeeManager.getEmployee(username);
            persistence.writeEmployee(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            persistence.close();
        }
    }


}
//Employees



