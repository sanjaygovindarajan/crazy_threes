package interface_adapter;

import use_case.game_actions.NewGameInteractor;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.load_game.LoadGameInputData;

public class LoadGameController {
    LoadGameInputBoundary loadGameInteractor;
    NewGameInteractor newGame;

    public LoadGameController(NewGameInteractor newGame, LoadGameInputBoundary loadGameInteractor) {
        this.newGame = newGame;
        this.loadGameInteractor = loadGameInteractor;
    }

    public LoadGameController(LoadGameInputBoundary loadGameInteractor) {
        this.loadGameInteractor = loadGameInteractor;
    }

    public void execute(String gameName){
        LoadGameInputData loadGameInputData = new LoadGameInputData(
                gameName);

        loadGameInteractor.execute(loadGameInputData);

    }
}

