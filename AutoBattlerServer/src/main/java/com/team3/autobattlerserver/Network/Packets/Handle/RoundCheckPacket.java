/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;

import com.team3.autobattlerserver.AutoBattlerServer;
import com.team3.autobattlerserver.Game.GameStates;
import com.team3.autobattlerserver.Game.MyGameState;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Game.Game;
import com.team3.autobattlerserver.Game.GameBoard;
import com.team3.autobattlerserver.Game.Troop;
import com.team3.autobattlerserver.Game.Unit;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketHandler;
import com.team3.autobattlerserver.Network.Packets.Create.GameResultsPacket;
import org.json.JSONObject;

import com.team3.autobattlerserver.Network.Packets.Create.GameStateChangePacket;
import com.team3.autobattlerserver.Network.Packets.Create.ShopEntitiesPacket;
import java.util.List;


/**
 * Checks how many rounds a player has played within the current game, used to 
 * end game once 10 win or 3 losses has been reached
 * 
 * @author Emily
 */
public class RoundCheckPacket implements PacketHandler {

    
    @Override
    public void execute(ClientHandler handler, JSONObject inputBuffer) {
        
        System.out.println("RoundCheckPacket, Recieved: " + inputBuffer);
        
        // Derefrence client
        Client client = handler.getClient();
        Game game = client.getGame();
        
        if (game != null) {
            if (game.getWins() < 10 && game.getLosses() < 3) {
                client.setGameState(GameStates.SHOP);
                PacketElement statePacket = new GameStateChangePacket(GameStates.SHOP);
                handler.sendData(statePacket);
            }
            else {
                PacketElement resultsPacket = new GameResultsPacket(game);
                handler.sendData(resultsPacket);
                
                client.setGameState(GameStates.ENDGAME);
                PacketElement statePacket = new GameStateChangePacket(GameStates.ENDGAME);
                handler.sendData(statePacket);

            }
        }
        
    }
    
    
    
}
