package use_case.player_actions;

import entity.InvalidCardException;

public interface PlayCardInputBoundary {
    void playCard(int number, char suit) throws InvalidCardException;
}