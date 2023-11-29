/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network;

/**
 * The User class represents a user in the auto-battler game.
 * Each user has a unique identifier (UUID), username, and password.
 * This class follows the DAO (Data Access Object) pattern.
 *
 * @author rio
 */
public class User {
    
    private long uuid;

    private String username;
    private String password;
    
    /**
     * Constructs a new user with default values.
     */
    public User() {

    }
    
    /**
     * Gets the unique identifier (UUID) of the user.
     *
     * @return The UUID of the user.
     */
    public long getId() {
        return uuid;
    }
    
    /**
     * Sets the unique identifier (UUID) of the user.
     *
     * @param uuid The UUID to set.
     */
    public void setId(long uuid) {
        this.uuid = uuid;
    }
    
    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Sets the username of the user.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
