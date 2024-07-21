package use_case.game_actions.start_game;

import entity.GameInterface;

public interface StartGameInputBoundary {
    /**
     * Starts a new game from the beginning.
     *
     * @param inputData The names of the players in the new game
     */
    void execute(StartGameInputData inputData);

    /**
     * Loads presenter view. (Only called by other interactor)
     */
    void present();

    GameInterface getGame();
}
