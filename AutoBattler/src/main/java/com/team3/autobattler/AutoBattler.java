/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.team3.autobattler;

import com.team3.autobattler.Network.SocketHandler;
import com.team3.autobattler.SceneManagement.SceneManager;
import com.team3.autobattler.Game.GameStates;


/**
 *
 * @author Rio
 */

// Entry point in to the Auto Battler Game's Client
public class AutoBattler {

    // Socket Handler, handles connection to Server.
    public static SocketHandler socketHandler;
    // Scene Manager, manages game GUI
    public static SceneManager sceneManager;
    
    public static GameStates clientGameState;
    
    public AutoBattler() {
        
        // Client Game State
        clientGameState = GameStates.UNCONNECTED;
        System.out.println("Current Client Game State: " + clientGameState);
        
        // SocketHandler
        // Network Manager
        socketHandler = new SocketHandler();

        
        // Game
        // gameManager

        // SceneManager https://refactoring.guru/design-patterns/mediator
        sceneManager = sceneManager.getInstance();
    }
 
    
    /* Entry point for Client */
    public static void main(String[] args) {
        new AutoBattler();
    }
}
