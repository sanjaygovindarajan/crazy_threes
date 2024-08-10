package interface_adapter.load_game;

import org.junit.jupiter.api.Test;
import use_case.deck_actions.ShuffleInputBoundary;
import use_case.game_actions.NewGameInterface;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.load_game.LoadGameInputData;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.start_game.StartGameInputBoundary;
import use_case.game_actions.start_game.StartGameInputData;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import use_case.player_actions.play_card.PlayCardInputBoundary;

import static org.junit.jupiter.api.Assertions.*;

class LoadGameControllerTest {
    LoadGameController controller = new LoadGameController(new MockInteractor());

    @Test
    void execute() {
        controller.execute("Game 1");
    }

    class MockInteractor implements NewGameInterface {

        @Override
        public void startGame(StartGameInputData inputData) {

        }

        @Override
        public void loadGame(LoadGameInputData inputData) {
            assertEquals(inputData.getGameName(), "Game 1");

        }

        @Override
        public SaveGameInputBoundary getSaveGame() {
            return null;
        }

        @Override
        public PlayCardInputBoundary getPlayCard() {
            return null;
        }

        @Override
        public ShuffleInputBoundary getShuffle() {
            return null;
        }

        @Override
        public LoadGameInputBoundary getLoadGame() {
            return null;
        }

        @Override
        public StartGameInputBoundary getStartGame() {
            return null;
        }

        @Override
        public DrawCardInputBoundary getDrawCard() {
            return null;
        }
    }
}