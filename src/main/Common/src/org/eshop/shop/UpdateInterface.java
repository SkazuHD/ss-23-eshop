package org.eshop.shop;

public interface UpdateInterface {
    void addClient(updatable client, String keyword);
    void removeClient(updatable client);
    void notifyClients(String keyword);
}
