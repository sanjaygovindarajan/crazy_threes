package interface_adapter;

import interface_adapter.start_game.StartGameOutputBoundary;

public interface DrawCardOutputBoundary extends StartGameOutputBoundary {
    void loadUnableToDrawCard();

    void loadShuffleView();
}
