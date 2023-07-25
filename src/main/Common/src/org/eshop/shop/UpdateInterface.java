package org.eshop.shop;

public interface UpdateInterface {
    void addClient(updatable client);
    void removeClient(updatable client);
    void notifyClients();
}
