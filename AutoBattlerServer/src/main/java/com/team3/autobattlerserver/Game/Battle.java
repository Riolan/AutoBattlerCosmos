/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game;

import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.Packets.Create.GameStateChangePacket;
/**
 *
 * @author pzex
 */
public class Battle{
    private ClientHandler playerOneHandler;
    private ClientHandler playerTwoHandler;
    private Client playerOne;
    private Client playerTwo;
    
    public Battle(ClientHandler playerOneHandler, ClientHandler playerTwoHandler) {
        System.out.println("battle entered");
        this.playerOneHandler = playerOneHandler;
        this.playerTwoHandler = playerTwoHandler;
        this.playerOne = playerOneHandler.getClient();
        this.playerTwo = playerTwoHandler.getClient();
        
        // Send users to next state.
        PacketElement packet = new GameStateChangePacket(GameStates.STARTROUND);
        this.playerOneHandler.sendData(packet);
        this.playerTwoHandler.sendData(packet);
        
        
        
        
    }
    
}
