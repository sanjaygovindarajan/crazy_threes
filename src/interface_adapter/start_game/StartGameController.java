package interface_adapter.start_game;

import use_case.game_actions.NewGameInteractor;
import use_case.game_actions.start_game.StartGameInputBoundary;
import use_case.game_actions.start_game.StartGameInputData;

public class StartGameController {
    NewGameInteractor newGame;
    StartGameInputBoundary startGame;
    public StartGameController(NewGameInteractor newGame){
        this.newGame = newGame;
        this.startGame = newGame.getStartGame();
    }

    public void execute(String userInput){
        newGame.startGame(new StartGameInputData(userInput.split(" ")));
    }
}
