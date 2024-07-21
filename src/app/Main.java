package app;

import data_access.*;
import interface_adapter.*;
import use_case.deck_actions.*;
import use_case.game_actions.NewGameInteractor;
import use_case.game_actions.read_rules.*;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.player_actions.*;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import view.*;

public class Main {
    /**
     * Create clean architecture objects and load the initial view.
     */
    public static void main(String[] args) {
        DataAccessInterface dataAccess = new DataAccess();

        TemporaryTurnView view = new TemporaryTurnView();
        TemporaryShuffleView shuffleView = new TemporaryShuffleView();

        NewGameInteractor newGame = new NewGameInteractor(dataAccess, view, shuffleView);
        //Interactors
        PlayCardInputBoundary playCard = newGame.getPlayCard();
        DrawCardInputBoundary drawCard = newGame.getDrawCard();
        SaveGameInputBoundary saveGame = newGame.getSaveGame();
        ShuffleInputBoundary shuffle = new ShuffleInteractor(new ShufflePresenter(view));
        //Controllers
        SaveGameController sg = new SaveGameController(saveGame);
        LoadGameController lg = new LoadGameController(newGame);
        interface_adapter.PlayCardController pc = new interface_adapter.PlayCardController(playCard);
        DrawCardController dc = new DrawCardController(drawCard);
        StartGameController ng = new StartGameController(newGame);
        ShuffleController sh = new ShuffleController(shuffle);

        TemporaryDefaultView defaultView = new TemporaryDefaultView(ng, lg);
        //Reading the rules
        ReadRulesInputBoundary viewRules = new ReadRulesInteractor(new ReadRulesPresenter(view, defaultView));
        ReadRulesController vr = new ReadRulesController(viewRules);
        //Set view controllers
        shuffleView.setController(sh);
        defaultView.setViewRules(vr);
        view.setViewRules(vr);
        view.setControllers(pc, sg, dc);

        playCard.getPresenter().setThreeView(new TemporaryThreeView(pc));

        defaultView.requestAction();
    }
}
