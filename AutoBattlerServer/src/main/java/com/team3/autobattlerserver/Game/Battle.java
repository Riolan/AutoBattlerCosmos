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
 *
 * @author pzex
 */
public class Battle{
    private ClientHandler playerOneHandler;
    private ClientHandler playerTwoHandler;
    private Client playerOne;
    private Client playerTwo;
    private int id;
    
    public Battle(ClientHandler playerOneHandler, ClientHandler playerTwoHandler) {
        System.out.println("battle entered");
        this.playerOneHandler = playerOneHandler;
        this.playerTwoHandler = playerTwoHandler;
        this.playerOne = playerOneHandler.getClient();
        this.playerTwo = playerTwoHandler.getClient();
        
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
    
    public void doBattle() {
        
    }
    
}
