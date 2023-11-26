/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game;

import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Network.PacketElement;
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
        System.out.println("battle entered");
        this.id = id;
        this.playerOneHandler = playerOneHandler;
        this.playerTwoHandler = playerTwoHandler;
        this.playerOne = playerOneHandler.getClient();
        this.playerTwo = playerTwoHandler.getClient();
        
        playerOne.getGame().setInBattle(true);
        playerTwo.getGame().setInBattle(true);
        playerOne.getGame().setBattleId(id);
        playerTwo.getGame().setBattleId(id);
        
        PacketElement packet1 = new OpponentPacket(playerTwo);
        PacketElement packet2 = new OpponentPacket(playerOne);
        playerOneHandler.sendData(packet1);
        playerTwoHandler.sendData(packet2);
        System.out.println("opponents sent");
        
        playerOne.setGameState(GameStates.STARTROUND);
        playerTwo.setGameState(GameStates.STARTROUND);
        
        PacketElement statePacket = new GameStateChangePacket(GameStates.STARTROUND);
        playerOneHandler.sendData(statePacket);
        playerTwoHandler.sendData(statePacket);
        
        
    }
    
    public void doBattle(Client client ) {
        System.out.println("Attempting to do bnattle");
        
        System.out.println("Attempting to do bnattle1" + client.getGameState());
        
        if (client.getGameState() == GameStates.PLAYOUTROUND) {
            System.out.println("doBattle");
            BattleLogic battleLogic = new BattleLogic(playerOne.getUnits(), playerTwo.getUnits());
            System.out.println(battleLogic.getSequence());
            for (int i = 0; i < battleLogic.sequence.size(); i++) {
                System.out.println(String.valueOf(battleLogic.getSequence().get(i)));
            }
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

