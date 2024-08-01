package use_case.player_actions.draw_card;

import data_access.DataAccess;
import data_access.DataAccessInterface;

import entity.Game;
import interface_adapter.LoadGameOutputBoundary;
import interface_adapter.LoadGameOutputData;
import interface_adapter.save_game.SaveGameOutputBoundary;
import interface_adapter.save_game.SaveGamePresenter;
import interface_adapter.start_game.StartGamePresenter;
import org.junit.Test;
import use_case.game_actions.load_game.*;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInputData;
import use_case.game_actions.save_game.SaveGameInteractor;
import view.TemporaryTurnView;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadGameInteractorTest {
    private SaveGameInputBoundary interactor;

    private SaveGameOutputBoundary output;
    private File file = new File("src/data_access/database.txt");
    private DataAccessInterface dataAccess = new DataAccess(file);

    @Test
    public void successTest() throws Exception {
        TemporaryTurnView view = new TemporaryTurnView();
        Files.writeString(Path.of(file.getPath()), "");
        output = new SaveGamePresenter(view);
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
            public void prepareFailView(String error){
                System.out.println(error);

            }

        };

        LoadGameInputData inputData1 = new LoadGameInputData("game1");
        LoadGameInputBoundary interactor = new LoadGameInteractor(dataAccess, successPresenter, new StartGamePresenter());
        interactor.execute(inputData1);
        assertEquals(game.getCurrentPlayer().getName(), "player1");
    }

    @Test
    public void failTest() throws Exception {
        DataAccessInterface dataAccess = new DataAccess();
        LoadGameOutputBoundary successPresenter = new LoadGameOutputBoundary() {
            public void prepareSuccessView(LoadGameOutputData loadGameOutputData) {
                System.out.println("You load the game successfully!");
            }
            @Override
            public void prepareFailView(String error){
                System.out.println(error);

            }
        };
        LoadGameInputData inputData2 = new LoadGameInputData("game2");
        LoadGameInputBoundary interactor = new LoadGameInteractor(dataAccess, successPresenter, new StartGamePresenter());
        interactor.execute(inputData2);
    }

}
