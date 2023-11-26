/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.team3.autobattler.SceneManagement;

import com.team3.autobattler.SceneManagement.Scenes.Shop;
import com.team3.autobattler.Game.GameStateObservable;
import com.team3.autobattler.Game.GameStateObserver;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Game.MyGameState;
import com.team3.autobattler.Network.Client;
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
public class SceneManager extends javax.swing.JFrame implements GameStateObserver  {
    
    public static SceneManager INSTANCE;
    CardLayout cardLayout;
    JPanel mainPanel;    
    
    TestPane testPane;
    //ConnectToServer testConnect;
    //
    LaunchScene launchScene;
    LoginScene loginScene;
    SignUpScene signUpScene;
    MainMenuScene mainMenuScene;
    Shop shopScene;
    GameSearchScene gameSearchScene;
    StartRoundScene startRoundScene;
    PlayOutRoundScene playOutRoundScene;
    EndRoundScene endRoundScene;
    EndGameScene endGameScene;
    //ShopScene shopScene;
    ImagePanel imagePanel;
    UnconnectedScene unconnectedScene;
    
    /**
     * Creates new form SceneManager
     */
    public SceneManager() throws IllegalAccessException  {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the SceneManager class. Please use the getInstance() function.");
        }
        
        try {
            this.setIconImage(new ImageIcon(SceneManager.class.getResource("/icon.png")).getImage());
        } catch (java.lang.NullPointerException ex) {
            Logger.getLogger(SceneManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setTitle("Auto Battler: Cosmos");
        
        Logger.getLogger(SceneManager.class.getName()).log(Level.INFO, "--Starting Scene Manager");
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        unconnectedScene = new UnconnectedScene();
        testPane = new TestPane();
        launchScene = new LaunchScene();
        loginScene = new LoginScene();
        signUpScene = new SignUpScene();
        mainMenuScene = new MainMenuScene();
        shopScene = new Shop();
        gameSearchScene = new GameSearchScene();
        startRoundScene = new StartRoundScene();
        playOutRoundScene = new PlayOutRoundScene();
        endRoundScene = new EndRoundScene();
        endGameScene = new EndGameScene();
        imagePanel = new ImagePanel();
        
        
        
        mainPanel.add(unconnectedScene, "unconnectedScene");
        mainPanel.add(launchScene, "launchScene");
        mainPanel.add(loginScene, "loginScene");
        mainPanel.add(signUpScene, "signUpScene");
        mainPanel.add(mainMenuScene, "mainMenuScene");
        mainPanel.add(shopScene, "shopScene");
        mainPanel.add(gameSearchScene, "gameSearchScene");
        mainPanel.add(startRoundScene, "startRoundScene");
        mainPanel.add(playOutRoundScene, "playOutRoundScene");
        mainPanel.add(endRoundScene, "endRoundScene");
        mainPanel.add(endGameScene, "endGameScene");
        mainPanel.add(unconnectedScene, "unconnectedScene");
        mainPanel.add(testPane, "testPane");
        mainPanel.add(imagePanel, "imagePanel");
        
        
        
        add(mainPanel);
        

        
        
        
        // Not sure why, but breaks things
        //initComponents();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
        
    }

    
    @Override
    public void update(Object o) {
        changeScene((GameStates)o);
    }
    
    
    /**
     * @param newScene 
     */
    public void changeScene(GameStates newScene) {
        System.out.println("Change Scene " + newScene.toString());
        switch (newScene){
            case UNCONNECTED -> cardLayout.show(mainPanel, "unconnectedScene");
            case CONNECTED -> cardLayout.show(mainPanel, "testShop");
            case LAUNCH -> cardLayout.show(mainPanel, "launchScene");
            case LOGIN -> cardLayout.show(mainPanel, "loginScene");
            case SIGNUP -> cardLayout.show(mainPanel, "signUpScene");
            case MAINMENU -> cardLayout.show(mainPanel, "mainMenuScene");
            case SHOP -> cardLayout.show(mainPanel, "shopScene");
            case GAMESEARCH -> cardLayout.show(mainPanel, "gameSearchScene");
            case STARTROUND -> cardLayout.show(mainPanel, "startRoundScene");
            case PLAYOUTROUND -> cardLayout.show(mainPanel, "playOutRoundScene");
            case ENDROUND -> cardLayout.show(mainPanel, "endRoundScene");
            case ENDGAME -> cardLayout.show(mainPanel, "endGameScene");
            case TESTPANE -> cardLayout.show(mainPanel, "testPane");
        } 
        // Validate the scene
        getScene(newScene).validate();
    }
    
    
    public javax.swing.JPanel getScene(GameStates scene) {
        switch (scene){
            case UNCONNECTED -> {
                return unconnectedScene;
            }
            case LAUNCH -> {
                return launchScene;
            }
            case LOGIN -> {
                return loginScene;
            }
            case SIGNUP -> {
                return signUpScene;
            }
            case MAINMENU -> {
                return mainMenuScene;
            }
            case SHOP -> {
                return shopScene;
            }
            case GAMESEARCH -> {
                return gameSearchScene;
            }
            case STARTROUND -> {
                startRoundScene.jPanel1.setVisible(false);
                return startRoundScene;
            }
            case PLAYOUTROUND -> {
                return playOutRoundScene;
            }
            case ENDROUND -> {
                return endRoundScene;
            }
            case ENDGAME -> {
                return endGameScene;
            }
        }

        // uhm not the best solution
        return new JPanel();
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
