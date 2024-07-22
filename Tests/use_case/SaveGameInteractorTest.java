package use_case.player_actions.draw_card;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import entity.*;
import interface_adapter.save_game.*;
import org.junit.jupiter.api.*;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInputData;
import use_case.game_actions.save_game.SaveGameInteractor;
import view.TemporaryTurnView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SaveGameInteractorTest {
    private SaveGameInputBoundary interactor;
    private DataAccessInterface dataAccess;
    private SaveGameOutputBoundary output;
    private File file;

    @BeforeEach
    public void setUp() throws IOException {
        file = new File("src/data_access/database.txt");
        dataAccess = new DataAccess(file);
        TemporaryTurnView view = new TemporaryTurnView();
        Files.writeString(Path.of(file.getPath()), "");
        output = new SaveGamePresenter(view);
        interactor = new SaveGameInteractor(dataAccess, output);
    }

    @Test
    public void testSaveGame() throws IOException {

        List<String> players = new ArrayList<>(3);
        players.add("player1");
        players.add("player2");
        players.add("player3");
        Game game = new Game(players);
        SaveGameInputData inputData = new SaveGameInputData("game1");
        interactor.setGame(game);
        interactor.execute(inputData);
        File file = new File("src/data_access/database.txt");
        System.out.println(Files.readString(Path.of(file.getPath())));
    }

    @AfterEach
    void tearDown() throws IOException {
        setUp();
    }

}
