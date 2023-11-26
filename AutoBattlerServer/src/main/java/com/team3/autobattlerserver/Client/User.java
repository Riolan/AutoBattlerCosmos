/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Client;

import com.team3.autobattlerserver.AutoBattlerServer;
import java.io.Serializable;

/**
 * Each client has a user. https://www.baeldung.com/java-dao-pattern
 *
 * @author riola
 */
public class User implements Serializable {
    private static final long serialVersionUID = -6691078704282048003L;

    private long uuid;

    private String username;
    private String password;
    
    private int currency;
    
    public User( ) {
        this.username = null;
        this.password = null;
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public long getId() {
        return uuid;
    }
    public void setId(long uuid) {
        this.uuid = uuid;
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
    
    public int getCurrency() {
        return currency;
    }
    
    public void setCurrency(int currency) {
        this.currency = currency;
        AutoBattlerServer.userDataAccess.updateUserData(this);
    }
    
    public void addCurrency(int amount) {
        this.currency += amount;
        AutoBattlerServer.userDataAccess.updateUserData(this);
    }
    
    public void subtractCurrency(int amount) {
        this.currency -= amount;
        AutoBattlerServer.userDataAccess.updateUserData(this);
    }
}
