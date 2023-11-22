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
 * Clients Model
 * @author Rio
 */
public class AutoBattler {

    // Socket Handler, handles connection to Server.
    public static SocketHandler socketHandler;
    // Scene Manager, manages game GUI
    public static SceneManager sceneManager;
    
    
    public AutoBattler() {
        
        // Client Game State
        //clientGameState = GameStates.UNCONNECTED;
        //System.out.println("Current Client Game State: " + clientGameState);
        
        // SocketHandler
        // Network Manager
        socketHandler = new SocketHandler();
        


        // Game
        // gameManager

        // SceneManager https://refactoring.guru/design-patterns/mediator
        sceneManager = sceneManager.getInstance();
    }
 
    
    
    static boolean hasConnected = false;
    /* Entry point for Client */
    public static void main(String[] args) {
        new AutoBattler();
        
        
        connect();
        //}
    }
    
    
    public static void connect() {
                // Hard coded for no reason
        String ip = "127.0.0.1";
        int port = 31228;
        
        UnconnectedScene my = (UnconnectedScene) sceneManager.getScene(GameStates.UNCONNECTED);
        //while (!hasConnected) {
            // Connect to server
            new Thread(() -> {
                // call controller
                hasConnected = AutoBattler.socketHandler.connect(ip, port);            
                System.out.println("Connection Thread ---- Initialization Result: " + hasConnected);
                
                // This might cause a memory leak not sure (?)
                // I dont actually think it does
                while (!hasConnected) {
                    socketHandler.getClient().bypassGameState(GameStates.UNCONNECTED);
                    //socketHandler.getClient().setGameState(GameStates.UNCONNECTED);
                    my.unconnectedLabel.setText("Socket Handler Connect Error: Connection refused: no further information");
                    hasConnected = AutoBattler.socketHandler.connect(ip, port);
                    // Every 3 seconds try to connect to the server with the same ip & port
                    try { 
                        Thread.sleep(3000);
                    } 
                    catch (InterruptedException e) {}
                   
                }

            }).start();
            
            
    }
    
    
}
