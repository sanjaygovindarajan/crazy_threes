package view;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.*;
import interface_adapter.draw_card.DrawCardController;
import interface_adapter.load_game.*;
import interface_adapter.play_card.*;
import interface_adapter.save_game.*;
import interface_adapter.shuffle.ShuffleController;
import interface_adapter.shuffle.ShuffleOutputBoundary;
import interface_adapter.shuffle.ShufflePresenter;
import interface_adapter.start_game.StartGameController;
import interface_adapter.start_game.StartGameOutputBoundary;
import interface_adapter.view_rules.*;
import use_case.game_actions.NewGameInteractor;
import use_case.game_actions.load_game.LoadGameOutputBoundary;
import use_case.game_actions.read_rules.ReadRulesInputBoundary;
import use_case.game_actions.read_rules.ReadRulesInteractor;

import javax.swing.*;
import java.io.IOException;

/**
 * Creates the views and constructors.
 */
public class NewGameUseCaseFactory {
    /**
     * Creates a new ShuffleView
     * @param viewManagerModel The ViewManagerModel
     * @param interactor The interactor for creating a new game
     * @return A ShuffleView
     */
    public static ShuffleView createShuffleView(ViewManagerModel viewManagerModel, NewGameInteractor interactor){
        ShuffleController shuffleController = new ShuffleController(interactor.getShuffle());
        return new ShuffleView(shuffleController);
    }
    /**
     * Creates a new TurnView
     * @param viewManagerModel The ViewManagerModel
     * @param turnViewModel The TurnViewModel
     * @param interactor The NewGameInteractor
     * @return A TurnView
     */
    public static TurnView createTurnView(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel, NewGameInteractor interactor){
        SaveGameController saveGameController = new SaveGameController(interactor.getSaveGame());
        DrawCardController drawCardController = new DrawCardController(interactor.getDrawCard());
        PlayCardController playCardController = new PlayCardController(interactor.getPlayCard());
        ReadRulesController readRulesController = new ReadRulesController(new ReadRulesInteractor(new ReadRulesPresenter()));
        return new TurnView(saveGameController, playCardController, drawCardController, readRulesController, turnViewModel);
    }

    /**
     * Creates a new LoadGameView
     * @param viewManagerModel The ViewManagerModel
     * @param turnViewModel The TurnViewModel
     * @param loadGameViewModel The LoadGameViewModel
     * @return A LoadGameView
     */
    public static LoadGameView createLoadGameView(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel, LoadGameViewModel loadGameViewModel) {
        try {
            LoadGameController loadGameController = createUserLoadGameUseCase(viewManagerModel, turnViewModel);
            return new LoadGameView(loadGameController, loadGameViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates a new NewGameView
     * @param viewManagerModel The view manager model
     * @return A new NewGameView
     */
    public static NewGameView createNewGame(ViewManagerModel viewManagerModel){
        return new NewGameView(viewManagerModel, createUserReadRulesUseCase());
    }

    public static InputPlayersView createInputPlayers(NewGameInteractor interactor) {
        return new InputPlayersView(createUserStartGameUseCase(interactor));
    }

    /**
     * Creates a new controller for the read rules use case
     * @return A ReadRulesController
     */
    private static ReadRulesController createUserReadRulesUseCase(){
        ReadRulesOutputBoundary readRulesOutputBoundary = new ReadRulesPresenter();
        ReadRulesInputBoundary readRules = new ReadRulesInteractor(readRulesOutputBoundary);
        return new ReadRulesController(readRules);
    }

    /**
     * Creates a new controller for the start game use case
     * @param interactor The new game interactor
     * @return A StartGameController
     */
    private static StartGameController createUserStartGameUseCase(NewGameInteractor interactor){
        return new StartGameController(interactor);
    }

    /**
     * Creates a new controller for the load game use case
     * @param viewManagerModel The view manager model
     * @param turnViewModel The turn view model
     * @return A LoadGameController
     * @throws IOException Faulty data access
     */
    private static LoadGameController createUserLoadGameUseCase(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) throws IOException {
        NewGameInteractor newGame = createNewGameInteractor(viewManagerModel, turnViewModel);
        return new LoadGameController(newGame);
    }

    /**
     * Creates a new NewGameInteractor
     * @param viewManagerModel The view manager model
     * @param turnViewModel The turn view model
     * @return A NewGameInteractor
     */
    private static NewGameInteractor createNewGameInteractor(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) {
        DataAccessInterface userDataAccessObject = new DataAccess() {
        };
        LoadGameOutputBoundary loadGameOutputBoundary = new LoadGamePresenter(viewManagerModel, turnViewModel);
        SaveGameOutputBoundary saveGameOutputBoundary = new SaveGamePresenter();
        PlayCardOutputBoundary playCardOutputBoundary = new PlayCardPresenter(viewManagerModel, turnViewModel);
        StartGameOutputBoundary startGameOutputBoundary = new StartGamePresenter(viewManagerModel, turnViewModel);
        DrawCardOutputBoundary drawCardOutputBoundary = new DrawCardPresenter(viewManagerModel, turnViewModel);
        ShuffleOutputBoundary shuffleOutputBoundary = new ShufflePresenter(viewManagerModel);
        return new NewGameInteractor(
                userDataAccessObject,
                loadGameOutputBoundary,
                saveGameOutputBoundary,
                playCardOutputBoundary,
                startGameOutputBoundary,
                drawCardOutputBoundary,
                shuffleOutputBoundary
        );
    }
}
