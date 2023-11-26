package com.team3.autobattlerserver.Network;

import org.json.JSONObject;
import com.team3.autobattlerserver.Client.ClientHandler;

/**
 *
 * @author riola
*/
public interface PacketHandler {
    // The collection of bytes which make up 
    // the user's request.
    public void execute(ClientHandler handler, JSONObject inputBuffer);
}
