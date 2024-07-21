package interface_adapter;

import use_case.game_actions.NewGameInteractor;
import view.TemporaryTurnView;

public class LoadGamePresenter implements LoadGameOutputBoundary {

    private LoadGameViewModel loadGameViewModel;
    private TemporaryTurnView view;
    private ViewManagerModel viewManagerModel;


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

    @Override
    public void prepareSuccessView(LoadGameOutputData loadGameOutputData) {
        System.out.println("You load " + loadGameOutputData.getGameName() + " successfully");
        view.requestAction();

    }

    public void prepareFailView(String error){
        System.out.println(error);

    }
}
