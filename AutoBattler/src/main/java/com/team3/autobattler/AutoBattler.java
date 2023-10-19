/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.team3.autobattler;

import com.team3.autobattler.SceneManagement.TestPane;
import com.team3.autobattler.SceneManagement.ConnectToServer;
import com.team3.autobattler.SceneManagement.GameFrame;
import com.team3.autobattler.Network.SocketHandler;
import com.team3.autobattler.SceneManagement.SceneManager;



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
    
    public AutoBattler() {
        // SocketHandler
        socketHandler = new SocketHandler();
        // Game

        // SceneManager https://refactoring.guru/design-patterns/mediator
        // Currently a game frame
        sceneManager = sceneManager.getInstance();
        //sceneManager.changeScene(SceneManager.GameStates.TEST);
        //sceneManager.changeScene(sceneManager.GameStates.TEST);
        // Ensure proper loading.
        //NewScene test = new NewScene();
        //sceneManager.changeScene(test);
        
        //gameFrame = new GameFrame();
        
    }
 
    
    /* Entry point for Client */
    public static void main(String[] args) {
        new AutoBattler();
    }
}
