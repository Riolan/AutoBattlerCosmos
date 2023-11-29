/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.team3.autobattler.SceneManagement.Scenes;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.Base.Player;
import com.team3.autobattler.Game.Base.UnitA.*;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.Packet.Create.GameStateChangePacket;
import com.team3.autobattler.Network.Packet.Create.SearchForGamePacket;
import com.team3.autobattler.Network.Packet.Create.ShopInteractionPacket;
import com.team3.autobattler.Network.Packet.PacketElement;
import com.team3.autobattler.SceneManagement.SceneManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Provides an interface to the user to buy, sell units, and progress to next state.
 * @author Rio
 * @author Collin
 */
public class Shop extends javax.swing.JPanel {
    private Image backgroundImage;
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
        this.backgroundImage = new ImageIcon(getClass().getResource("/spaceBackground.png")).getImage();
        Dimension size = new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        initComponents();

              
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
    
    
    public void receiveData(JSONArray units) {
        // Remove all panels to make room for next on the screen
        buyPanel.removeAll();
        // clear the list so values appear in correct order.
        listOfBuyPanels.clear();
        
        // Log info
        Logger.getLogger(SceneManager.class.getName()).log(Level.INFO, "Shop Scene recieved data.");

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
            aBuy.setOpaque(false);
            aBuy.buyButton.addActionListener(new BuyButtonListener(i, aTroop.aggregate.get(-1).get(aTroop.aggregate.get(-1).size() - 1)));

            
            //
            listOfBuyPanels.add(aBuy); 
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
                playerUnitPanel.setOpaque(false);
                
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
            panel.setOpaque(false);
            buyPanel.add(panel);
        }
        validate();
    }
    
    public void updatePlayerPanels() {
        for (PlayerPanel panel : listOfPlayerPanels) {
            panel.setOpaque(false);
            playerPanel.add(panel);
        }
        validate();
    }
    
    
    
    ////
    //      Custom Action Listeners
    ////
    private class BuyButtonListener implements ActionListener {                                          
        int index = 0;
        Unit unit;
        public BuyButtonListener(int index, Unit unit) {
            this.index = index;
            this.unit = unit;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check
            int size = player.getUnits().size();
        
            // Check to see if player has space for another unit
            // Check to see if player has gold to buy another unit
            if (size < 4 && player.subtractGold(unit.getCost())) {
                // Add unit to player locally
                player.addUnit(unit);
                System.out.println("You spent " + unit.getCost() + " on " + unit.getName());
                // Send Packet to Server registering buy
                // and to add unit to player on server side.
                PacketElement packet = null;
                packet = new ShopInteractionPacket(0, index);
                AutoBattler.socketHandler.sendData(packet);
            } else if (size >= 4) {
                buyErrorMsg.setText("Max party size reached");
            } else if (!player.subtractGold(unit.getCost())) {
                buyErrorMsg.setText("Not enough gold");
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
                
                int index = player.getUnits().indexOf(unit);
                player.removeUnit(unit);
                player.addGold(cost);
                
                
                PacketElement statePacket = new ShopInteractionPacket(1, index);
                AutoBattler.socketHandler.sendData(statePacket);    
        
                
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
            Collections.rotate(player.getUnits(), -1);  
            
            // Send Rotate Left Packet
            PacketElement statePacket = new ShopInteractionPacket(2, 0);
            AutoBattler.socketHandler.sendData(statePacket);
            
            handlePlayerPanel();
        }
        
    }  
    
      
     private class RightRotationListener implements ActionListener {
        
        public RightRotationListener() {
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            playerPanel.removeAll();

            Collections.rotate(player.getUnits(), 1);  
       
            
            // Send Rotate Right Packet
            PacketElement statePacket = new ShopInteractionPacket(2, 1);
            AutoBattler.socketHandler.sendData(statePacket);
            
            handlePlayerPanel();

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
        changeSceneButton = new javax.swing.JButton();
        buyPanel = new javax.swing.JPanel();
        playerPanel = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        endShop = new javax.swing.JButton();
        buyErrorMsg = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(1000, 1000));
        setLayout(null);
        add(canvas1);
        canvas1.setBounds(1000, 860, 0, 0);
        add(canvas2);
        canvas2.setBounds(43, 891, 0, 0);
        add(canvas3);
        canvas3.setBounds(53, 891, 0, 0);
        add(canvas4);
        canvas4.setBounds(160, 186, 0, 0);

        jButton9.setText("Reroll (Disabled)");
        jButton9.setEnabled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        add(jButton9);
        jButton9.setBounds(850, 510, 125, 23);

        changeSceneButton.setText("Change Scene");
        changeSceneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSceneButtonActionPerformed(evt);
            }
        });
        add(changeSceneButton);
        changeSceneButton.setBounds(880, 35, 114, 23);

        buyPanel.setBackground(new java.awt.Color(204, 255, 204));
        buyPanel.setOpaque(false);
        buyPanel.setPreferredSize(new java.awt.Dimension(300, 300));
        buyPanel.setLayout(new java.awt.GridLayout(1, 5));
        add(buyPanel);
        buyPanel.setBounds(23, 581, 880, 300);

        playerPanel.setBackground(new java.awt.Color(153, 255, 153));
        playerPanel.setOpaque(false);
        playerPanel.setPreferredSize(new java.awt.Dimension(300, 122));
        playerPanel.setLayout(new java.awt.GridLayout(1, 5));
        add(playerPanel);
        playerPanel.setBounds(23, 76, 880, 300);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LAUNCH", "LOGIN", "SIGNUP", "MAINMENU", "SHOP", "GAMESEARCH", "STARTROUND", "PLAYOUTROUND", "ENDROUND", "ENDGAME" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1);
        jComboBox1.setBounds(856, 6, 138, 23);

        endShop.setText("End Shop");
        endShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endShopActionPerformed(evt);
            }
        });
        add(endShop);
        endShop.setBounds(850, 410, 125, 89);

        buyErrorMsg.setForeground(new java.awt.Color(255, 255, 255));
        buyErrorMsg.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        add(buyErrorMsg);
        buyErrorMsg.setBounds(447, 440, 380, 34);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void changeSceneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSceneButtonActionPerformed
        // TODO add your handling code here:
        
        PacketElement searchPacket = new SearchForGamePacket(true);
        AutoBattler.socketHandler.sendData(searchPacket);
    }//GEN-LAST:event_changeSceneButtonActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        AutoBattler.socketHandler.getClient().bypassGameState(GameStates.valueOf(jComboBox1.getSelectedItem().toString()));
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void endShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endShopActionPerformed
        // TODO add your handling code here:
        
        // Send out info about attemptedBuy
        PacketElement searchPacket = new SearchForGamePacket(true);
        AutoBattler.socketHandler.sendData(searchPacket); 
        
        
    }//GEN-LAST:event_endShopActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buyErrorMsg;
    private javax.swing.JPanel buyPanel;
    private java.awt.Canvas canvas1;
    private java.awt.Canvas canvas2;
    private java.awt.Canvas canvas3;
    private java.awt.Canvas canvas4;
    private javax.swing.JButton changeSceneButton;
    private javax.swing.JButton endShop;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel playerPanel;
    // End of variables declaration//GEN-END:variables
}
