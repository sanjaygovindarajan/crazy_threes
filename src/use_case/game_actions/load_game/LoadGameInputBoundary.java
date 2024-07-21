package use_case.game_actions.load_game;

import entity.GameInterface;

public interface LoadGameInputBoundary {
    void execute(LoadGameInputData loadGameInputDataInputData) throws IllegalStateException;

    /**
     *
     * @return
     */
    GameInterface getGame();

    void present();
}
