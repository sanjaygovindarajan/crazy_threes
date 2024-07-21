package use_case.player_actions.play_card;

import entity.GameInterface;
import interface_adapter.StartGameOutputBoundary;

public interface PlayCardInputBoundary {
    void playCard(int number, char suit);

    void setGame(GameInterface game);

    void playThree(char suit, char newSuit);

    StartGameOutputBoundary getPresenter();
}