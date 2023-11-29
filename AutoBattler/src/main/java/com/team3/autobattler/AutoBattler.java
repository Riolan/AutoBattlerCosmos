/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.team3.autobattler;

import com.team3.autobattler.Game.GameStateObservable;
import com.team3.autobattler.Network.SocketHandler;
import com.team3.autobattler.SceneManagement.SceneManager;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Game.MyGameState;
import com.team3.autobattler.SceneManagement.Scenes.UnconnectedScene;


/**
 * The AutoBattler class represents the entry point for the client-side of the AutoBattler game.
 * It initializes essential components such as the SocketHandler for handling connections to the server
 * and the SceneManager for managing the game GUI. The main method connects to the server and starts
 * the client application.
 *
 * Clients Model
 * 
 * @author Rio
 */
public class AutoBattler {

    // Socket Handler, handles connection to Server.
    public static SocketHandler socketHandler;
    // Scene Manager, manages game GUI
    public static SceneManager sceneManager;
    
    /**
     * Constructs a new AutoBattler instance, initializing the SocketHandler and SceneManager.
     */
    public AutoBattler() {
        
        // Client Game State
        //clientGameState = GameStates.UNCONNECTED;
        //System.out.println("Current Client Game State: " + clientGameState);
        
        // SocketHandler
        // Network Manager
        socketHandler = new SocketHandler();
        

        // SceneManager https://refactoring.guru/design-patterns/mediator
        sceneManager = sceneManager.getInstance();
        System.out.println(socketHandler.getClient().setGameState(GameStates.UNCONNECTED));
    }
 
    
    /**
     * The entry point for the AutoBattler client application.
     *
     * @param args The command-line arguments.
     */
    static boolean hasConnected = false;
    /* Entry point for Client */
    public static void main(String[] args) {
        new AutoBattler();
        
        
        connect();
        //}
    }
    
     /**
     * Connects to the server using a separate thread and attempts to establish a connection every 3 seconds.
     * Displays connection status in the UNCONNECTED scene.
     */
    public static void connect() {
        // Hard coded for no reason
        String ip = "127.0.0.1";
        int port = 31228;
        StringBuilder output = new StringBuilder ();
        UnconnectedScene my = (UnconnectedScene) sceneManager.getScene(GameStates.UNCONNECTED);
        //while (!hasConnected) {
            // Connect to server
            new Thread(() -> {
                // call controller
                hasConnected = AutoBattler.socketHandler.connect(ip, port, output);            
                System.out.println("Connection Thread ---- Initialization Result: " + hasConnected);

                while (!hasConnected) {      
                    my.unconnectedLabel.setText(output.toString());

                    hasConnected = AutoBattler.socketHandler.connect(ip, port, output);
                    // Every 3 seconds try to connect to the server with the same ip & port
                    try { 
                        Thread.sleep(3000);
                    } 
                    catch (InterruptedException e) {}
                   
                }

            }).start();
            
            
    }
    
    
}
