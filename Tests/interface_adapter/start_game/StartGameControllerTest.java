package interface_adapter.start_game;

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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StartGameControllerTest {
    StartGameController controller = new StartGameController(new MockInteractor());

    @Test
    void execute() {
        controller.execute(List.of(new String[]{"Player 1"}));
    }

    class MockInteractor implements NewGameInterface{

        @Override
        public void startGame(StartGameInputData inputData) {
            assertEquals(inputData.getPlayers().getFirst(), "Player 1");
        }

        @Override
        public void loadGame(LoadGameInputData inputData) {

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