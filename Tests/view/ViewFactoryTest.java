package view;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.*;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.game_actions.NewGameInterface;

import static org.junit.jupiter.api.Assertions.*;

class ViewFactoryTest {
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    DataAccessInterface dataAccess = new DataAccess();
    TurnViewModel turnViewModel = new TurnViewModel();
    LoadGameViewModel loadGameViewModel = new LoadGameViewModel();
    WinViewModel winViewModel = new WinViewModel();
    PlayThreeViewModel playThreeViewModel = new PlayThreeViewModel();
    NewGameInterface newGame;
    LoadGameView loadGame;

    @BeforeEach
    void setUp(){
        loadGame = ViewFactory.createLoadGameView(
                viewManagerModel,
                turnViewModel,
                winViewModel,
                playThreeViewModel,
                loadGameViewModel
        );
        newGame = loadGame.getController().getInteractor();
    }

    @Test
    void createShuffleView() {
        ShuffleView view = ViewFactory.createShuffleView(newGame);
        assertEquals(view.getController().getInteractor(), newGame.getShuffle());
    }

    @Test
    void createTurnView() {
        TurnView view = ViewFactory.createTurnView(turnViewModel, newGame);
        assertEquals(view.getSaveGameController().getInteractor(), newGame.getSaveGame());
        assertEquals(view.getPlayCardController().getInteractor(), newGame.getPlayCard());
        assertEquals(view.getDrawCardController().getInteractor(), newGame.getDrawCard());
        assertNotNull(view.getReadRulesController().getInteractor());
    }

    @Test
    void createNewGame() {
        NewGameView view = ViewFactory.createNewGame(viewManagerModel, loadGameViewModel);
        assertNotNull(view.getViewGamesController().getInteractor());
    }

    @Test
    void createInputPlayers() {
        InputPlayersView view = ViewFactory.createInputPlayers(newGame);
        assertEquals(view.getController().getInteractor(), newGame);
    }

    @Test
    void createWinView() {
        WinView view = ViewFactory.createWinView(viewManagerModel, winViewModel);
    }

    @Test
    void createThreeView() {
        PlayThreeView view = ViewFactory.createThreeView(viewManagerModel, newGame, playThreeViewModel);
        assertEquals(view.getController().getInteractor(), newGame.getPlayCard());
    }
}