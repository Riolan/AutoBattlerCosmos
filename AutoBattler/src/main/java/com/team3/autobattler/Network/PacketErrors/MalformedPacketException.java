/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.PacketErrors;

/**
 *
 * @author riola
 */
public class MalformedPacketException extends Exception {
    public MalformedPacketException(String errorMessage) {
        super(errorMessage);
    }
}
