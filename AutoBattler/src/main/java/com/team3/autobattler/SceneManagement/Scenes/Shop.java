/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.team3.autobattler.SceneManagement.Scenes;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.Base.Player;
import com.team3.autobattler.Game.Base.UnitA.*;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.Packet.Create.BuyUnitsPacket;
import com.team3.autobattler.Network.Packet.Create.GameStateChangePacket;
import com.team3.autobattler.Network.Packet.Create.SearchForGamePacket;
import com.team3.autobattler.Network.Packet.PacketElement;
import com.team3.autobattler.SceneManagement.SceneManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Provides an interface to the user to buy, sell units, and progress to next state.
 * @author Rio
 * @author Collin
 */
public class Shop extends javax.swing.JPanel {
    final int MAX_UNITS = 5;
    Troop aTroop = new Troop();
    Player player = Player.getPlayer();
    List<PlayerPanel> listOfPlayerPanels = new ArrayList<PlayerPanel>();
    List<BuyPanel> listOfBuyPanels = new ArrayList<BuyPanel>();
    

    
    byte attemptedBuy = 0b0000;
    
    /**
     * Creates new form Shop
     */
    public Shop() {
        initComponents();

              
    }
    
    
    public void receiveData(JSONArray units) {
        // Remove all panels to make room for next on the screen
        buyPanel.removeAll();
        // clear the list so values appear in correct order.
        listOfBuyPanels.clear();
        
        // Log info
        Logger.getLogger(SceneManager.class.getName()).log(Level.INFO, "Shop Scene recieved data.");

        byte count= 0b0001;

        // Loop through each recieved
        for (int i = 0; i < units.length(); i++) {
            
            JSONObject unit = (JSONObject)units.get(i);
            String name = unit.getString("name");
            int health = unit.getInt("health");
            int attack = unit.getInt("attack");
            int cost = unit.getInt("cost");
            String ability = "N/A";

            
            // Improve this logic here later.
            // Troop logic seems kinda silly imo
            aTroop.createUnit(-1, health, attack, name, ability, cost);
            Logger.getLogger(SceneManager.class.getName()).log(Level.INFO, aTroop.aggregate.toString());
            
            BuyPanel aBuy = new BuyPanel( aTroop.aggregate.get(-1).get(aTroop.aggregate.get(-1).size() - 1));
            aBuy.buyButton.addActionListener(new BuyButtonListener(count, aTroop.aggregate.get(-1).get(aTroop.aggregate.get(-1).size() - 1)));

            
            //
            listOfBuyPanels.add(aBuy);
            count = (byte) (count << 0x1);   
        }
        
        
        handlePlayerPanel();
        // update panel
        updateBuyPanels();
    }
        
    
    public void handlePlayerPanel() {
        // Remove all panels to make room for next on the screen
        playerPanel.removeAll();
        // clear the list so values appear in correct order.
        listOfPlayerPanels.clear();

        Unit unit;
        PlayerPanel playerUnitPanel;
        
        for (int i = 0; i < player.getUnits().size(); i++) {

            if ((unit = player.getUnit(i)) != null) {
                playerUnitPanel = new PlayerPanel(unit);
                playerUnitPanel.leftButton.addActionListener(new LeftRotationListener());
                playerUnitPanel.rightButton.addActionListener(new RightRotationListener());
                playerUnitPanel.sellButton.addActionListener(new SellButtonListener(unit));
                
                listOfPlayerPanels.add(playerUnitPanel);
            
            } 
        }

        updatePlayerPanels();
        validate();
    }
    
    
    
    ////
    //      Update Panels
    ////
    private void updateBuyPanels() {
        for (BuyPanel panel : listOfBuyPanels ) {
            buyPanel.add(panel);
        }
        validate();
    }
    
    public void updatePlayerPanels() {
        for (PlayerPanel panel : listOfPlayerPanels) {
            playerPanel.add(panel);
        }
        validate();
    }
    
    
    
