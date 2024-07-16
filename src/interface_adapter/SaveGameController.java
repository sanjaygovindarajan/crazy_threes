package interface_adapter;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInputData;

import java.io.IOException;

public class SaveGameController {
    final SaveGameInputBoundary saveGameInputBoundary;
    public SaveGameController(SaveGameInputBoundary saveGameInputBoundary) {
        this.saveGameInputBoundary = saveGameInputBoundary;
    }
    public void execute(String gameName) throws IOException {
        SaveGameInputData inputData = new SaveGameInputData(gameName);
        saveGameInputBoundary.execute(inputData);
    }
}
