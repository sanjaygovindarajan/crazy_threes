package interface_adapter.save_game;

import entity.GameInterface;
import org.junit.jupiter.api.Test;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInputData;

import static org.junit.jupiter.api.Assertions.*;

class SaveGameControllerTest {
    SaveGameController controller = new SaveGameController(new MockInteractor());

    @Test
    void execute() {
        controller.execute("Game 1");
    }

    class MockInteractor implements SaveGameInputBoundary{

        @Override
        public void execute(SaveGameInputData inputData) {
            assertEquals(inputData.getGameName(), "Game 1");
        }

        @Override
        public void setGame(GameInterface game) {

        }
    }
}