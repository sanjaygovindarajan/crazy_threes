package interface_adapter;

import use_case.game_actions.load_game.LoadGameOutputBoundary;
import use_case.game_actions.load_game.LoadGameOutputData;

public class LoadGamePresenter implements LoadGameOutputBoundary {
    private final LoadGameViewModel loadGameViewModel;
    private ViewManagerModel viewManagerModel;

    public LoadGamePresenter(ViewManagerModel viewManagerModel,
                           LoadGameViewModel signupViewModel
                           ) {
        this.viewManagerModel = viewManagerModel;
        this.loadGameViewModel = signupViewModel;
    }

    public void prepareSuccessView(LoadGameOutputData loadGameOutputData) {
        System.out.println("You load the game " + loadGameOutputData.getGameName() + " successfully!");
    }


    public void prepareFailView(String error) throws Exception {
        System.out.println(error);

    }
}
