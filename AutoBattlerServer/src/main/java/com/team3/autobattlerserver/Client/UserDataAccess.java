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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Accesses stored user data
 * @author Emily
 */
public class UserDataAccess {
    
    public static UserDataAccess INSTANCE;
    private String dataFileName = "UserData.ser";
    private File dataFile = new File(dataFileName);
    private static Map<Number, User> users = new HashMap<>();
    private int userAmt = 0;
    
    public UserDataAccess() throws IllegalAccessException {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the UserDataAccess class. Please use the getInstance() function.");
        }
        try (FileInputStream fis = new FileInputStream(dataFile);
            ObjectInputStream ois = new ObjectInputStream(fis)){
            
            boolean fileDone = false;
            while(!fileDone) {
                try {
                    User user = (User) ois.readObject();
                    users.put(user.getId(), user);
                    userAmt++;
                } catch (EOFException ex) {
                    fileDone = true;
                    System.out.println("end of file reached");
                } catch (ClassNotFoundException | ClassCastException e) {
                    e.printStackTrace();
                }
            }
        } 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.getMap();
    }
    
    public User getUser(int id) {
        return users.get(id);
    }
    
    public User getUser(String username) {
        for (User entry : users.values()) {
            if (username.equals(entry.getUsername())) {
                return entry;
            }
        }
        return null;
    }
    
    public int getUserAmt() {
        return userAmt;
    }
    
    public void getMap() {
        for (Map.Entry<Number, User> entry : users.entrySet()) {
            
                System.out.println(entry.getKey() + " " + entry.getValue().getId() + " " + entry.getValue().getUsername());
            }
    }
    public List<User> getData() {
        List<User> users = new ArrayList();
        try (FileInputStream fis = new FileInputStream(dataFile);
            ObjectInputStream ois = new ObjectInputStream(fis);) {
            User user = null;
            
            boolean fileDone = false;
            while(!fileDone) {
                try {
                    user = (User) ois.readObject();
                    users.add(user);
                } catch (EOFException ex) {
                    fileDone = true;
                    System.out.println("end of file reached");
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return users;
    }
    
    public void save(User user) {
        users.put(user.getId(), user);
        userAmt++;
        try (FileOutputStream fos = new FileOutputStream("temp.ser", true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            for (User entry : users.values()) {
                oos.writeObject(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file
        new File("temp.ser").renameTo(new File(dataFileName));
    }
    
    public void updateUserData(User newUser) {
        System.out.println("updateUserData");
        try (FileInputStream fis = new FileInputStream(dataFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            FileOutputStream fos = new FileOutputStream("temp.ser", true);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            // Read objects from the file
            User oldUser;

            while ((oldUser = (User) ois.readObject()) != null) {
                // Check if this is the object to be modified
                if (oldUser.getId() == newUser.getId()) {
                    oos.writeObject(newUser);
                    
                } else {
                    // Write unchanged objects to the temporary file
                    oos.writeObject(oldUser);
                }
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file
        new File("temp.ser").renameTo(new File(dataFileName));
    }
    
    public void deleteUser(User user) {
        try (FileInputStream fis = new FileInputStream(dataFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            FileOutputStream fos = new FileOutputStream("temp.ser", true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            // Read objects from the file
            User oldUser;

            while ((oldUser = (User) ois.readObject()) != null) {
                // Check if this is the object to be modified
                if (oldUser.getId() == user.getId()) {
                    
                } else {
                    // Write unchanged objects to the temporary file
                    oos.writeObject(oldUser);
                }
                
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file
        new File("temp.ser").renameTo(new File(dataFileName));
    }
    
    /**
     * Retrieves the singleton instance.
     *
     * @return
     *          The singleton instance.
     */
    public static UserDataAccess getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new UserDataAccess();
            } catch (IllegalAccessException e) {
                
            }
        }
        return INSTANCE;
    }
}
