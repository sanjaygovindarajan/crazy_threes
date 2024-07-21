package use_case.deck_actions;

import entity.GameInterface;

public interface ShuffleInputBoundary {
    void shuffle();

    void setGame(GameInterface game);
}