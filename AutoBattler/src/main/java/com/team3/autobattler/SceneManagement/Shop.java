/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.team3.autobattler.SceneManagement;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.Base.Player;
import com.team3.autobattler.Game.Base.Item;
import com.team3.autobattler.Game.Base.UnitA.*;
import com.team3.autobattler.Game.Factories.ItemFactory;
import com.team3.autobattler.Network.Packet.Create.TestPacket;
import com.team3.autobattler.Network.Packet.PacketElement;
import com.team3.autobattler.SceneManagement.Scenes.BuyPanel;
import java.util.List;


import com.team3.autobattler.SceneManagement.Scenes.PlayerPanel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author colli
 */
public class Shop extends javax.swing.JPanel {
//    Don't care about items atm can implement later
//    ItemFactory itemFactory = ItemFactory.getInstance();
//    Item item = itemFactory.getItem();
    final int MAX_UNITS = 5;
    Troop aTroop = new Troop();
    Player player = Player.getPlayer();
    List<PlayerPanel> listOfPlayerPanels = new ArrayList<PlayerPanel>();
    List<BuyPanel> listOfBuyPanels = new ArrayList<BuyPanel>();
    
    
    /**
     * Creates new form Shop
     */
    public Shop() {
        initComponents();
        
    }
    
    public void handlePlayerPanel() {
        playerPanel.removeAll();
        listOfPlayerPanels.clear();
        
        Unit unit;
        
        //int count =  player.getUnits().size();
        for (int i = 0; i < MAX_UNITS; i++) {
            if ((unit = player.getUnit(i)) != null) listOfPlayerPanels.add(new PlayerPanel(unit));
            else {
                listOfPlayerPanels.add(new PlayerPanel());
            }
        }
        
        updatePlayerPanels();
        validate();
    }
    
    
    /**
     * Takes in a Packets Array Data.
     * @param units 
     */
    public void recieveData(JSONArray units) {
        // Remove old input
        buyPanel.removeAll();
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
            
            // Troop logic seems kinda silly imo
            //aTroop.createUnit(health, attack, name, ability, cost);
            
            //listOfBuyPanels.add(new BuyPanel(aTroop.aggregate.get(aTroop.aggregate.size() - 1)));
        }
        handlePlayerPanel();
        // update panel
        updateBuyPanels();
    }
    
    public void updatePlayerPanels() {
        for (PlayerPanel panel : listOfPlayerPanels) {
            playerPanel.add(panel);
        }
        validate();
    }
    private void updateBuyPanels() {
        for (BuyPanel panel : listOfBuyPanels ) {
            buyPanel.add(panel);
        }
        validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        playerPanel = new javax.swing.JPanel();
        buyPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        canvas1 = new java.awt.Canvas();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        playerPanel.setBackground(new java.awt.Color(255, 153, 153));
        playerPanel.setMinimumSize(new java.awt.Dimension(100, 100));
        playerPanel.setLayout(new java.awt.GridLayout());
        jPanel1.add(playerPanel);

        buyPanel.setBackground(new java.awt.Color(204, 255, 204));
        buyPanel.setMinimumSize(new java.awt.Dimension(100, 100));
        buyPanel.setPreferredSize(new java.awt.Dimension(300, 300));
        buyPanel.setLayout(new java.awt.GridLayout(1, 5));
        jPanel1.add(buyPanel);

        add(jPanel1, new java.awt.GridBagConstraints());

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        canvas1.setPreferredSize(new java.awt.Dimension(300, 200));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buyPanel;
    private java.awt.Canvas canvas1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel playerPanel;
    // End of variables declaration//GEN-END:variables
}
