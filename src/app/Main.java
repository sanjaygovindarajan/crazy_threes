package app;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.LoadGameController;
import interface_adapter.SaveGameController;
import interface_adapter.StartGameController;
import use_case.game_actions.NewGameInteractor;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.start_game.*;
import use_case.player_actions.*;
import use_case.player_actions.draw_card.DrawCardInputBoundary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataAccessInterface dataAccess = new DataAccess();
        NewGameInteractor newGame = new NewGameInteractor(dataAccess);
        StartGameInputBoundary startGame = newGame.getStartGame();
        LoadGameInputBoundary loadGame = newGame.getLoadGame();
        PlayCardInputBoundary playCard = newGame.getPlayCard();
        DrawCardInputBoundary drawCard = newGame.getDrawCard();
        SaveGameInputBoundary saveGame = newGame.getSaveGame();
        SaveGameController sg = new SaveGameController(saveGame);
        LoadGameController lg = new LoadGameController(loadGame);
        PlayCardController pc = new PlayCardController();
        StartGameController ng = new StartGameController(startGame);

        System.out.println("Type the name of a saved game to load a game");
        System.out.println("Or, type 'Start game' to start a new game");
        Scanner input = new Scanner(System.in);
        String action = input.nextLine();
        if(action.equals("Start game")) {
            System.out.println("Input player names separated by a space");
            String playerNames = input.nextLine();
                ng.execute(playerNames);
        } else {
            try {
                lg.execute(action);
            }
            catch(Exception e) {
                System.out.println("Game not found");
            }
        }
    }
}
