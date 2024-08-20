package use_case;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.draw_card.DrawCardOutputBoundary;
import interface_adapter.load_game.LoadGameOutputBoundary;
import interface_adapter.play_card.PlayCardOutputBoundary;
import interface_adapter.save_game.SaveGameOutputBoundary;
import interface_adapter.shuffle.ShuffleOutputBoundary;
import interface_adapter.start_game.StartGameOutputBoundary;
import interface_adapter.start_game.StartGameOutputData;
import org.junit.jupiter.api.Test;
import use_case.game_actions.NewGameFacade;
import use_case.game_actions.NewGameInterface;
import use_case.game_actions.start_game.StartGameInputData;

import static org.junit.jupiter.api.Assertions.*;

class NewGameFacadeTest {

    DataAccessInterface dataAccess = new DataAccess();
    DrawCardOutputBoundary drawCard = new MockDrawCard();
    SaveGameOutputBoundary saveGame = new MockSaveGame();
    StartGameOutputBoundary startGame = new MockStartGame();
    LoadGameOutputBoundary loadGame = new MockLoadGame();
    PlayCardOutputBoundary playCard = new MockPlayCard();
    ShuffleOutputBoundary shuffle = new MockShuffle();

    NewGameInterface interactor = new NewGameFacade(
            dataAccess,
            loadGame,
            saveGame,
            playCard,
            startGame,
            drawCard,
            shuffle);

    @Test
    void startGame() {
        StartGameInputData inputData = new StartGameInputData(new String[]{"Player1"});
        interactor.startGame(inputData);
    }

    @Test
    void getSaveGame() {
        assertNotNull(interactor.getSaveGame());
    }

    @Test
    void getPlayCard() {
        assertNotNull(interactor.getPlayCard());
    }

    @Test
    void getLoadGame() {
        assertNotNull(interactor.getLoadGame());
    }

    @Test
    void getDrawCard() {
        assertNotNull(interactor.getDrawCard());
    }

    @Test
    void getShuffle() {
        assertNotNull(interactor.getShuffle());
    }

    @Test
    void getStartGame() {
        assertNotNull(interactor.getStartGame());
    }

    class MockDrawCard implements DrawCardOutputBoundary {
        @Override
        public void loadUnableToDrawCard() {

        }

        @Override
        public void loadShuffleView() {

        }

        @Override
        public void loadSuccessView(StartGameOutputData data) {

        }
    }

    class MockSaveGame implements SaveGameOutputBoundary {
        @Override
        public void prepareSuccessView(String message) {

        }

        @Override
        public void prepareFailureView(String error) {

        }
    }

    class MockStartGame implements StartGameOutputBoundary {
        @Override
        public void loadSuccessView(StartGameOutputData data) {
            assertEquals("Player1", data.getPlayerName());

        }
    }
    class MockLoadGame implements LoadGameOutputBoundary {
        @Override
        public void prepareFailView(String error) {

        }

        @Override
        public void loadSuccessView(StartGameOutputData data) {

        }
    }
    class MockPlayCard implements PlayCardOutputBoundary {
        @Override
        public void winMessage(String player) {

        }

        @Override
        public void loadInvalidCardView() {

        }

        @Override
        public void loadThreeView(char suit) {

        }

        @Override
        public void loadSuccessView(StartGameOutputData data) {

        }
    }
    class MockShuffle implements ShuffleOutputBoundary {
        @Override
        public void loadSuccessful() {

        }
    }
}