package use_case.game_actions.load_game;

import interface_adapter.start_game.StartGameOutputBoundary;

public interface LoadGameOutputBoundary extends StartGameOutputBoundary {

    void prepareFailView(String error);
}
