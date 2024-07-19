package use_case.game_actions.load_game;

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
    public void execute(LoadGameInputData loadGameInputData) throws Exception {

        Game game = null;
        try {
            if (userDataAccessObject != null) {
                game = userDataAccessObject.loadGameByName(loadGameInputData.getGameName());
            } else {
                throw new IllegalStateException("Data access object is not initialized.");
            }

            if (game == null) {
                userPresenter.prepareFailView("This game doesn't exist.");
            } else {
                LoadGameOutputData loadGameOutputData = new LoadGameOutputData(game, loadGameInputData.getGameName(), false);
                userPresenter.prepareSuccessView(loadGameOutputData);
            }
        } catch (Exception e) {
            if (e.getMessage() == null) {
                userPresenter.prepareFailView("This game doesn't exist.");
            }
            else {
                userPresenter.prepareFailView(e.getMessage());}
            }

        }
    }



