package use_case.game_actions.load_game;

import data_access.DataAccess;
import data_access.DataAccessInterface;

import entity.Game;
import interface_adapter.LoadGamePresenter;
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

    private SaveGameOutputBoundary output;
    private File file = new File("src/data_access/database.txt");
    private DataAccessInterface dataAccess = new DataAccess(file);
    @Test
    public void successTest() throws Exception {

        Files.writeString(Path.of(file.getPath()), "");
        output = new SaveGamePresenter();
        interactor = new SaveGameInteractor(dataAccess, output);
        interactor = new SaveGameInteractor(dataAccess, output);
        List<String> players = new ArrayList<>(3);
        players.add("player1");
        players.add("player2");
        players.add("player3");
        Game game = new Game(players);

        SaveGameInputData inputData = new SaveGameInputData("game1");

        interactor.setGame(game);
        interactor.execute(inputData);

        LoadGameOutputBoundary successPresenter = new LoadGameOutputBoundary(){
            public void prepareSuccessView(LoadGameOutputData loadGameOutputData) {
                System.out.println("You load the game successfully!");
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
                System.out.println("You load the game successfully!");
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
