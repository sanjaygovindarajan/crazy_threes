package use_case.game_actions.save_game;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import entity.*;
import interface_adapter.SaveGameOutputBoundary;
import interface_adapter.SaveGamePresenter;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SaveGameInteractorTest {
    private SaveGameInputBoundary interactor;
    private DataAccessInterface dataAccess;
    private SaveGameOutputBoundary output;
    private File file;

    @BeforeEach
    void setUp() throws IOException {
        file = new File("src/data_access/database.txt");
        dataAccess = new DataAccess(file);
        Files.writeString(Path.of(file.getPath()), "");
        output = new SaveGamePresenter();
        interactor = new SaveGameInteractor(dataAccess, output);
    }

    @Test
    void testSaveGame() throws IOException {
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