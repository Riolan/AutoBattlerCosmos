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
    LAUNCH(Arrays.asList(new GameStates[]{})),
    LOGIN(Arrays.asList(new GameStates[]{})),
    SIGNUP(Arrays.asList(new GameStates[]{})),
    MAINMENU(Arrays.asList(new GameStates[]{})),
    GAMESEARCH(Arrays.asList(new GameStates[]{})),
    ENDGAME(Arrays.asList(new GameStates[]{})),
    ENDROUND(Arrays.asList(new GameStates[]{})),
    PLAYOUTROUND(Arrays.asList(new GameStates[]{})),
    SHOP(Arrays.asList(new GameStates[]{})),
    STARTROUND(Arrays.asList(new GameStates[]{})),
    CONNECTED(Arrays.asList(new GameStates[]{})),
    TESTPANE(Arrays.asList(new GameStates[]{}));

    private List<GameStates> validStates = new LinkedList<GameStates>();

    static {
        CONNECTED.validStates = (Arrays.asList(new GameStates[]{GameStates.LAUNCH, GameStates.LOGIN,
            GameStates.SIGNUP, GameStates.MAINMENU, GameStates.GAMESEARCH, GameStates.STARTROUND,
            GameStates.SHOP, GameStates.PLAYOUTROUND, GameStates.ENDROUND, GameStates.UNCONNECTED}));
        LAUNCH.validStates = Arrays.asList(new GameStates[]{GameStates.LOGIN, GameStates.UNCONNECTED});
        STARTROUND.validStates = Arrays.asList(new GameStates[]{GameStates.PLAYOUTROUND, GameStates.UNCONNECTED});
        SHOP.validStates = Arrays.asList(new GameStates[]{GameStates.GAMESEARCH, GameStates.UNCONNECTED});
        PLAYOUTROUND.validStates = Arrays.asList(new GameStates[]{GameStates.ENDROUND, GameStates.UNCONNECTED});
        ENDROUND.validStates = Arrays.asList(new GameStates[]{GameStates.SHOP, GameStates.ENDGAME, GameStates.UNCONNECTED});
        ENDGAME.validStates = Arrays.asList(new GameStates[]{GameStates.MAINMENU, GameStates.UNCONNECTED});
        GAMESEARCH.validStates = Arrays.asList(new GameStates[]{GameStates.STARTROUND, GameStates.UNCONNECTED});
        MAINMENU.validStates = Arrays.asList(new GameStates[]{GameStates.SHOP, GameStates.LOGIN, GameStates.UNCONNECTED});
        LOGIN.validStates = Arrays.asList(new GameStates[]{GameStates.SIGNUP, GameStates.MAINMENU, GameStates.UNCONNECTED});
        SIGNUP.validStates = Arrays.asList(new GameStates[]{GameStates.LOGIN, GameStates.MAINMENU, GameStates.UNCONNECTED});
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
