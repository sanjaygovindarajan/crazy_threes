package use_case.game_actions.load_game;

import entity.Game;

public interface LoadGameInputBoundary {
    void execute(LoadGameInputData loadGameInputDataInputData) throws IllegalStateException;

    Game getGame();

    void present(LoadGameInputData inputData);
}
