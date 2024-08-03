package interface_adapter.load_game;

import interface_adapter.StartGamePresenter;
import interface_adapter.ViewManagerModel;
import use_case.game_actions.load_game.LoadGameOutputBoundary;

public class LoadGamePresenter extends StartGamePresenter implements LoadGameOutputBoundary {

    private LoadGameViewModel loadGameViewModel;
    private TemporaryTurnView view;

    /**
     * The version of the constructor for Phase 2
     * @param viewManagerModel The view manager model
     * @param signupViewModel The view model
     */
    public LoadGamePresenter(ViewManagerModel viewManagerModel,
                           LoadGameViewModel signupViewModel
                           ) {
        this.viewManagerModel = viewManagerModel;
        this.loadGameViewModel = signupViewModel;
    }

    /**
     * The version of the constructor for Phase 1
     * @param view The view used in Phase 1
     */
    public LoadGamePresenter(TemporaryTurnView view){
        this.view = view;
    }

    public void prepareFailView(String error){
        System.out.println(error);

    }
}
