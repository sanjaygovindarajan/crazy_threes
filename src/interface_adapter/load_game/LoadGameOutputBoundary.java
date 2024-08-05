package interface_adapter.load_game;

import interface_adapter.start_game.StartGameOutputBoundary;

public interface LoadGameOutputBoundary extends StartGameOutputBoundary {

    void prepareFailView(String error);
}
