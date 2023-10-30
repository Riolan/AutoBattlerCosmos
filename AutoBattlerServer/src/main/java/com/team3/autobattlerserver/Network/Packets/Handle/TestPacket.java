/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Network.PacketCreator;
import com.team3.autobattlerserver.Network.PacketCreatorFactory;
import com.team3.autobattlerserver.Network.PacketHandler;
import org.json.JSONObject;


/**
 *
 * @author riola
 * @Todo Rename to HandleTestPacket or TestPacketHandler or rename execute to handle()
 */
public class TestPacket implements PacketHandler {
    
    @Override
    public void execute(JSONObject inputBuffer) {
        
        // validate(inputBuffer) apart of PacketHandler, general
        // validation, then implement proper, more extansive
        System.out.println("Test Packet Handler, Recieved: " + inputBuffer);
        PacketCreatorFactory packetCreatorFactory = new PacketCreatorFactory();
        
        PacketCreator x = packetCreatorFactory.make(0);
        JSONObject data = x.create();
        // Need to also pass information about which client handler called but for testing assume 0
        ClientHandler.clientHandlers.get(0).sendJSON(data);
        
    }
    
   
    
}
