package view;

import data_access.DataAccess;
import interface_adapter.*;
import interface_adapter.draw_card.DrawCardController;
import interface_adapter.play_card.PlayCardController;
import interface_adapter.play_card.PlayCardPresenter;
import interface_adapter.save_game.SaveGameController;
import interface_adapter.save_game.SaveGamePresenter;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInteractor;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import use_case.player_actions.draw_card.DrawCardInteractor;
import use_case.player_actions.play_card.PlayCardInputBoundary;
import use_case.player_actions.play_card.PlayCardInteractor;

public class TurnUseCaseFactory {
    public static TurnView create(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) {
        PlayCardController playCardController = createUserPlayCardUseCase(viewManagerModel, turnViewModel);
        DrawCardController drawCardController = createUserDrawCardUseCase(viewManagerModel, turnViewModel);
        SaveGameController saveGameController = createUserSaveGameUseCase(viewManagerModel, turnViewModel);
        return new TurnView(saveGameController, playCardController, drawCardController, turnViewModel);
    }

    private static PlayCardController createUserPlayCardUseCase(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) {
        PlayCardInputBoundary interactor = new PlayCardInteractor(new PlayCardPresenter(viewManagerModel, turnViewModel));
        return new PlayCardController(interactor);
    }
    private static DrawCardController createUserDrawCardUseCase(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) {
        DrawCardInputBoundary interactor = new DrawCardInteractor(new DrawCardPresenter(viewManagerModel, turnViewModel));
        return new DrawCardController(interactor);
    }
    private static SaveGameController createUserSaveGameUseCase(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) {
        SaveGameInputBoundary interactor = new SaveGameInteractor(new DataAccess(), new SaveGamePresenter());
        return new SaveGameController(interactor);
    }
}
