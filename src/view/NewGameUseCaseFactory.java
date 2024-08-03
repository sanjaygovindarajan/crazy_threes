package view;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.*;
import interface_adapter.draw_card.DrawCardController;
import interface_adapter.load_game.*;
import interface_adapter.play_card.*;
import interface_adapter.save_game.*;
import interface_adapter.start_game.StartGameOutputBoundary;
import interface_adapter.view_rules.*;
import use_case.game_actions.NewGameInteractor;
import use_case.game_actions.load_game.LoadGameOutputBoundary;
import use_case.game_actions.read_rules.ReadRulesInputBoundary;
import use_case.game_actions.read_rules.ReadRulesInteractor;

import javax.swing.*;
import java.io.IOException;

/**
 * Not used in Phase 1
 */
public class NewGameUseCaseFactory {



    public static TurnView create(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel, LoadGameView loadGameView){
        LoadGameController loadGameController = loadGameView.getController();
        NewGameInteractor interactor = loadGameController.getInteractor();
        SaveGameController saveGameController = new SaveGameController(interactor.getSaveGame());
        DrawCardController drawCardController = new DrawCardController(interactor.getDrawCard());
        PlayCardController playCardController = new PlayCardController(interactor.getPlayCard());
        ReadRulesController readRulesController = new ReadRulesController(new ReadRulesInteractor(new ReadRulesPresenter()));
        return new TurnView(saveGameController, playCardController, drawCardController, readRulesController, turnViewModel);
    }

    public static LoadGameView create(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel, LoadGameViewModel loadGameViewModel) {
        try {
            LoadGameController loadGameController = createUserLoadGameUseCase(viewManagerModel, turnViewModel);
            return new LoadGameView(loadGameController, loadGameViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    public static GamePanel createNewGame(ViewManagerModel viewManagerModel){
        return new GamePanel(viewManagerModel, createUserReadRulesUseCase());
    }

    private static ReadRulesController createUserReadRulesUseCase(){
        ReadRulesOutputBoundary readRulesOutputBoundary = new ReadRulesPresenter();
        ReadRulesInputBoundary readRules = new ReadRulesInteractor(readRulesOutputBoundary);
        return new ReadRulesController(readRules);
    }

    private static LoadGameController createUserLoadGameUseCase(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) throws IOException {
        DataAccessInterface userDataAccessObject = new DataAccess() {
        };
        LoadGameOutputBoundary loadGameOutputBoundary = new LoadGamePresenter(viewManagerModel, turnViewModel);
        SaveGameOutputBoundary saveGameOutputBoundary = new SaveGamePresenter();
        PlayCardOutputBoundary playCardOutputBoundary = new PlayCardPresenter(viewManagerModel, turnViewModel);
        StartGameOutputBoundary startGameOutputBoundary = new StartGamePresenter(viewManagerModel, turnViewModel);
        DrawCardOutputBoundary drawCardOutputBoundary = new DrawCardPresenter(viewManagerModel, turnViewModel);
        NewGameInteractor newGame = new NewGameInteractor(
                userDataAccessObject,
                loadGameOutputBoundary,
                saveGameOutputBoundary,
                playCardOutputBoundary,
                startGameOutputBoundary,
                drawCardOutputBoundary
        );
        return new LoadGameController(newGame);
    }
}
