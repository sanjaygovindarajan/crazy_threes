package app;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.*;
import use_case.deck_actions.ShuffleInputBoundary;
import use_case.game_actions.NewGameInteractor;
import use_case.game_actions.read_rules.ReadRulesInputBoundary;
import use_case.game_actions.read_rules.ReadRulesInteractor;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import use_case.player_actions.play_card.PlayCardInputBoundary;
import view.TemporaryDefaultView;
import view.TemporaryShuffleView;
import view.TemporaryThreeView;
import view.TemporaryTurnView;

public class Main {
    public static void main(String[] args) {
        DataAccessInterface dataAccess = new DataAccess();

        TemporaryTurnView view = new TemporaryTurnView();
        TemporaryShuffleView shuffleView = new TemporaryShuffleView();

        NewGameInteractor newGame = new NewGameInteractor(dataAccess, view, shuffleView);
        PlayCardInputBoundary playCard = newGame.getPlayCard();
        DrawCardInputBoundary drawCard = newGame.getDrawCard();
        SaveGameInputBoundary saveGame = newGame.getSaveGame();
        SaveGameController sg = new SaveGameController(saveGame);
        LoadGameController lg = new LoadGameController(newGame);
        interface_adapter.PlayCardController pc = new interface_adapter.PlayCardController(playCard);
        DrawCardController dc = new DrawCardController(drawCard);

        StartGameController ng = new StartGameController(newGame);

        ShuffleInputBoundary shuffle = newGame.getShuffle();
        ShuffleController sh = new ShuffleController(shuffle);
        shuffleView.setController(sh);

        TemporaryDefaultView defaultView = new TemporaryDefaultView(ng, lg);
        ReadRulesInputBoundary viewRules = new ReadRulesInteractor(new ReadRulesPresenter(view, defaultView));
        ReadRulesController vr = new ReadRulesController(viewRules);
        defaultView.setViewRules(vr);
        view.setViewRules(vr);
        view.setControllers(pc, sg, dc);

        playCard.getPresenter().setThreeView(new TemporaryThreeView(pc));

        defaultView.requestAction();
    }
}
