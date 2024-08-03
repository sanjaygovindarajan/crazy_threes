package interface_adapter.load_game;

import interface_adapter.StartGamePresenter;
import interface_adapter.TurnViewModel;
import interface_adapter.ViewManagerModel;
import use_case.game_actions.load_game.LoadGameOutputBoundary;

public class LoadGamePresenter extends StartGamePresenter implements LoadGameOutputBoundary {

    public LoadGamePresenter(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) {
        super(viewManagerModel, turnViewModel);
    }

    public void prepareFailView(String error){
        System.out.println(error);

    }
}
