package use_case;

import data_access.DataAccessInterface;
import entity.*;
import interface_adapter.load_game.LoadGameOutputBoundary;
import interface_adapter.start_game.StartGameOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.game_actions.load_game.LoadGameInputData;
import use_case.game_actions.load_game.LoadGameInteractor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadGameInteractorTest {

    private DataAccessInterface dataAccess;
    private LoadGameOutputBoundary presenter;
    private LoadGameInteractor loadGameInteractor;
    private LoadGameInputData inputData;
    private String failMessage;
    private StartGameOutputData outputData;

    @BeforeEach
    void setUp() {

        dataAccess = new DataAccessMock();
        presenter = new PresenterMock();
        loadGameInteractor = new LoadGameInteractor(dataAccess, presenter);
        inputData = new LoadGameInputData("game3");
        failMessage = null;
        outputData = null;
    }

    @Test
    void testExecuteGameExists() throws Exception {
        ((DataAccessMock) dataAccess).setGamesList(List.of(
                "game3&S10,D10,C14,C10,H3,C9,C8,C5,C4,D14,S7,S8,S5,H12,H7,H11,S13,D9,H6,C3,H4,H8,S2,C6,S3,C13,H2,S6,H9,H13,S14,C7,H10&C12&player1;S9,D5,C2,D11,D8,D7,D2,D6,D4/player2;S4,S12,D13,D3,H14,S11,D12,C11,H5&0&2024-08-06 21:48:02"
        ));


        loadGameInteractor.execute(inputData);

        assertNotNull(loadGameInteractor.getGame());
        assertNull(failMessage);
    }

    @Test
    void testExecuteGameDoesNotExist() throws Exception {
        ((DataAccessMock) dataAccess).setGamesList(new ArrayList<>());

        loadGameInteractor.execute(inputData);

        assertEquals("This game doesn't exist.", failMessage);
    }

    @Test
    void testExecuteDataAccessThrowsException() throws Exception {
        ((DataAccessMock) dataAccess).setThrowException(true);

        loadGameInteractor.execute(inputData);

        assertEquals("Data access error", failMessage);
    }

    @Test
    void testPresent() {

        Deck deck = new Deck(new ArrayList<>());
        DeckDisposed discard = new DeckDisposed(Arrays.asList(new Card(12, 'C')));
        Hand hand = new Hand(Arrays.asList(new Card(9, 'S'), new Card(5, 'D'), new Card(2, 'C'), new Card(11, 'D'), new Card(8, 'D'), new Card(7, 'D'), new Card(2, 'D'), new Card(6, 'D'), new Card(4, 'D')));
        Player player = new Player("player1", hand);
        GameInterface game = new Game(deck, Arrays.asList(player), 0, discard);

        ((DataAccessMock) dataAccess).setGamesList(List.of(
                "game3&S10,D10,C14,C10,H3,C9,C8,C5,C4,D14,S7,S8,S5,H12,H7,H11,S13,D9,H6,C3,H4,H8,S2,C6,S3,C13,H2,S6,H9,H13,S14,C7,H10&C12&player1;S9,D5,C2,D11,D8,D7,D2,D6,D4/player2;S4,S12,D13,D3,H14,S11,D12,C11,H5&0&2024-08-06 21:48:02"
        ));
        loadGameInteractor.execute(inputData);

        loadGameInteractor.present(inputData);

        assertNotNull(outputData);
        assertEquals("player1", outputData.getPlayerName());
        assertEquals("S9,D5,C2,D11,D8,D7,D2,D6,D4", outputData.getPlayerCards());
        assertEquals("C12", outputData.getCard());
        assertEquals('C', outputData.getCurrentSuit());
    }


    class DataAccessMock implements DataAccessInterface {
        private List<String> gamesList;
        private boolean throwException;

        public void setGamesList(List<String> gamesList) {
            this.gamesList = gamesList;
        }

        public void setThrowException(boolean throwException) {
            this.throwException = throwException;
        }

        @Override
        public List<String> loadGames() {
            if (throwException) {
                throw new RuntimeException("Data access error");
            }
            return gamesList;
        }

        @Override
        public void saveGame(String game, LocalDateTime date) throws IOException {

        }
    }

    class PresenterMock implements LoadGameOutputBoundary {

        @Override
        public void prepareFailView(String message) {
            failMessage = message;
        }

        @Override
        public void loadSuccessView(StartGameOutputData outputData) {
            LoadGameInteractorTest.this.outputData = outputData;
        }
    }
}
