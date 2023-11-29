/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.PacketErrors;

/**
 * The MalformedPacketException class extends the Exception class and is thrown
 * when a packet is found to be malformed or improperly constructed.
 *
 * @author  rio
 */
public class MalformedPacketException extends Exception {
     /**
     * Constructs a new MalformedPacketException with the specified error message.
     *
     * @param errorMessage A string containing the error message describing the issue.
     */
    public MalformedPacketException(String errorMessage) {
        super(errorMessage);
    }
}
