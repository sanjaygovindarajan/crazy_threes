package use_case;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import entity.*;
import interface_adapter.save_game.SaveGameOutputBoundary;
import org.junit.jupiter.api.*;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInputData;
import use_case.game_actions.save_game.SaveGameInteractor;

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
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        File dir = new File("src/data_access");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file = new File("src/data_access/database.txt");
        dataAccess = new DataAccess(file);
        Files.writeString(Path.of(file.getPath()), "");
        output = new MockPresenter();
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
        String originalPath = Files.readString(Path.of(file.getPath()));
        assert originalPath.contains("game1");
        interactor.execute(inputData);
        assertEquals(originalPath, Files.readString(Path.of(file.getPath())));
    }

    @AfterEach
    void tearDown() throws IOException {
        setUp();
    }

    class MockPresenter implements SaveGameOutputBoundary {

        @Override
        public void prepareSuccessView(String message) {

        }

        @Override
        public void prepareFailureView(String error) {

        }
    }

}

