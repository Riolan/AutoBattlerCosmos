/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.team3.autobattler.SceneManagement.Scenes;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.Base.Player;
import com.team3.autobattler.Game.Base.Item;
import com.team3.autobattler.Game.Base.UnitA.*;
import com.team3.autobattler.Game.Factories.ItemFactory;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.Packet.Create.BuyUnitsPacket;
import com.team3.autobattler.Network.Packet.Create.GameStateChangePacket;
import com.team3.autobattler.Network.Packet.Create.ShopEntitiesPacket;
import com.team3.autobattler.Network.Packet.Create.TestPacket;
import com.team3.autobattler.Network.Packet.PacketElement;
import com.team3.autobattler.SceneManagement.SceneManager;
import com.team3.autobattler.SceneManagement.Scenes.BuyPanel;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.team3.autobattler.SceneManagement.Scenes.PlayerPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY_OR_MOVE;
import static javax.swing.TransferHandler.MOVE;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author Rio
 */
public class Shop extends javax.swing.JPanel {
    final int MAX_UNITS = 5;
    Troop aTroop = new Troop();
    Player player = Player.getPlayer();
    List<PlayerPanel> listOfPlayerPanels = new ArrayList<PlayerPanel>();
    List<BuyPanel> listOfBuyPanels = new ArrayList<BuyPanel>();
    
    final DefaultListModel<Unit> from = new DefaultListModel<>();
    final DefaultListModel<Unit> move = new DefaultListModel<>();
    
    
    JList<Unit> dragFrom;
    JList<Unit> moveTo;
    byte attemptedBuy = 0b0000;
    
    /**
     * Creates new form Shop
     */
    public Shop() {
        initComponents();

        this.setLayout(new GridLayout(3, 3));       

        // Drag panel
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        dragFrom = new JList<Unit>(from);
        dragFrom.setTransferHandler(new customTransferHandler(TransferHandler.MOVE));
        dragFrom.setDragEnabled(true);
        dragFrom.setDropMode(DropMode.INSERT);
        dragFrom.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JLabel label = new JLabel("Drag from here:");
        label.setAlignmentX(0f);
        p.add(label);
        JScrollPane sp = new JScrollPane(dragFrom);
        sp.setAlignmentX(0f);
        p.add(sp);
        add(p, BorderLayout.WEST);
        
        // Move
        
        moveTo = new JList<Unit>(move);
        moveTo.setTransferHandler(new customTransferHandler(TransferHandler.MOVE));
        moveTo.setDropMode(DropMode.INSERT);
        moveTo.setDragEnabled(true);
        moveTo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        moveTo.setCellRenderer(new ComboBoxRenderer());
        // Copy Panel
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        sp = new JScrollPane(moveTo);
        
        
        sp.setAlignmentX(0f);
        p.add(sp);
        label = new JLabel("Drop to MOVE to here:");
        label.setAlignmentX(0f);
        p.add(label);
        add(p, BorderLayout.CENTER);       
    }
    
    
    public void createLists() {
        
    }
    
    class ComboBoxRenderer extends JLabel implements ListCellRenderer<Unit> {
    public ComboBoxRenderer() {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }

