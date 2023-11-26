/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Client;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Accesses stored user data
 * @author Emily
 */
public class ClientDataAccess {
    
    public static ClientDataAccess INSTANCE;
    
    private File dataFile = new File("ClientData.ser");
    private static Map<Number, Client> clients = new HashMap<>();
    private static Map<String, User> users = new HashMap<>();
    private int clientAmt = 0;
    
    public ClientDataAccess() throws IllegalAccessException {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the UserDataAccess class. Please use the getInstance() function.");
        }
        try {
            Client client = null;
            FileInputStream fis = new FileInputStream(dataFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean fileDone = false;
            while(!fileDone) {
                System.out.println("while");
                try {
                    client = (Client) ois.readObject();
                } catch (EOFException ex) {
                    fileDone = true;
                    System.out.println("end of file reached");
                }
                clients.put(client.getUser().getId(), client);
                users.put(client.getUser().getUsername(), client.getUser());
                clientAmt++;
                System.out.println("client found");
            }
            ois.close();
            fis.close();
            System.out.println(client.getUser().getId());
            
        } 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public Client getClient(int id) {
        return clients.get(id);
    }
    public User getUser(String username) {
        return users.get(username);
    }
    
    public int getClientAmt() {
        return clientAmt;
    }
    
    public void save(Client client) {
        clients.put(client.getUser().getId(), client);
        users.put(client.getUser().getUsername(), client.getUser());
        try {
            FileOutputStream fos = new FileOutputStream(dataFile, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write object to file
            oos.writeObject(client);
            System.out.println("saved");
            // closing resources
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
	}
    }
    
    /**
     * Retrieves the singleton instance.
     *
     * @return
     *          The singleton instance.
     */
    public static ClientDataAccess getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new ClientDataAccess();
            } catch (IllegalAccessException e) {
                
            }
        }
        return INSTANCE;
    }

    private Client Client() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
