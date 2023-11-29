/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.team3.autobattler.SceneManagement.Scenes;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.Base.Player;
import com.team3.autobattler.Game.Base.UnitA.Unit;
import com.team3.autobattler.Game.Base.UnitA.UnitType;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.Packet.Create.GameStateChangePacket;
import com.team3.autobattler.Network.Packet.Create.StartBattlePacket;
import com.team3.autobattler.Network.Packet.PacketElement;
import com.team3.autobattler.SceneManagement.SceneManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Emily
 */
public class StartRoundScene extends javax.swing.JPanel {
    private Image backgroundImage;
    Player player = Player.getPlayer();
    List<UnitPanel> listOfPlayerPanels = new ArrayList<UnitPanel>();
    List<UnitPanel> listOfOpponentPanels = new ArrayList<UnitPanel>();
    /**
     * Creates new form PlayOutRoundScene
     */
    public StartRoundScene() {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    public void receiveData(String opponentName, JSONArray opponentUnits) {
        
        playerPanel.removeAll();
        opponentPanel.removeAll();
        // Log info
        Logger.getLogger(SceneManager.class.getName()).log(Level.INFO, "StartRoundScene recieved data.");
        
        List<Unit> playerUnits = player.getUnits();
        
        for (int i = 0; i < playerUnits.size(); i++) {
            UnitPanel aUnit = new UnitPanel(playerUnits.get(i));
            aUnit.setOpaque(false);
            listOfPlayerPanels.add(aUnit);
        }
        
        // Loop through each recieved
        for (int i = 0; i < opponentUnits.length(); i++) {
            
            JSONObject unit = (JSONObject)opponentUnits.get(i);
            String unitName = unit.getString("name");
            int health = unit.getInt("health");
            int attack = unit.getInt("attack");
            int cost = unit.getInt("cost");
            String ability = "N/A";
            
            UnitType unitType = new UnitType(unitName, ability, cost);
            Unit newUnit = new Unit(health, attack, unitType);
            UnitPanel aUnit = new UnitPanel(newUnit);
            aUnit.setOpaque(false);
            listOfOpponentPanels.add(aUnit);
        }
        
        updatePlayerPanels();
        updateOpponentPanels();
        validate();
    }

    ////
    //      Update Panels
    ////
    private void updatePlayerPanels() {
        for (UnitPanel panel : listOfPlayerPanels ) {
            panel.setOpaque(false);
            playerPanel.add(panel);
        }
        validate();
    }
    
    public void updateOpponentPanels() {
        for (UnitPanel panel : listOfOpponentPanels) {
            panel.setOpaque(false);
            opponentPanel.add(panel);
        }
        validate();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        playerName = new javax.swing.JLabel();
        opponentName = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        waitingMsg = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        opponentPanel = new javax.swing.JPanel();
        playerPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 204));
        setLayout(null);

        jButton1.setText("Battle");
        jButton1.setBounds(new java.awt.Rectangle(464, 610, 72, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(470, 590, 72, 23);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LAUNCH", "LOGIN", "SIGNUP", "MAINMENU", "SHOP", "GAMESEARCH", "STARTROUND", "PLAYOUTROUND", "ENDROUND", "ENDGAME" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1);
        jComboBox1.setBounds(856, 6, 138, 23);

        playerName.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        playerName.setForeground(new java.awt.Color(255, 255, 255));
        playerName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        playerName.setText("*player name*");
        add(playerName);
        playerName.setBounds(150, 80, 260, 40);

        opponentName.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        opponentName.setForeground(new java.awt.Color(255, 255, 255));
        opponentName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opponentName.setText("*opponent name*");
        add(opponentName);
        opponentName.setBounds(570, 80, 260, 40);

        jPanel1.setVisible(false);
        jPanel1.setBounds(new java.awt.Rectangle(370, 200, 260, 80));

        waitingMsg.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        waitingMsg.setText("Waiting for other player...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(waitingMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(waitingMsg)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        add(jPanel1);
        jPanel1.setBounds(370, 230, 260, 80);

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("VS");
        add(jLabel9);
        jLabel9.setBounds(440, 70, 110, 60);

        opponentPanel.setBounds(new java.awt.Rectangle(520, 330, 440, 250));

        javax.swing.GroupLayout opponentPanelLayout = new javax.swing.GroupLayout(opponentPanel);
        opponentPanel.setLayout(opponentPanelLayout);
        opponentPanelLayout.setHorizontalGroup(
            opponentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        opponentPanelLayout.setVerticalGroup(
            opponentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        add(opponentPanel);
        opponentPanel.setBounds(510, 330, 440, 250);

        playerPanel.setBounds(new java.awt.Rectangle(40, 330, 440, 250));

        javax.swing.GroupLayout playerPanelLayout = new javax.swing.GroupLayout(playerPanel);
        playerPanel.setLayout(playerPanelLayout);
        playerPanelLayout.setHorizontalGroup(
            playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        playerPanelLayout.setVerticalGroup(
            playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        add(playerPanel);
        playerPanel.setBounds(30, 330, 440, 250);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        PacketElement battlePacket = new StartBattlePacket(true);
        AutoBattler.socketHandler.sendData(battlePacket);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        AutoBattler.socketHandler.getClient().bypassGameState(GameStates.valueOf(jComboBox1.getSelectedItem().toString()));
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JLabel opponentName;
    private javax.swing.JPanel opponentPanel;
    public javax.swing.JLabel playerName;
    private javax.swing.JPanel playerPanel;
    private javax.swing.JLabel waitingMsg;
    // End of variables declaration//GEN-END:variables
}
