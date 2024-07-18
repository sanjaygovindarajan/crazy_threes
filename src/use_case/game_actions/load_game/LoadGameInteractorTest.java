package use_case.game_actions.load_game;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.SaveGameOutputBoundary;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.game_actions.save_game.SaveGameInputBoundary;

import java.io.File;
import java.io.IOException;

public class LoadGameInteractorTest {
    private File file;

    @BeforeEach
    void setUp() throws IOException {
        file = new File("src/data_access/database.txt");
    }


    @Test
    public void successTest() throws Exception {
        DataAccessInterface dataAccess = new DataAccess();
        LoadGameOutputBoundary successPresenter = new LoadGameOutputBoundary() {
            @Override
            public void prepareSuccessView(LoadGameOutputData loadGameOutputData) {
                System.out.println("You load the game successfully");
            }

            @Override
            public void prepareFailView(String error) throws Exception {
                 System.out.println(error);

            }
        };
        LoadGameInputData inputData1 = new LoadGameInputData("game1");
        LoadGameInputBoundary interactor = new LoadGameInteractor(dataAccess, successPresenter);
        interactor.execute(inputData1);
    }

    @Test
    public void failTest() throws Exception {
        DataAccessInterface dataAccess = new DataAccess();
        LoadGameOutputBoundary successPresenter = new LoadGameOutputBoundary() {
            public void prepareSuccessView(LoadGameOutputData loadGameOutputData) {
                System.out.println("You load the game successfully");
            }
            @Override
            public void prepareFailView(String error) throws Exception {
                System.out.println(error);

            }
        };
        LoadGameInputData inputData2 = new LoadGameInputData("game2");
        LoadGameInputBoundary interactor = new LoadGameInteractor(dataAccess, successPresenter);
        interactor.execute(inputData2);
    }

}
