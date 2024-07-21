package use_case.player_actions;

import entity.Game;
import entity.InvalidCardException;
import interface_adapter.StartGameOutputBoundary;

public interface PlayCardInputBoundary {
    void playCard(int number, char suit);

    void setGame(Game game);

    void playThree(char suit, char newSuit);

    StartGameOutputBoundary getPresenter();
}