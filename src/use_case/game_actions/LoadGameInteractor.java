package use_case.game_actions;

import data_access.DataAccessInterface;
import entity.Game;


public class LoadGameInteractor implements LoadGameInputBoundary {

    final DataAccessInterface userDataAccessObject;
    final LoadGameOutputBoundary userPresenter;

    public LoadGameInteractor(DataAccessInterface userDataAccessObject,
                              LoadGameOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(LoadGameInputData loadGameInputData) {
        Game game = null;
        try {
            game = userDataAccessObject.loadGameByName(loadGameInputData.getGameName());
        } catch (Exception e) {
            userPresenter.prepareFailView("This old game doesn't exist.");

        }
        LoadGameOutputData loadGameOutputData = new LoadGameOutputData(game, false);
        userPresenter.prepareSuccessView(loadGameOutputData);
    }
}


