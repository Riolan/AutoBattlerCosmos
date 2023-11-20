/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Client;

/**
 * Each client has a user. https://www.baeldung.com/java-dao-pattern
 *
 * @author riola
 */
public class User {
    
    private long uuid;

    private String username;
    private String password;
    
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

}
