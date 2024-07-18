package view;

import entity.Game;
import use_case.player_actions.NewGame;

public class StartGame {
    public static void main(String[] args) {
        System.out.println("You are now playing the game Crazy Three's! /n");
        NewGame.setNamesList();
        NewGame.setPlayers();
    }
}