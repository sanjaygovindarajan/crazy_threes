package interface_adapter.load_game;

import interface_adapter.start_game.StartGameOutputBoundary;

public interface LoadGameOutputBoundary extends StartGameOutputBoundary {
    /**
     * Prints an error message.
     * @param error The error message.
     */
    void prepareFailView(String error);
}
