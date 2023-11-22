/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game.Matchmaker;

import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Game.Battle;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Every n seconds check to see who requested to start a game.
 * 
 * @author Rio
 */
public class ClientMatchmaker {
    
    
    public static ClientMatchmaker INSTANCE;

    private static Queue<ClientHandler> clientQueue = new LinkedList<>();

    Timer timer = new Timer();
    
    public ClientMatchmaker() throws IllegalAccessException {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the SceneManager class. Please use the getInstance() function.");
        }
        
        final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Date x = new Date();
                System.out.println(x + " - " + clientQueue);
                
                if (clientQueue.size() >= 2) {
                    System.out.println("match found");
                    for (int i = 0; i < clientQueue.size() / 2; i++ ) {
                        ClientHandler playerOne = clientQueue.remove();
                        ClientHandler playerTwo = clientQueue.remove();
                        new Battle(playerOne, playerTwo);
                    }
                    
                }
            }
        }, 0, 5, TimeUnit.SECONDS);
        
    }
    
    /**
     * Retrieves the singleton instance.
     *
     * @return
     *          The singleton instance.
     */
    public static ClientMatchmaker getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new ClientMatchmaker();
            } catch (IllegalAccessException e) {
                
            }
        }
        return INSTANCE;
    }
    
    
    public void addClient(ClientHandler newClient) {
        //if (clientQueue.isEmpty()) return;
        clientQueue.add(newClient);
    }
    
    public void removeClient(ClientHandler client) {
        clientQueue.remove(client);
    }
}
