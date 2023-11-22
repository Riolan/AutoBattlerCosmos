/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.team3.autobattlerserver.Game;

/**
 *
 * @author Rio
 */
public interface GameStateObserver {
    /**
     * This function is invoked when the state of 
     * GameStateObservable changes.
     * @param o
     */
    public void update(Object o);
}
