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
import interface_adapter.save_game.SaveGamePresenter;
import interface_adapter.view_rules.ReadRulesController;
import interface_adapter.view_rules.ReadRulesPresenter;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.load_game.LoadGameInteractor;
import use_case.game_actions.load_game.LoadGameOutputBoundary;
import use_case.game_actions.read_rules.ReadRulesInputBoundary;
import use_case.game_actions.read_rules.ReadRulesInteractor;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInteractor;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import use_case.player_actions.draw_card.DrawCardInteractor;
import use_case.player_actions.play_card.PlayCardInputBoundary;
import use_case.player_actions.play_card.PlayCardInteractor;

import javax.swing.*;
import java.io.IOException;

public class TurnUseCaseFactory {
    public static TurnView create(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) {
        PlayCardController playCardController = createUserPlayCardUseCase(viewManagerModel, turnViewModel);
        DrawCardController drawCardController = createUserDrawCardUseCase(viewManagerModel, turnViewModel);
        SaveGameController saveGameController = createUserSaveGameUseCase(viewManagerModel, turnViewModel);
        return new TurnView(saveGameController, playCardController, drawCardController, turnViewModel);
    }

    private static PlayCardController createUserPlayCardUseCase(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) {
        PlayCardInputBoundary interactor = new PlayCardInteractor(new PlayCardPresenter());
        return new PlayCardController(interactor);
    }
    private static DrawCardController createUserDrawCardUseCase(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) {
        DrawCardInputBoundary interactor = new DrawCardInteractor(new DrawCardPresenter());
        return new DrawCardController(interactor);
    }
    private static SaveGameController createUserSaveGameUseCase(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) {
        SaveGameInputBoundary interactor = new SaveGameInteractor(new DataAccess(), new SaveGamePresenter());
        return new SaveGameController(interactor);
    }
}
