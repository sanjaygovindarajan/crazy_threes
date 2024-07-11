package use_case.game_actions;

import data_access.DataAccess;
import data_access.DataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

class LoadGameInteractorTest {

    void successTest(){
        DataAccessInterface dataAccess = new DataAccess();
        LoadGameOutputBoundary successPresenter = new LoadGameOutputBoundary() {
            @Override
            public void prepareSuccessView(LoadGameOutputData loadGameOutputData) {
                System.out.println("Success");
            }

            @Override
            public void prepareFailView(String error) {
                fail("This old game doesn't exist.");
            }
        };
        LoadGameInputData inputData = new LoadGameInputData("Game1");
        LoadGameInputBoundary interactor = new LoadGameInteractor(dataAccess, successPresenter);
        interactor.execute(inputData);
    }

}
