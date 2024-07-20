package app;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.LoadGameController;
import interface_adapter.SaveGameController;
import interface_adapter.StartGameController;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.start_game.*;
import use_case.player_actions.*;
import use_case.player_actions.draw_card.DrawCardInputBoundary;

public class Main {
    public static void main(String[] args) {
        DataAccessInterface dataAccess = new DataAccess();
        StartGameInputBoundary startGame = new StartGameInteractor(dataAccess);
        LoadGameInputBoundary loadGame = startGame.getLoadGame();
        PlayCardInputBoundary playCard = startGame.getPlayCard();
        DrawCardInputBoundary drawCard = startGame.getDrawCard();
        SaveGameInputBoundary saveGame = startGame.getSaveGame();
        SaveGameController sg = new SaveGameController(saveGame);
        LoadGameController lg = new LoadGameController(loadGame);
        PlayCardController pc = new PlayCardController();
        StartGameController ng = new StartGameController(startGame);

    }
}
