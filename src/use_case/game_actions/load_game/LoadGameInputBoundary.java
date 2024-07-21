package use_case.game_actions.load_game;

import entity.Game;
import entity.GameInterface;

public interface LoadGameInputBoundary {
    void execute(LoadGameInputData loadGameInputDataInputData) throws IllegalStateException;

    GameInterface getGame();

    void present(LoadGameInputData inputData);
}
