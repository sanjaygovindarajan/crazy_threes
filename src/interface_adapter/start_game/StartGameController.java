package interface_adapter.start_game;

import use_case.game_actions.NewGameFacade;
import use_case.game_actions.start_game.StartGameInputBoundary;
import use_case.game_actions.start_game.StartGameInputData;

import java.util.List;

public class StartGameController {
    NewGameFacade newGame;
    StartGameInputBoundary startGame;
    public StartGameController(NewGameFacade newGame){
        this.newGame = newGame;
        this.startGame = newGame.getStartGame();
    }

    public void execute(List<String> userInput){
        String[] playersArray = new String[userInput.size()];
        newGame.startGame(new StartGameInputData(userInput.toArray(playersArray)));
    }
}
