/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.team3.autobattler.SceneManagement.Scenes;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.Packet.Create.GameStateChangePacket;
import com.team3.autobattler.Network.Packet.Create.ShopEntitiesPacket;
import com.team3.autobattler.Network.Packet.Create.StartGamePacket;
import com.team3.autobattler.Network.Packet.PacketElement;

/**
 *
 * @author pzex
 */
public class MainMenuScene extends javax.swing.JPanel {

    /**
     * Creates new form MainMenuScene
     */
    public MainMenuScene() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startGameButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();
        planet1 = new javax.swing.JLabel();
        planet2 = new javax.swing.JLabel();
        planet3 = new javax.swing.JLabel();
        currency = new javax.swing.JLabel();
        items = new javax.swing.JLabel();
        planet4 = new javax.swing.JLabel();
        welcomeUser = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        startGameButton.setText("Start Game");
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameButtonActionPerformed(evt);
            }
        });

        logOutButton.setText("Log Out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        planet1.setText("planet1");

        planet2.setText("planet2");

        planet3.setText("planet3");

        currency.setText("currency");

        items.setText("items");

        planet4.setText("planet4");

        welcomeUser.setText("welcome, *insert username*");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LAUNCH", "LOGIN", "SIGNUP", "MAINMENU", "SHOP", "GAMESEARCH", "STARTROUND", "PLAYOUTROUND", "ENDROUND", "ENDGAME" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(startGameButton)
                .addGap(335, 335, 335))
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(planet1)
                .addGap(148, 148, 148)
                .addComponent(planet2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addComponent(planet3)
                .addGap(116, 116, 116)
                .addComponent(planet4)
                .addGap(93, 93, 93))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(welcomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(currency)
                        .addGap(40, 40, 40)
                        .addComponent(items)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logOutButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logOutButton)
                    .addComponent(currency)
                    .addComponent(items)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(welcomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(planet1)
                    .addComponent(planet2)
                    .addComponent(planet3)
                    .addComponent(planet4))
                .addGap(104, 104, 104)
                .addComponent(startGameButton)
                .addGap(99, 99, 99))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        PacketElement statePacket = new GameStateChangePacket(GameStates.LOGIN);
        AutoBattler.socketHandler.sendData(statePacket);   
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void startGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameButtonActionPerformed
        // Start Game Packet
//PacketElement statePacket = new GameStateChangePacket(GameStates.SHOP);
//        AutoBattler.socketHandler.sendData(statePacket);
        PacketElement startGame = new StartGamePacket(true);
        AutoBattler.socketHandler.sendData(startGame);   
        PacketElement statePacket = new GameStateChangePacket(GameStates.SHOP);
        AutoBattler.socketHandler.sendData(statePacket);
    }//GEN-LAST:event_startGameButtonActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        AutoBattler.socketHandler.getClient().bypassGameState(GameStates.valueOf(jComboBox1.getSelectedItem().toString()));
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currency;
    private javax.swing.JLabel items;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logOutButton;
    private javax.swing.JLabel planet1;
    private javax.swing.JLabel planet2;
    private javax.swing.JLabel planet3;
    private javax.swing.JLabel planet4;
    private javax.swing.JButton startGameButton;
    public javax.swing.JLabel welcomeUser;
    // End of variables declaration//GEN-END:variables
}
