/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * The GameStates enum represents different states in the AutoBattler game.
 * Each state defines a set of valid next states, allowing controlled state transitions.
 *
 * @author rio
 */
// Abstract this out to another not dependent on Scene
// copy too server both will maintain the state, and some reconcilliationn/stop from
// cheating is required. look into State Pattern enum
public enum GameStates {
    UNCONNECTED(Arrays.asList(new GameStates[]{})),
    CONNECTED(Arrays.asList(new GameStates[]{})),
    LAUNCH(Arrays.asList(new GameStates[]{})),
    LOGIN(Arrays.asList(new GameStates[]{})),
    SIGNUP(Arrays.asList(new GameStates[]{})),
    MAINMENU(Arrays.asList(new GameStates[]{})),
    SHOP(Arrays.asList(new GameStates[]{})),
    GAMESEARCH(Arrays.asList(new GameStates[]{})),
    STARTROUND(Arrays.asList(new GameStates[]{})),
    PLAYOUTROUND(Arrays.asList(new GameStates[]{})),
    ENDROUND(Arrays.asList(new GameStates[]{})),
    ENDGAME(Arrays.asList(new GameStates[]{})),
    TESTPANE(Arrays.asList(new GameStates[]{}));

    private List<GameStates> validStates = new LinkedList<GameStates>();

    static {
        CONNECTED.validStates = (Arrays.asList(new GameStates[]{GameStates.LAUNCH, GameStates.LOGIN,
            GameStates.SIGNUP, GameStates.MAINMENU, GameStates.GAMESEARCH, GameStates.STARTROUND,
            GameStates.SHOP, GameStates.PLAYOUTROUND, GameStates.ENDROUND, GameStates.UNCONNECTED, GameStates.CONNECTED}));
        LAUNCH.validStates = Arrays.asList(new GameStates[]{GameStates.LOGIN, GameStates.UNCONNECTED, GameStates.CONNECTED});
        LOGIN.validStates = Arrays.asList(new GameStates[]{GameStates.SIGNUP, GameStates.MAINMENU, GameStates.UNCONNECTED, GameStates.CONNECTED});
        SIGNUP.validStates = Arrays.asList(new GameStates[]{GameStates.LOGIN, GameStates.MAINMENU, GameStates.UNCONNECTED, GameStates.CONNECTED});
        MAINMENU.validStates = Arrays.asList(new GameStates[]{GameStates.SHOP, GameStates.LOGIN, GameStates.UNCONNECTED, GameStates.CONNECTED});
        SHOP.validStates = Arrays.asList(new GameStates[]{GameStates.GAMESEARCH, GameStates.UNCONNECTED, GameStates.CONNECTED});
        GAMESEARCH.validStates = Arrays.asList(new GameStates[]{GameStates.STARTROUND, GameStates.UNCONNECTED, GameStates.CONNECTED});
        STARTROUND.validStates = Arrays.asList(new GameStates[]{GameStates.PLAYOUTROUND, GameStates.UNCONNECTED, GameStates.CONNECTED});
        PLAYOUTROUND.validStates = Arrays.asList(new GameStates[]{GameStates.ENDROUND, GameStates.UNCONNECTED, GameStates.CONNECTED});
        ENDROUND.validStates = Arrays.asList(new GameStates[]{GameStates.SHOP, GameStates.ENDGAME, GameStates.UNCONNECTED, GameStates.CONNECTED});
        ENDGAME.validStates = Arrays.asList(new GameStates[]{GameStates.MAINMENU, GameStates.UNCONNECTED, GameStates.CONNECTED});
        UNCONNECTED.validStates = Arrays.asList(new GameStates[]{GameStates.CONNECTED, GameStates.UNCONNECTED});
    }
    
    /**
     * Constructs a new GameStates enum value with a list of valid states.
     *
     * @param validStates The list of valid next states.
     */
    GameStates(List<GameStates> vaildStates) {
        this.validStates = validStates;
    }

    /**
     * Checks if a transition from the current state to the next state is valid.
     *
     * @param fromState The current state.
     * @param toState   The next state.
     * @return True if the transition is valid, false otherwise.
     */
    public boolean canChangeGameState(GameStates fromState, GameStates toState) {
        return fromState.validStates.contains(toState);
    }

}
