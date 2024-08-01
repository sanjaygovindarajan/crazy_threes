package view;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.*;
import interface_adapter.draw_card.DrawCardController;
import interface_adapter.load_game.LoadGameController;
import interface_adapter.load_game.LoadGamePresenter;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.play_card.PlayCardController;
import interface_adapter.save_game.SaveGameController;
import interface_adapter.shuffle.ShuffleController;
import interface_adapter.start_game.LoadSuccessViewModel;
import interface_adapter.start_game.StartGameController;
import interface_adapter.start_game.StartGamePresenter;
import interface_adapter.view_rules.ReadRulesController;
import interface_adapter.view_rules.ReadRulesPresenter;
import use_case.deck_actions.ShuffleInputBoundary;
import use_case.game_actions.NewGameInteractor;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.load_game.LoadGameInteractor;
import use_case.game_actions.load_game.LoadGameOutputBoundary;
import use_case.game_actions.read_rules.ReadRulesInputBoundary;
import use_case.game_actions.read_rules.ReadRulesInteractor;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import use_case.player_actions.play_card.PlayCardInputBoundary;

import javax.swing.*;
import java.io.IOException;

/**
 * Not used in Phase 1
 */
public class LoadGameUseCaseFactory {
    /** Prevent instantiation. */
    private LoadGameUseCaseFactory() {}

    public static LoadGameView create(ViewManagerModel viewManagerModel, LoadGameViewModel loadGameViewModel) {
        try {
            LoadGameController loadGameController = createUserLoadGameUseCase(viewManagerModel, loadGameViewModel);
            return new LoadGameView(loadGameController, loadGameViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static LoadGameController createUserLoadGameUseCase(ViewManagerModel viewManagerModel, LoadGameViewModel loadGameViewModel) throws IOException {
        DataAccessInterface dataAccess = new DataAccess() {
        };
        LoadGameOutputBoundary loadGameOutputBoundary = new LoadGamePresenter(viewManagerModel, loadGameViewModel);


        TemporaryTurnView view = new TemporaryTurnView();
        TemporaryShuffleView shuffleView = new TemporaryShuffleView();
        LoadSuccessViewModel loadSuccessViewModel = new LoadSuccessViewModel();
        NewGameInteractor newGame = new NewGameInteractor(dataAccess, view, shuffleView, viewManagerModel, loadSuccessViewModel);
        PlayCardInputBoundary playCard = newGame.getPlayCard();
        DrawCardInputBoundary drawCard = newGame.getDrawCard();
        SaveGameInputBoundary saveGame = newGame.getSaveGame();
        SaveGameController sg = new SaveGameController(saveGame);
        LoadGameController lg = new LoadGameController(newGame);
        PlayCardController pc = new PlayCardController(playCard);
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
        LoadGameInputBoundary loadGameInteractor = new LoadGameInteractor(
                dataAccess, loadGameOutputBoundary, new StartGamePresenter(view, viewManagerModel, loadSuccessViewModel));

        return new LoadGameController(loadGameInteractor, newGame);
    }
}
