package use_case.game_actions;

import data_access.DataAccess;
import data_access.DataAccessInterface;

class LoadGameInteractorTest {

    void successTest() throws Exception {
        DataAccessInterface dataAccess = new DataAccess();
        LoadGameOutputBoundary successPresenter = new LoadGameOutputBoundary() {
            @Override
            public void prepareSuccessView(LoadGameOutputData loadGameOutputData) {
                System.out.println("Success");
            }

            @Override
            public void prepareFailView(String error) throws Exception {

                throw new Exception("This old game doesn't exist.");
            }
        };
        LoadGameInputData inputData = new LoadGameInputData("Game1");
        LoadGameInputBoundary interactor = new LoadGameInteractor(dataAccess, successPresenter);
        interactor.execute(inputData);
    }

}
