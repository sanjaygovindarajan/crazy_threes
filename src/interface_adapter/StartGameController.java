package interface_adapter;

import use_case.game_actions.start_game.StartGameInputBoundary;
import use_case.game_actions.start_game.StartGameInputData;

public class StartGameController {
    StartGameInputBoundary startGame;
    public StartGameController(StartGameInputBoundary startGame){
        this.startGame = startGame;
    }
    public void execute(String userInput){
        startGame.execute(new StartGameInputData(userInput.split("/n")));
    }
}
