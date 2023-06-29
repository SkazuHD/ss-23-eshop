package org.eshop.shop;

import org.eshop.entities.*;
import org.eshop.exceptions.*;
import org.eshop.persistence.FileManager;
import org.eshop.persistence.ShopPersistence;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * The type Shop.
 */
public class Shop implements ShopFacade{

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
    public void saveAsync() {
        // Parallel Process
        new Thread(() -> {
            saveProducts();
        }).start();
    }

    /**
     * Save products.
     */
    public void saveProducts() {
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
            Collection<Products> products = productManager.getProducts();
            for (Products p : products) {
                persistence.writeProducts(p);
            }
            persistence.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void save(){
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
                    employeeManager.register(e.getUsername(), Integer.parseInt(e.getID()), e.getName(), e.getPassword());
                }
            } while (e != null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            persistence.close();
        }
        try {
            persistence.openForReading("products.csv");
            Products p;
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
    public User getUser(String username){
        User u = customerManager.getCustomer(username);
        if(u == null){
            u = employeeManager.getEmployee(username);
        }
        if (u == null){
            throw new IllegalArgumentException("User not found");
        }
        return u;
    };
    public void registerUser(String username, String password, String name, String address) throws UserExistsException {
        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || address.isEmpty()) {
            throw new IllegalArgumentException("Empty Fields");
        }

        if (!customerManager.register(username, password, name, address)) {
            throw new UserExistsException(username);
        }
        //Try Saving to File
        try {
            persistence.openForWriting("customers.csv", true);
            Customer c = customerManager.getCustomer(username);
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

    //Products
    public Collection<Products> getAllProducts() {
        return productManager.getProducts();
    }

    //CUSTOMER ONLY
    public Collection <Employee>getAllEmployees(){
        return employeeManager.getEmployee();
    }

    public void addToCart(int id, int quantity, Customer c) throws ProductNotFound, PacksizeNotMatching, NotInStockException {
        Products p = productManager.getProductById(id);

        if (p instanceof MassProducts mp) {
            if (quantity % mp.getPacksize() != 0) {
                throw new PacksizeNotMatching((mp.getPacksize()));
            }
        }
        if (p != null) {
            customerManager.buyProduct(p, quantity, c);
        } else {
            throw new ProductNotFound(id);
        }
    }

    public void removeFromCart(int id, int quantity, Customer c) throws ProductNotFound {
        Products p = productManager.getProductById(id);
        if (p != null) {
            customerManager.removeProduct(p, quantity, c);
        } else {
            throw new ProductNotFound(id);
        }
    }


    public Map<Products, Integer> getCart(Customer c) {
        return customerManager.getCart(c);
    }

    public void checkout(Customer c) {
        //TODO Check all products before removing it
        Map<Products, Integer> cart = c.getCart();
        for (Products key : cart.keySet()) {
            try {
                eventManager.addEvent(c, key, -cart.get(key));
                productManager.decreaseQuantity(key.getId(), cart.get(key));
            } catch (ProductNotFound | PacksizeNotMatching | NotInStockException e) {
                //TODO HANDLE IT CORRECTLY
                System.out.println(e.getMessage());
            }
        }
        saveAsync();
        cart.clear();
    }

    public String getInvoice(Customer c) {
        Invoice i = customerManager.checkout(c);
        return i.toString();
    }
    //EMPLOYEE ONLY


    public void createProduct(String name, double price, int quantity, User u) {
        Products p = productManager.createProduct(name, price, quantity, 0);
        saveAsync();
        eventManager.addEvent(u, p, quantity);

    }

    public void createProduct(String name, double price, int quantity, int packsize, User u) throws PacksizeNotMatching {
        if(quantity % packsize != 0){
            throw new PacksizeNotMatching(packsize);
        }
        Products p = productManager.createProduct(name, price, quantity, packsize);
        saveAsync();
        eventManager.addEvent(u, p, quantity);
    }

    @Override
    public void deleteProduct(int id) throws ProductNotFound {
        //TODO
    }

    @Override
    public Products editProductDetails(int id, String name, double price) throws ProductNotFound {
        Products p = productManager.getProductById(id);
        p.setName(name);
        p.setPrice(price);
        saveAsync();
        return p;
    }

    @Override
    public Products editProductDetails(int id, String name, double price, int packSize) throws ProductNotFound {
        MassProducts mp = (MassProducts) productManager.getProductById(id);
        mp.setName(name);
        mp.setPrice(price);
        mp.setPacksize(packSize);
        saveAsync();
        return mp;
    }

    public List<Products> findProducts(String name) {
        return productManager.getProductByName(name);
    }
    public Products findProduct(int id) throws ProductNotFound {
        return productManager.getProductById(id);
    }



    public void changeQuantity(int id, int quantity, User u) throws ProductNotFound, PacksizeNotMatching, NotInStockException {
        if(quantity>0){
            productManager.increaseQuantity(id, quantity);
        }else if (quantity<0){
            productManager.decreaseQuantity(id, quantity);
        }else {
            //Returns when quantity isnt effected -> 0
            return;
        }
        saveAsync();
        eventManager.addEvent(u, productManager.getProductById(id), quantity);

    }
    //Employees
    public void registerEmployee(int id, String username, String password, String name) throws UserExistsException {
        if (!employeeManager.register(username, id, name, password)) {
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



