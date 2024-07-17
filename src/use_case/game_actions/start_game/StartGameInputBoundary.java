package use_case.game_actions.start_game;

import entity.Game;

public interface StartGameInputBoundary {
    /**
     * Starts a new game from the beginning.
     *
     * @param inputData The names of the players in the new game
     */
    void execute(StartGameInputData inputData);
}
