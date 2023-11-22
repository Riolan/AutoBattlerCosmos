/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game;

/**
 * Hi 
 * @author Rio
 */
public interface GameStateObservable {
    public void addObserver(GameStateObserver observer);
   
    public void removeObserver(GameStateObserver observer);

    public void notifyObservers();
    
}