    ////
    //      Custom Action Listeners
    ////
    private class BuyButtonListener implements ActionListener {                                          
        byte count = 0;
        Unit unit;
        public BuyButtonListener(byte count, Unit unit) {
            this.count = count;
            this.unit = unit;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            attemptedBuy += count;
            String s1 = String.format("%8s", Integer.toBinaryString(attemptedBuy & 0xFF)).replace(' ', '0');
            System.out.println("Attempted buy is now: " + s1);
            count = 0;
            
            
            int size = player.getUnits().size();
        
            if (size < 5 && player.subtractGold(unit.getCost())) {
                player.addUnit(unit);
                System.out.println("You spent " + unit.getCost());
                // Send Packet to Server registering buy
            } else {
                    System.out.println("Missing gold requirement or have the max party size.");
            }
            
            
            handlePlayerPanel();
        }
        
    }         
    
    
    private class SellButtonListener implements ActionListener {                                          
        Unit unit;
        public SellButtonListener(Unit unit) {
            this.unit = unit;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            int size = player.getUnits().size();
        
            if (size > 1) {
                int cost = unit.getCost();
                player.removeUnit(unit);
                player.addGold(cost);
            } else {
                System.out.println("Can't have less than one unit.");
            }
            
            
            handlePlayerPanel();
        }
        
    }         
    
    
    
    
     private class LeftRotationListener implements ActionListener {
        
        public LeftRotationListener() {
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            playerPanel.removeAll();
            Collections.rotate(listOfBuyPanels, -1);  
            updatePlayerPanels();
        }
        
    }  
    
      
     private class RightRotationListener implements ActionListener {
        
        public RightRotationListener() {
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            playerPanel.removeAll();
            Collections.rotate(listOfBuyPanels, 1);  
            updatePlayerPanels();

        }
        
    }  
    

    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas1 = new java.awt.Canvas();
        canvas2 = new java.awt.Canvas();
        canvas3 = new java.awt.Canvas();
        canvas4 = new java.awt.Canvas();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        changeSceneButton = new javax.swing.JButton();
        buyPanel = new javax.swing.JPanel();
        playerPanel = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        endShop = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));

        jButton9.setText("Reroll");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Buy Item");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        changeSceneButton.setText("Change Scene");
        changeSceneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSceneButtonActionPerformed(evt);
            }
        });

        buyPanel.setBackground(new java.awt.Color(204, 255, 204));
        buyPanel.setPreferredSize(new java.awt.Dimension(300, 300));
        buyPanel.setLayout(new java.awt.GridLayout(1, 5));

        playerPanel.setBackground(new java.awt.Color(153, 255, 153));
        playerPanel.setPreferredSize(new java.awt.Dimension(300, 122));
        playerPanel.setLayout(new java.awt.GridLayout(1, 5));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LAUNCH", "LOGIN", "SIGNUP", "MAINMENU", "SHOP", "GAMESEARCH", "STARTROUND", "PLAYOUTROUND", "ENDROUND", "ENDGAME" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        endShop.setText("End Shop");
        endShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endShopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(canvas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(changeSceneButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(378, 378, 378)
                                .addComponent(jButton9)
                                .addGap(18, 18, 18)
                                .addComponent(jButton10))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(playerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                                    .addComponent(buyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(86, 86, 86)
                                .addComponent(endShop, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 45, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(160, 160, 160)
                    .addComponent(canvas4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(740, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(changeSceneButton)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(playerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(endShop, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 185, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(canvas3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(canvas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(186, 186, 186)
                    .addComponent(canvas4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(20, 20, 20)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void changeSceneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSceneButtonActionPerformed
        // TODO add your handling code here:
        
        PacketElement statePacket = new GameStateChangePacket(GameStates.GAMESEARCH);
        AutoBattler.socketHandler.sendData(statePacket);   
        PacketElement searchPacket = new SearchForGamePacket(true);
        AutoBattler.socketHandler.sendData(searchPacket);
    }//GEN-LAST:event_changeSceneButtonActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        AutoBattler.socketHandler.getClient().bypassGameState(GameStates.valueOf(jComboBox1.getSelectedItem().toString()));
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void endShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endShopActionPerformed
        // TODO add your handling code here:
        
        // Send out info about attemptedBuy
        PacketElement statePacket = new BuyUnitsPacket(attemptedBuy);
        AutoBattler.socketHandler.sendData(statePacket);    
        
        
    }//GEN-LAST:event_endShopActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buyPanel;
    private java.awt.Canvas canvas1;
    private java.awt.Canvas canvas2;
    private java.awt.Canvas canvas3;
    private java.awt.Canvas canvas4;
    private javax.swing.JButton changeSceneButton;
    private javax.swing.JButton endShop;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel playerPanel;
    // End of variables declaration//GEN-END:variables
}
