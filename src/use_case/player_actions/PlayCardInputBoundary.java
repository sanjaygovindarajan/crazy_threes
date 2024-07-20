package use_case.player_actions;

import entity.Game;
import entity.InvalidCardException;

public interface PlayCardInputBoundary {
    void playCard(int number, char suit) throws InvalidCardException;

    void setGame(Game game);
}