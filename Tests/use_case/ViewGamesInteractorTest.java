package use_case;

import data_access.DataAccessInterface;
import interface_adapter.load_game.ViewGamesOutputBoundary;
import interface_adapter.load_game.ViewGamesOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.game_actions.load_game.ViewGamesInteractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewGamesInteractorTest {

    private ViewGamesInteractor interactor;
    private TestDataAccess dataAccess;
    private TestPresenter presenter;

    @BeforeEach
    void setUp() {
        // Create custom implementations of the dependencies
        dataAccess = new TestDataAccess();
        presenter = new TestPresenter();

        // Create the interactor with the custom implementations
        interactor = new ViewGamesInteractor(dataAccess, presenter);
    }

    @Test
    void testLoadSavedGamesSuccessfully() {
        // Arrange: Set up the TestDataAccess to return a list of games
        dataAccess.setGames(List.of(
                "game1&S10,D10,C14&C12&player1;S9,D5,C2/player2;S4,S12,D13&D3&2024-08-07 21:48:02",
                "game2&S11,D9,H3&C7&player1;H9,S7,C8/player3;D6,S2,H2&H14&2024-08-08 15:30:00"
        ));

        // Act: Call the method to load saved games
        interactor.loadSavedGames();

        // Assert: Verify the presenter's output data
        assertTrue(presenter.isLoadSuccessCalled());
        assertEquals(List.of(
                "game1              2024-08-07 21:48:02",
                "game2              2024-08-08 15:30:00"
        ), presenter.getOutputData().getGames());
    }

    @Test
    void testLoadSavedGamesWithIOException() {
        // Arrange: Set up the TestDataAccess to throw an IOException
        dataAccess.setThrowIOException(true);

        // Act: Call the method to load saved games
        interactor.loadSavedGames();

        // Assert: Verify the presenter reported a failure
        assertTrue(presenter.isLoadFailCalled());
    }

    // Custom implementation of DataAccessInterface for testing
    class TestDataAccess implements DataAccessInterface {
        private List<String> games = new ArrayList<>();
        private boolean throwIOException = false;

        void setGames(List<String> games) {
            this.games = games;
        }

        void setThrowIOException(boolean throwIOException) {
            this.throwIOException = throwIOException;
        }

        @Override
        public List<String> loadGames() throws IOException {
            if (throwIOException) {
                throw new IOException();
            }
            return games;
        }

        @Override
        public void saveGame(String game, java.time.LocalDateTime date) throws IOException {
            // Not needed for this test
        }
    }

    // Custom implementation of ViewGamesOutputBoundary for testing
    class TestPresenter implements ViewGamesOutputBoundary {
        private boolean loadSuccessCalled = false;
        private boolean loadFailCalled = false;
        private ViewGamesOutputData outputData;

        boolean isLoadSuccessCalled() {
            return loadSuccessCalled;
        }

        boolean isLoadFailCalled() {
            return loadFailCalled;
        }

        ViewGamesOutputData getOutputData() {
            return outputData;
        }

        @Override
        public void loadSuccessView(ViewGamesOutputData outputData) {
            this.loadSuccessCalled = true;
            this.outputData = outputData;
        }

        @Override
        public void loadFailView() {
            this.loadFailCalled = true;
        }
    }
}
