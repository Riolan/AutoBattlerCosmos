/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network;

/**
 * Each client has a user. https://www.baeldung.com/java-dao-pattern
 *
 * @author riola
 */
public class User {
    
    private long uuid;

    private String username;
    private String password;
    
    public User() {

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

}
