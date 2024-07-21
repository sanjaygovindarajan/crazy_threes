package interface_adapter.save_game;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInputData;

public class SaveGameController {
    final SaveGameInputBoundary saveGameInputBoundary;
    public SaveGameController(SaveGameInputBoundary saveGameInputBoundary) {
        this.saveGameInputBoundary = saveGameInputBoundary;
    }
    public void execute(String gameName) {
        SaveGameInputData inputData = new SaveGameInputData(gameName);
        saveGameInputBoundary.execute(inputData);
    }
}
