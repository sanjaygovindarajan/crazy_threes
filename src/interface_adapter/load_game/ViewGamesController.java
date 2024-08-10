package interface_adapter.load_game;

import use_case.game_actions.load_game.ViewGamesInputBoundary;

public class ViewGamesController {
    private final ViewGamesInputBoundary interactor;
    /**
     * Constructs a new viewGameController with the viewGames interactor.
     *
     * @param interactor responsible for handling the view games logic
     */
    public ViewGamesController(ViewGamesInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(){
        interactor.loadSavedGames();
    }

    /**
     * Getter method for the use case interactor
     * @return THe use case interactor
     */
    public ViewGamesInputBoundary getInteractor() {
        return this.interactor;
    }
}
