package interface_adapter;

import use_case.game_actions.load_game.LoadGameOutputBoundary;
import use_case.game_actions.load_game.LoadGameOutputData;

public class LoadGamePresenter implements LoadGameOutputBoundary {
    public void prepareSuccessView(LoadGameOutputData loadGameOutputData) {
        System.out.println("You load the game " + loadGameOutputData.getGameName() + " successfully!");
    }


    public void prepareFailView(String error) throws Exception {
        System.out.println(error);

    }
}
