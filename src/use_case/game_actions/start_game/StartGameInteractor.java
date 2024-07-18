package use_case.game_actions.start_game;

import entity.Game;

public class StartGameInteractor implements StartGameInputBoundary {
    Game game;
    /**
     * Starts a new game from the beginning.
     *
     * @param inputData The names of the players in the new game
     */
    @Override
    public void execute(StartGameInputData inputData) {
        this.game = new Game(inputData.getPlayers());

    }
}
