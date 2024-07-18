package interface_adapter;

import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.load_game.LoadGameInputData;

public class LoadGameController {
    final LoadGameInputBoundary loadGameInteractor;

    public LoadGameController(LoadGameInputBoundary loadGameInteractor) {
            this.loadGameInteractor = loadGameInteractor;
        }

    public void execute(String gameName) throws Exception {
        LoadGameInputData loadGameInputData = new LoadGameInputData(
                gameName);

        loadGameInteractor.execute(loadGameInputData);
    }
}

