/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.team3.autobattler.SceneManagement;


import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Network.Packet.Create.TestPacket;
import com.team3.autobattler.Network.Packet.PacketElement;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.*;
import com.team3.autobattler.Game.Base.Player;
import com.team3.autobattler.Game.Base.Item;
import com.team3.autobattler.Game.Base.ItemType;
import com.team3.autobattler.Game.Base.Unit;
import com.team3.autobattler.Game.Base.UnitType;
import com.team3.autobattler.Game.Factories.ItemFactory;

import java.awt.*;
import java.awt.event.*;

/**
 * Basic connect to Server GUI
 * @author riola
 */
public class ConnectToServer extends javax.swing.JPanel {

    /**
     * Creates new form ConnectToServer
     */
    public ConnectToServer() {
        initComponents();
        setBackground(Color.darkGray);
        
    }
    
    public Player player;
    public Player computer; //The computer.

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton1.setText("Connect");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Connect to the server by pressing the button");

        jButton2.setText("Test Data");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setLabel("Shop");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(182, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(58, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Connect to local host on port 31228
        // Will need to update to necessary ip later
        // if we are running an actual server.
        connect("127.0.0.1", 31228);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        PacketElement packet = new TestPacket("Ping!");
        AutoBattler.socketHandler.sendData(packet);        
        
    }//GEN-LAST:event_jButton2ActionPerformed


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    
//    player = new Player();
//    computer = new Player();
//    
//    ItemFactory iFactory = ItemFactory.getInstance();
//    
//    // Item factory should probably be an instance not a static class.
//    // Call get item for 10 or so real examples to add them to the map.
//    // Then use a random number generator to grab keys from the map.
//    //Maybe change the key relative to the size of the map so the last key is mapsize - 1.
//    // Then call map.getsize() to get the size of the map and pass into a random number generator.
//    // use that random number as a key to get a random item.
//    Item itemf = iFactory.getItem();
//    
//    System.out.print(itemf.getName());
//    
//        
//    JFrame frame = new JFrame("Shop" );
//    frame.setSize( 1820,980 );
//    frame.setLocationRelativeTo( null );
//    frame.getContentPane().setLayout(null);
//
//    JButton buttonI = new JButton("Item");
//    buttonI.addActionListener(new ActionListener()
//    {
//        public void actionPerformed(ActionEvent e) {
//            Item item = new Item(new ItemType("blah",""));
//            player.items[0] = item;
//        }
//    });
//    JButton buttonU = new JButton("Purchase");
//           buttonU.addActionListener(new ActionListener()
//    {
//        public void actionPerformed(ActionEvent e) {
//            Unit unit = new Unit(0,0,new UnitType("blah","blah"));
//            player.units[0] = unit;
//        }
//    });
//    JButton buttonJ = new JButton("Purchase");
//    buttonJ.addActionListener(new ActionListener()
//    {
//    public void actionPerformed(ActionEvent e) {
//            Unit unit = new Unit(0,0,new UnitType("blah","blah"));
//            player.units[0] = unit;
//        }
//    });
//    JButton buttonK = new JButton("Purchase");
//    buttonK.addActionListener(new ActionListener()
//    {
//    public void actionPerformed(ActionEvent e) {
//            Unit unit = new Unit(0,0,new UnitType("blah","blah"));
//            player.units[0] = unit;
//        }
//    });
//    JButton buttonL = new JButton("Purchase");
//    buttonL.addActionListener(new ActionListener()
//    {
//    public void actionPerformed(ActionEvent e) {
//            Unit unit = new Unit(0,0,new UnitType("blah","blah"));
//            player.units[0] = unit;
//        }
//    });
//    JButton buttonR = new JButton("Reroll");
//    buttonR.addActionListener(new ActionListener()
//    {
//    public void actionPerformed(ActionEvent e) {
//            Unit unit = new Unit(0,0,new UnitType("blah","blah"));
//            player.units[0] = unit;
//        }
//    });
//
//
//    buttonI.setSize(100, 50);
//    buttonU.setSize(100, 50);
//    buttonJ.setSize(100, 50);
//    buttonK.setSize(100, 50);
//    buttonL.setSize(100, 50);
//    buttonR.setSize(100, 50);
//    
//    frame.add(buttonU);
//    frame.add(buttonI);
//    frame.add(buttonJ);
//    frame.add(buttonK);
//    frame.add(buttonL);
//    frame.add(buttonR);
//    
//    buttonU.setLocation(90,800);
//    buttonI.setLocation(1390,800);
//    buttonJ.setLocation(290,800);
//    buttonK.setLocation(490,800);
//    buttonL.setLocation(690,800);
//    buttonR.setLocation(890,800);
//    
//    frame.setVisible( true );
//    
//    Item item = new Item(new ItemType("blah",""));
//    computer.items[0] = item;
//    Unit unit = new Unit(0,0,new UnitType("blah","blah"));
//    computer.units[0] = unit;
    }//GEN-LAST:event_jButton3ActionPerformed

    

    /**
     * Starts initial connection to Server.
     * @param ip
     * @param port 
     */

    private void connect(String ip, int port) {
        
        // Connect to server
        new Thread(() -> {
            // call controller
            boolean hasConnected = AutoBattler.socketHandler.connect(ip, port);            
            System.out.println("Connection Thread Initialization Result: " + hasConnected);
           
        }).start();
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