    /*
     * This method finds the image and text corresponding
     * to the selected value and returns the label, set up
     * to display the text and image.
     */
    @Override
    public Component getListCellRendererComponent(
                                       JList<? extends Unit> list,
                                       Unit value,
                                       int index,
                                       boolean isSelected,
                                       boolean cellHasFocus) {
        //Get the selected index. (The index parameter isn't
        //always valid, so just use the value.)
        list.setVisibleRowCount(5);
        
        setText(value.getName());
        
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }

    }
    
    
    public void recieveData(JSONArray units) {
        // Remove old input
        buyPanel.removeAll();
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

            // Troop logic seems kinda silly imo
            aTroop.createUnit(-1, health, attack, name, ability, cost);
            Logger.getLogger(SceneManager.class.getName()).log(Level.INFO, aTroop.aggregate.toString());
            
            BuyPanel aBuy = new BuyPanel( aTroop.aggregate.get(-1).get(aTroop.aggregate.get(-1).size() - 1));
            aBuy.buyButton.addActionListener(new buyButtonListener(count));
            //
            from.add(0, aTroop.aggregate.get(-1).get(aTroop.aggregate.get(-1).size() - 1));
            move.add(0, aTroop.aggregate.get(-1).get(aTroop.aggregate.get(-1).size() - 1));
            listOfBuyPanels.add(aBuy);
            count = (byte) (count << 0x1);   
        }
        handlePlayerPanel();
        // update panel
        updateBuyPanels();
        
        
        Unit unit = aTroop.aggregate.get(-1).get(0);

    }
    
    public class ObjectTransferable<E> implements Transferable {

    /*
     * This class can be used to transfer any kind of java class.
     * Can only be used within the same JVM.
     */

    private final DataFlavor[] flavors;
    private final E obj;

    public ObjectTransferable(E object) throws ClassNotFoundException {
        obj = object;
        flavors = new DataFlavor[] {
            new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class="+object.getClass().getName())
        };
    }

    public ObjectTransferable(E object, DataFlavor flavor) {
        obj = object;
        flavors = new DataFlavor[] {
            flavor
        };
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (!isDataFlavorSupported(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        return obj;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavors[0].equals(flavor);
    }

}
    
    
    
    class customTransferHandler extends TransferHandler {
        int action;
        private int index = 0;
        DefaultListModel sharedModel;
        boolean skip = false;
        
        
        ObjectTransferable<Unit> unit_;

        public customTransferHandler(int action) {
            this.action = action;
            aTroop.createUnit(-9, 1, 1, "", "", 1);
            try {
                unit_ = new ObjectTransferable<Unit>(aTroop.aggregate.get(-9).get(0));
            } catch (Exception e) {
                System.out.println("big uh oh");
            }
            
        }
        
        @Override
        public int getSourceActions(JComponent comp) {
            return MOVE;
        }



        @Override
        public Transferable createTransferable(JComponent comp) {
            JList c = (JList)comp;
            DefaultListModel model = (DefaultListModel)c.getModel();
            index = c.getSelectedIndex();
            
            this.sharedModel = model;
            
            if ((model == from) && (move.getSize() >= 5)) {
                return null;
            }
            
        
            if (model == from) {
                System.out.println("->>>> from");  
            }
            
            if (model == move) {
                System.out.println("->>>> move");  
            }
            
            
            if (index < 0 || (index >= model.getSize())) {
                return null;
            }

            try {
                return new ObjectTransferable<Unit>((Unit)c.getSelectedValue());
            } catch (Exception e) {
                 System.out.println("Somethign went wrong");
            }
            return null;
        }
        
        @Override
        public void exportDone(JComponent comp, Transferable trans, int action) {
            if (action != MOVE) {
                return;
            }
            
            System.out.println("----------------------");  
            JList c = (JList)comp;
            DefaultListModel model = (DefaultListModel)c.getModel();

            if (skip) {
                // pass we just swap instead
            } else {
                model.removeElementAt(index);
            }
            
            for (int i = 0; i < move.getSize(); i++) {
                System.out.println(i + " " + move.get(i));
            }
           
        }
        
        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            // for the demo, we'll only support drops (not clipboard paste)
            System.out.println("======================================");
            if (!support.isDrop()) {
                return false;
            }

            
            
            if (!support.isDataFlavorSupported(unit_.getTransferDataFlavors()[0])) {
                return false;
            }


            boolean actionSupported = (action & support.getSourceDropActions()) == action;
            if (actionSupported) {
                support.setDropAction(action);
                return true;
            }

            return false;
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            // if we can't handle the import, say so
            if (!canImport(support)) {
                return false;
            }
            System.out.println("-wwwwwwwwwwwwwwwwwwwwwwww-");


            // fetch the drop location
            JList.DropLocation dl = (JList.DropLocation)support.getDropLocation();

            int aindex = dl.getIndex();

            JList list = (JList)support.getComponent();
            DefaultListModel<Unit> model = (DefaultListModel)list.getModel();


            
            
            // fetch the data and bail if this fails
            Unit data;
            try {
                data = (Unit)support.getTransferable().getTransferData(unit_.getTransferDataFlavors()[0]);
                // TODO IMPLEMENT LOGIC FOR VALID MOVEMENT   
                
                int cost = data.getCost();
                System.out.println("plr gold = 10: " + player.getGold());
                //player.subtractGold(10);
                //System.out.println("plr gold = 0 now: " + player.getGold());
                System.out.println("Attempted cost is: " + cost);
                if (!player.subtractGold(cost) && (model != from)) {
                    System.out.println("The Player does not have enough to buy this unit.");
                    return false;
                } else if (model == from) {
                    player.addGold(cost);
                    System.out.println("Added: " + cost + " to player.");
                }
                System.out.println("plr gold after: " + player.getGold());
                
            } catch (UnsupportedFlavorException e) {
                return false;
            } catch (java.io.IOException e) {
                return false;
            }

     
            // swap
            //if (model == sharedModel) {
            
            if (model == sharedModel) {
                List<Object> asList = Arrays.asList(model.toArray());
                if (aindex == model.getSize()) aindex--;
                Collections.swap(asList, aindex, index); 
                model.clear();
                for(Object val : asList) {
                    model.addElement((Unit)val);
                }
                skip = true;
            } else {
                model.insertElementAt(data, aindex);
            }
            return true;
        }  
    }

    
    
    private class buyButtonListener implements ActionListener {                                          
        byte count = 0;
        
        public buyButtonListener(byte count) {
            this.count = count;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            attemptedBuy += count;
            String s1 = String.format("%8s", Integer.toBinaryString(attemptedBuy & 0xFF)).replace(' ', '0');
            System.out.println("Attempted buy is now: " + s1);
            count = 0;
        }
        
    }                 
    
    
    
     public void handlePlayerPanel() {
        playerPanel.removeAll();
        listOfPlayerPanels.clear();

        Unit unit;

        for (int i = 0; i < MAX_UNITS; i++) {
            if ((unit = player.getUnit(i)) != null) listOfPlayerPanels.add(new PlayerPanel(unit));
            else {
                listOfPlayerPanels.add(new PlayerPanel());
            }
        }

        updatePlayerPanels();
        validate();
    }
    
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

        javax.swing.GroupLayout playerPanelLayout = new javax.swing.GroupLayout(playerPanel);
        playerPanel.setLayout(playerPanelLayout);
        playerPanelLayout.setHorizontalGroup(
            playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        playerPanelLayout.setVerticalGroup(
            playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(playerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addComponent(endShop, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(playerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(changeSceneButton)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(endShop, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
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

        for (int i = 0; i < moveTo.getModel().getSize(); i++) {
            Unit unit = (Unit) moveTo.getModel().getElementAt(i);
            
            System.out.println("Unit->"+ unit.getName());
        }
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void changeSceneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSceneButtonActionPerformed
        // TODO add your handling code here:
        
        PacketElement statePacket = new GameStateChangePacket(GameStates.GAMESEARCH);
        AutoBattler.socketHandler.sendData(statePacket);    
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