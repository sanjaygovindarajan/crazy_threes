package use_case.game_actions.load_game;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import entity.Game;
import interface_adapter.SaveGameOutputBoundary;
import interface_adapter.SaveGamePresenter;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInputData;
import use_case.game_actions.save_game.SaveGameInteractor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LoadGameInteractorTest {
    private SaveGameInputBoundary interactor;
    private DataAccessInterface dataAccess;
    private SaveGameOutputBoundary output;
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
