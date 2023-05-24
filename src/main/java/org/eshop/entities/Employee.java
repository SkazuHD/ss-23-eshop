package org.eshop.entities;

public class Employee extends User {
    String name;
    int persoNr;
    String username;
    String password;

    public Employee(String name, int persoNr, String username, String password) {
        super(username, password);
        this.name = name;
        this.persoNr = persoNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPersoNr() {
        return persoNr;
    }

    public void setPersoNr(int persoNr) {
        this.persoNr = persoNr;
    }

}