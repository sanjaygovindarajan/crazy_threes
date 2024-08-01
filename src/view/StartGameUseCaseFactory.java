package view;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.*;
import interface_adapter.load_game.LoadGameController;
import interface_adapter.load_game.LoadGamePresenter;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.start_game.LoadSuccessViewModel;
import interface_adapter.start_game.StartGamePresenter;
import use_case.game_actions.NewGameInteractor;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.load_game.LoadGameInteractor;


import javax.swing.*;
import java.io.IOException;

/**
 * Not used in Phase 1
 */
public class StartGameUseCaseFactory {
    /** Prevent instantiation. */
    private StartGameUseCaseFactory() {}

    public static StartGameView create(ViewManagerModel viewManagerModel, LoadSuccessViewModel loadSuccessViewModel) {
        try {
            StartGameController StartGameController = createUserStartGameUseCase(viewManagerModel, loadSuccessViewModel);
            return new StartGameView(StartGameController, loadSuccessViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static StartGameController createUserStartGameUseCase(ViewManagerModel viewManagerModel, LoadSuccessViewModel loadSuccessViewModel) throws IOException {

        DataAccessInterface dataAccess = new DataAccess();

        TemporaryTurnView view = new TemporaryTurnView();
        TemporaryShuffleView shuffleView = new TemporaryShuffleView();

        NewGameInteractor newGame = new NewGameInteractor(dataAccess, view, shuffleView, viewManagerModel, loadSuccessViewModel);



        return new StartGameController(newGame);
    }
}
