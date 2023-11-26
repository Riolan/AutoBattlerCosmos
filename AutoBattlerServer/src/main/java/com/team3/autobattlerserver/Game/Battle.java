/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game;

import com.team3.autobattlerserver.AutoBattlerServer;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.Packets.Create.BattleOutcomePacket;
import com.team3.autobattlerserver.Network.Packets.Create.GameStateChangePacket;
import com.team3.autobattlerserver.Network.Packets.Create.OpponentPacket;

/**
 * holds battle between two players
 * @author Emily
 */
public class Battle{
    private ClientHandler playerOneHandler;
    private ClientHandler playerTwoHandler;
    private Client playerOne;
    private Client playerTwo;
    private int id;
    
    public Battle(ClientHandler playerOneHandler, ClientHandler playerTwoHandler, int id) {
        // Could shorten this with a for loop/iterating a loop
        System.out.println("battle entered");
        this.id = id;
        this.playerOneHandler = playerOneHandler;
        this.playerTwoHandler = playerTwoHandler;
        this.playerOne = playerOneHandler.getClient();
        this.playerTwo = playerTwoHandler.getClient();
        
        // Set players battle state to true 
        // (might be better to represent as enum)
        playerOne.getGame().setInBattle(true);
        playerTwo.getGame().setInBattle(true);
        
        // Set player battle id
        playerOne.getGame().setBattleId(id);
        playerTwo.getGame().setBattleId(id);
        
        // Create new packet
        PacketElement packet1 = new OpponentPacket(playerTwo);
        playerOneHandler.sendData(packet1);
        
        PacketElement packet2 = new OpponentPacket(playerOne);
        playerTwoHandler.sendData(packet2);
        System.out.println("opponents sent");
        
        playerOne.setGameState(GameStates.STARTROUND);
        playerTwo.setGameState(GameStates.STARTROUND);
        
        PacketElement statePacket = new GameStateChangePacket(GameStates.STARTROUND);
        playerOneHandler.sendData(statePacket);
        playerTwoHandler.sendData(statePacket);
        
        
    }
    
    public void doBattle() {
        System.out.println("Attempting to do bnattle");
        
        System.out.println("Attempting to do bnattle1");
        
        if (playerOne.getGameState() == GameStates.PLAYOUTROUND && playerTwo.getGameState() == GameStates.PLAYOUTROUND) {
            System.out.println("doBattle");
            
            PacketElement statePacket = new GameStateChangePacket(GameStates.PLAYOUTROUND);
            playerOneHandler.sendData(statePacket);
            playerTwoHandler.sendData(statePacket);
            
            // Access players units through the troop
            Troop troop = new Troop();
            BattleLogic battleLogic = new BattleLogic(troop.getUnits((int)playerOne.getUser().getId()),
                    troop.getUnits((int)playerTwo.getUser().getId()));
            
            
            System.out.println("");
            
            // Analyze action sequence
            System.out.println(battleLogic.getSequence());
            for (int i = 0; i < battleLogic.sequence.size(); i++) {
                System.out.println(String.valueOf(battleLogic.getSequence().get(i)));
            }
            
            String victor = battleLogic.getVictor();
            if (victor.equals("Team1")){
                playerOne.getUser().addCurrency(10);
                playerOne.getGame().incrementWins();
                playerTwo.getUser().addCurrency(3);
                playerTwo.getGame().incrementLosses();
                
                PacketElement resultPacket1 = new BattleOutcomePacket("Victory", 10);
                playerOneHandler.sendData(resultPacket1);
                PacketElement resultPacket2 = new BattleOutcomePacket("Defeat", 3);
                playerTwoHandler.sendData(resultPacket2);
            }
            else if (victor.equals("Team2")) {
                playerOne.getUser().addCurrency(3);
                playerOne.getGame().incrementLosses();
                playerTwo.getUser().addCurrency(10);
                playerTwo.getGame().incrementWins();
                
                PacketElement resultPacket1 = new BattleOutcomePacket("Defeat", 3);
                playerOneHandler.sendData(resultPacket1);
                PacketElement resultPacket2 = new BattleOutcomePacket("Victory", 10);
                playerTwoHandler.sendData(resultPacket2);
            }
            else if (victor.equals("Draw")) {
                playerOne.getUser().addCurrency(5);
                playerOne.getGame().incrementDraws();
                playerTwo.getUser().addCurrency(5);
                playerTwo.getGame().incrementDraws();
                
                PacketElement resultPacket = new BattleOutcomePacket("Draw", 5);
                playerOneHandler.sendData(resultPacket);
                playerTwoHandler.sendData(resultPacket);
            }
            
            AutoBattlerServer.clientMatchmaker.matches.remove(id, this);
            playerOne.getGame().setInBattle(false);
            playerTwo.getGame().setInBattle(false);
        }
    }
    
    public Client getPlayerOne() {
        return playerOne;
    }
    
    public Client getPlayerTwo() {
        return playerTwo;
    }
    
    public int getId() {
        return id;
    }
}

