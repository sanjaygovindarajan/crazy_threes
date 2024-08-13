package interface_adapter.start_game;

import use_case.game_actions.NewGameFacade;
import use_case.game_actions.NewGameInterface;
import use_case.game_actions.start_game.StartGameInputBoundary;
import use_case.game_actions.start_game.StartGameInputData;

import java.util.List;

public class StartGameController {
    NewGameInterface newGame;
    StartGameInputBoundary startGame;
    public StartGameController(NewGameInterface newGame){
        this.newGame = newGame;
        this.startGame = newGame.getStartGame();
    }

    public void execute(List<String> userInput){
        String[] playersArray = new String[userInput.size()];
        newGame.startGame(new StartGameInputData(userInput.toArray(playersArray)));
    }
    /**
     * Getter method for the use case interactor
     * @return THe use case interactor
     */
    public NewGameInterface getInteractor() {
        return this.newGame;
    }
}
