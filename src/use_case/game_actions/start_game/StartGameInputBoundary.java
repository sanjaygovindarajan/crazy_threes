package use_case.game_actions.start_game;

import entity.Game;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import use_case.player_actions.PlayCardInputBoundary;

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

    Game getGame();
}
