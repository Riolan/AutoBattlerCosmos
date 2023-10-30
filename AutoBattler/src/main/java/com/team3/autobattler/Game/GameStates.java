/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author riola
 */
// Abstract this out to another not dependent on Scene
// copy too server both will maintain the state, and some reconcilliationn/stop from
// cheating is required. look into State Pattern enum
public enum GameStates {
    UNCONNECTED(Arrays.asList(new GameStates[]{})),
    LOGIN(Arrays.asList(new GameStates[]{})),
    SIGNUP(Arrays.asList(new GameStates[]{})),
    MAINMENU(Arrays.asList(new GameStates[]{})),
    GAMESEARCH(Arrays.asList(new GameStates[]{})),
    INGAME(Arrays.asList(new GameStates[]{})),
    ENDGAME(Arrays.asList(new GameStates[]{})),
    ENDROUND(Arrays.asList(new GameStates[]{})),
    PLAYOUTROUND(Arrays.asList(new GameStates[]{})),
    SHOP(Arrays.asList(new GameStates[]{})),
    STARTROUND(Arrays.asList(new GameStates[]{})),
    CONNECTED(Arrays.asList(new GameStates[]{}));

    private List<GameStates> validStates = new LinkedList<GameStates>();

    static {
        CONNECTED.validStates = (Arrays.asList(new GameStates[]{GameStates.LOGIN,
            GameStates.SIGNUP, GameStates.MAINMENU, GameStates.GAMESEARCH, GameStates.STARTROUND,
            GameStates.SHOP, GameStates.PLAYOUTROUND, GameStates.ENDROUND, GameStates.INGAME}));

        INGAME.validStates = Arrays.asList(new GameStates[]{GameStates.STARTROUND});

        STARTROUND.validStates = Arrays.asList(new GameStates[]{GameStates.SHOP});
        SHOP.validStates = Arrays.asList(new GameStates[]{GameStates.PLAYOUTROUND});
        PLAYOUTROUND.validStates = Arrays.asList(new GameStates[]{GameStates.ENDROUND});
        ENDROUND.validStates = Arrays.asList(new GameStates[]{GameStates.STARTROUND, GameStates.ENDGAME});
        ENDGAME.validStates = Arrays.asList(new GameStates[]{GameStates.MAINMENU});
        GAMESEARCH.validStates = Arrays.asList(new GameStates[]{GameStates.INGAME});
        MAINMENU.validStates = Arrays.asList(new GameStates[]{GameStates.GAMESEARCH});
        LOGIN.validStates = Arrays.asList(new GameStates[]{GameStates.SIGNUP, GameStates.MAINMENU});
        SIGNUP.validStates = Arrays.asList(new GameStates[]{GameStates.LOGIN});
        UNCONNECTED.validStates = Arrays.asList(new GameStates[]{GameStates.CONNECTED});
    }
    
    /**
     * 
     * @param vaildStates 
     */
    GameStates(List<GameStates> vaildStates) {
        this.validStates = validStates;
    }

    /**
     * Using current state, check if next state is a valid change.
     * @param fromState
     * @param toState
     * @return 
     */
    public boolean canChangeGameState(GameStates fromState, GameStates toState) {
        return fromState.validStates.contains(toState);
    }

}
