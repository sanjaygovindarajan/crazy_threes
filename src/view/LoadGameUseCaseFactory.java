package view;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.LoadGameController;
import interface_adapter.LoadGamePresenter;
import interface_adapter.LoadGameViewModel;
import interface_adapter.ViewManagerModel;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.load_game.LoadGameInteractor;
import interface_adapter.LoadGameOutputBoundary;

import javax.swing.*;
import java.io.IOException;

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
        DataAccessInterface userDataAccessObject = new DataAccess() {
        };
        LoadGameOutputBoundary loadGameOutputBoundary = new LoadGamePresenter(viewManagerModel, loadGameViewModel);
        LoadGameInputBoundary loadGameInteractor = new LoadGameInteractor(
                userDataAccessObject, loadGameOutputBoundary);
        return new LoadGameController(loadGameInteractor);
    }
}
