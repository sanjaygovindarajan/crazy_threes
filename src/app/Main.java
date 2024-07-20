package app;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.*;
import use_case.game_actions.NewGameInteractor;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.start_game.*;
import use_case.player_actions.*;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import view.TemporaryDefaultView;
import view.TemporaryTurnView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataAccessInterface dataAccess = new DataAccess();
        TemporaryTurnView view = new TemporaryTurnView();
        NewGameInteractor newGame = new NewGameInteractor(dataAccess, view);
        StartGameInputBoundary startGame = newGame.getStartGame();
        LoadGameInputBoundary loadGame = newGame.getLoadGame();
        PlayCardInputBoundary playCard = newGame.getPlayCard();
        DrawCardInputBoundary drawCard = newGame.getDrawCard();
        SaveGameInputBoundary saveGame = newGame.getSaveGame();
        SaveGameController sg = new SaveGameController(saveGame);
        LoadGameController lg = new LoadGameController(newGame);
        PlayCardController pc = new PlayCardController(playCard);
        DrawCardController dc = new DrawCardController(drawCard);
        StartGameController ng = new StartGameController(newGame);
        ViewRulesController vg = new ViewRulesController();
        view.setControllers(pc, sg, dc);

        TemporaryDefaultView defaultView = new TemporaryDefaultView(ng, lg, vr);
        defaultView.requestAction();
    }
}
