package interface_adapter.load_game;

import use_case.game_actions.NewGameFacade;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.load_game.LoadGameInputData;

public class LoadGameController {
    LoadGameInputBoundary loadGameInteractor;
    NewGameFacade newGame;

    public LoadGameController(NewGameFacade newGame) {
        this.newGame = newGame;
        this.loadGameInteractor = newGame.getLoadGame();
    }

    public LoadGameController(LoadGameInputBoundary loadGameInteractor) {
    }

    public void execute(String gameName){
        LoadGameInputData loadGameInputData = new LoadGameInputData(
                gameName);

        newGame.loadGame(loadGameInputData);
    }

    public NewGameFacade getInteractor() {
        return this.newGame;
    }
}

