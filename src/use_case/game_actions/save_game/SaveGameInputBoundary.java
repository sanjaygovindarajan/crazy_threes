package use_case.game_actions.save_game;

import entity.Game;
import entity.GameInterface;

import java.io.IOException;

public interface SaveGameInputBoundary {
    /**
     * Saves a game based on input data.
     * @param inputData The game name
     */
    void execute(SaveGameInputData inputData);
    /**
     * Sets the current game being played.
     * @param game The game currently being played.
     */
    void setGame(GameInterface game);
}
