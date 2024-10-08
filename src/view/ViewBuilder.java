package view;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.*;
import interface_adapter.draw_card.*;
import interface_adapter.load_game.*;
import interface_adapter.play_card.*;
import interface_adapter.save_game.*;
import interface_adapter.shuffle.*;
import interface_adapter.start_game.*;
import interface_adapter.view_rules.*;
import use_case.game_actions.NewGameFacade;
import interface_adapter.load_game.LoadGameOutputBoundary;
import use_case.game_actions.NewGameInterface;
import use_case.game_actions.load_game.ViewGamesInputBoundary;
import use_case.game_actions.load_game.ViewGamesInteractor;
import use_case.game_actions.read_rules.ReadRulesInputBoundary;
import use_case.game_actions.read_rules.ReadRulesInteractor;

import javax.swing.*;
import java.io.IOException;

/**
 * Creates the views and constructors.
 */
public class ViewBuilder {
    /**
     * Creates a new ShuffleView
     * @param interactor The interactor for creating a new game
     * @return A ShuffleView
     */
    public static ShuffleView createShuffleView(NewGameInterface interactor){
        ShuffleController shuffleController = new ShuffleController(interactor.getShuffle());
        return new ShuffleView(shuffleController);
    }
    /**
     * Creates a new TurnView
     * @param turnViewModel The TurnViewModel
     * @param interactor The NewGameFacade
     * @return A TurnView
     */
    public static TurnView createTurnView(TurnViewModel turnViewModel, NewGameInterface interactor){
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
     * @return A LoadGameView
     */
    public static LoadGameView createLoadGameView(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel, WinViewModel winViewModel, PlayThreeViewModel playThreeViewModel, LoadGameViewModel loadGameViewModel) {
        try {
            LoadGameController loadGameController = createUserLoadGameUseCase(viewManagerModel, turnViewModel, winViewModel, playThreeViewModel);
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
    public static NewGameView createNewGame(ViewManagerModel viewManagerModel, LoadGameViewModel loadGameViewModel){
        return new NewGameView(viewManagerModel, createUserReadRulesUseCase(), createUserViewGamesUseCase(new DataAccess(),
                loadGameViewModel,
                viewManagerModel));
    }


    /** Creates a new InputPlayersView
     * @param interactor The NewGameFacade
     * @return A new InputPlayersView
     */
    public static InputPlayersView createInputPlayers(NewGameInterface interactor) {
        return new InputPlayersView(createUserStartGameUseCase(interactor));
    }

    /**
     * Creates a new WinView
     * @param viewManagerModel The view manager model
     * @param winViewModel the winView model
     * @return A new WinView
     */
    public static WinView createWinView(ViewManagerModel viewManagerModel, WinViewModel winViewModel){
        return new WinView(winViewModel, viewManagerModel);
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

    private static ViewGamesController createUserViewGamesUseCase(DataAccessInterface dataAccess, LoadGameViewModel loadGameViewModel, ViewManagerModel viewManagerModel) {
        ViewGamesOutputBoundary viewGamesOutputBoundary = new ViewGamesPresenter(loadGameViewModel, viewManagerModel);
        ViewGamesInputBoundary viewGames = new ViewGamesInteractor(dataAccess, viewGamesOutputBoundary);
        return new ViewGamesController(viewGames);
    }

    /**
     * Creates a new controller for the start game use case
     * @param interactor The new game interactor
     * @return A StartGameController
     */
    private static StartGameController createUserStartGameUseCase(NewGameInterface interactor){
        return new StartGameController(interactor);
    }

    /**
     * Creates a new controller for the load game use case
     * @param viewManagerModel The view manager model
     * @param turnViewModel The turn view model
     * @return A LoadGameController
     * @throws IOException Faulty data access
     */
    private static LoadGameController createUserLoadGameUseCase(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel, WinViewModel winViewModel, PlayThreeViewModel playThreeViewModel) throws IOException {
        NewGameInterface newGame = createNewGameFacade(viewManagerModel, turnViewModel, winViewModel, playThreeViewModel);
        return new LoadGameController(newGame);
    }

    /**
     * Creates a new NewGameFacade from view models.
     * @param viewManagerModel The view manager model
     * @param turnViewModel The turn view model
     * @param winViewModel The win view model
     * @return A NewGameFacade
     */
    private static NewGameInterface createNewGameFacade(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel, WinViewModel winViewModel, PlayThreeViewModel playThreeViewModel) {
        DataAccessInterface userDataAccessObject = new DataAccess() {
        };
        LoadGameOutputBoundary loadGameOutputBoundary = new LoadGamePresenter(viewManagerModel, turnViewModel);
        SaveGameOutputBoundary saveGameOutputBoundary = new SaveGamePresenter();
        PlayCardOutputBoundary playCardOutputBoundary = new PlayCardPresenter(viewManagerModel, turnViewModel, winViewModel, playThreeViewModel);
        StartGameOutputBoundary startGameOutputBoundary = new StartGamePresenter(viewManagerModel, turnViewModel);
        DrawCardOutputBoundary drawCardOutputBoundary = new DrawCardPresenter(viewManagerModel, turnViewModel);
        ShuffleOutputBoundary shuffleOutputBoundary = new ShufflePresenter(viewManagerModel);
        return new NewGameFacade(
                userDataAccessObject,
                loadGameOutputBoundary,
                saveGameOutputBoundary,
                playCardOutputBoundary,
                startGameOutputBoundary,
                drawCardOutputBoundary,
                shuffleOutputBoundary
        );
    }

    public static PlayThreeView createThreeView(ViewManagerModel viewManagerModel, NewGameInterface interactor, PlayThreeViewModel playThreeViewModel) {
        PlayCardController playCardController = new PlayCardController(interactor.getPlayCard());
        return new PlayThreeView(playThreeViewModel, viewManagerModel, playCardController);
    }
}
