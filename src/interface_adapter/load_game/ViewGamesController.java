package interface_adapter.load_game;

import use_case.game_actions.load_game.ViewGamesInputBoundary;

public class ViewGamesController {
    private final ViewGamesInputBoundary interactor;

    public ViewGamesController(ViewGamesInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(){
        interactor.loadSavedGames();
    }
}
