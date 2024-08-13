package interface_adapter.load_game;

import use_case.game_actions.NewGameFacade;
import use_case.game_actions.NewGameInterface;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.load_game.LoadGameInputData;

public class LoadGameController {
    LoadGameInputBoundary loadGameInteractor;
    NewGameInterface newGame;

    public LoadGameController(NewGameInterface newGame) {
        this.newGame = newGame;
        this.loadGameInteractor = newGame.getLoadGame();
    }

    public void execute(String gameName){
        LoadGameInputData loadGameInputData = new LoadGameInputData(
                gameName);

        newGame.loadGame(loadGameInputData);
    }

    public NewGameInterface getInteractor() {
        return this.newGame;
    }
}

