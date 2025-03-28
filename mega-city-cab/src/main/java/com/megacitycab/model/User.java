package com.megacitycab.model;

public class User {
    private int id;
    private String name;
    private String address;
    private String nic;
    private String telephone;
    private String username;
    private String password;
    
    public User(int id, String name, String address, String nic, String telephone, String username, String password) {
    	this.id = id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.telephone = telephone;
        this.username = username;
        this.password = password;
    }

    public User(String name, String address, String nic, String telephone, String username, String password) {
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.telephone = telephone;
        this.username = username;
        this.password = password;
    }
    
    public User(String name, String address, String nic, String telephone, String username) {
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.telephone = telephone;
        this.username = username;
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}

