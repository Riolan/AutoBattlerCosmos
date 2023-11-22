/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game;

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
    CONNECTED(Arrays.asList(new GameStates[]{}));

    private List<GameStates> validStates = new LinkedList<GameStates>();

    static {
        CONNECTED.validStates = (Arrays.asList(new GameStates[]{GameStates.LOGIN, GameStates.LAUNCH,
            GameStates.SIGNUP, GameStates.MAINMENU, GameStates.GAMESEARCH, GameStates.STARTROUND,
            GameStates.SHOP, GameStates.PLAYOUTROUND, GameStates.ENDROUND}));

        STARTROUND.validStates = Arrays.asList(new GameStates[]{GameStates.PLAYOUTROUND});
        SHOP.validStates = Arrays.asList(new GameStates[]{GameStates.GAMESEARCH});
        PLAYOUTROUND.validStates = Arrays.asList(new GameStates[]{GameStates.ENDROUND});
        ENDROUND.validStates = Arrays.asList(new GameStates[]{GameStates.SHOP, GameStates.ENDGAME});
        ENDGAME.validStates = Arrays.asList(new GameStates[]{GameStates.MAINMENU});
        GAMESEARCH.validStates = Arrays.asList(new GameStates[]{GameStates.STARTROUND});
        LAUNCH.validStates = Arrays.asList(new GameStates[]{GameStates.LOGIN, GameStates.MAINMENU});
        MAINMENU.validStates = Arrays.asList(new GameStates[]{GameStates.SHOP, GameStates.LOGIN});
        LOGIN.validStates = Arrays.asList(new GameStates[]{GameStates.SIGNUP, GameStates.MAINMENU});
        SIGNUP.validStates = Arrays.asList(new GameStates[]{GameStates.LOGIN, GameStates.MAINMENU});
        UNCONNECTED.validStates = Arrays.asList(new GameStates[]{GameStates.CONNECTED});
    }

    GameStates(List<GameStates> vaildStates) {
        this.validStates = validStates;
    }

    public boolean canChangeGameState(GameStates fromState, GameStates toState) {
        return fromState.validStates.contains(toState);
    }

}
