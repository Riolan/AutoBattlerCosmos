/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.team3.autobattler.SceneManagement;

import com.team3.autobattler.Game.GameStateObservable;
import com.team3.autobattler.Game.GameStateObserver;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Game.MyGameState;
import com.team3.autobattler.SceneManagement.Scenes.*;
import java.awt.CardLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * https://stackoverflow.com/questions/3718435/refresh-jframe-after-adding-new-components
 * @author riola
 */
public class SceneManager extends javax.swing.JFrame {
    
    public static SceneManager INSTANCE;
    CardLayout cardLayout;
    JPanel mainPanel;    
    
    TestPane testPane;
    ConnectToServer testConnect;
    //
    LoginScene loginScene;



    
    /**
     * Creates new form SceneManager
     */
    public SceneManager() throws IllegalAccessException {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the SceneManager class. Please use the getInstance() function.");
        }
        
        System.out.println(new File("res/icon.png"));
        try {
            this.setIconImage(ImageIO.read(new File("res/icon.png")));
        } catch (IOException ex) {
            Logger.getLogger(SceneManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setTitle("Auto Battler: Cosmos");
        
        System.out.println("Starting Scene Manager");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        testPane = new TestPane();
        testConnect = new ConnectToServer();
        //
        loginScene = new LoginScene();
        
        
        mainPanel.add(loginScene, "loginScene");
        mainPanel.add(testConnect, "testConnect");
        mainPanel.add(testPane, "testPane");
        //
        
        add(mainPanel);
        

        
        
        
        // Not sure why, but breaks things
        //initComponents();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
        
    }

    /**
     * Ok, this is pretty bad implementation. Would like to set up an
     * observer so it just updates when gameState is updated. It is literally
     * based on the game state so.....
     * @param newScene 
     */
    public void changeScene(GameStates newScene) {

        
        System.out.println("Change Scene " + newScene.toString());
        switch (newScene){
            case UNCONNECTED:
                // Show the menu
                cardLayout.show(mainPanel, "testConnect");
                break;
            case CONNECTED:
                // Show the menu
                cardLayout.show(mainPanel, "testConnect");
                break;
            case LOGIN:
                cardLayout.show(mainPanel, "loginScene");
                break;
        }

    }

    
    
    /**
     * Retrieves the singleton instance.
     *
     * @return
     *          The singleton instance.
     */
    public static SceneManager getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new SceneManager();
            } catch (IllegalAccessException e) {
                
            }
        }
        return INSTANCE;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
