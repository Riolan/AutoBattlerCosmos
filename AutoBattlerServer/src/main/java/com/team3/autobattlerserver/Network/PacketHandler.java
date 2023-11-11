package com.team3.autobattlerserver.Network;

import org.json.JSONObject;

/**
 *
 * @author riola
*/
public interface PacketHandler {
    // The collection of bytes which make up 
    // the user's request.
    public void execute(int aId, JSONObject inputBuffer);
}
