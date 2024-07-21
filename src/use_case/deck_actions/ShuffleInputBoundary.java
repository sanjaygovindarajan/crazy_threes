package use_case.deck_actions;

import entity.Game;

public interface ShuffleInputBoundary {
    void shuffle();

    void setGame(Game game);
}