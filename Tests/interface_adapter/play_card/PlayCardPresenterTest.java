package interface_adapter.play_card;

import org.junit.jupiter.api.Test;
import interface_adapter.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayCardPresenterTest {
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    TurnViewModel turnViewModel = new TurnViewModel();
    WinViewModel winViewModel = new WinViewModel();
    PlayThreeViewModel playThreeViewModel = new PlayThreeViewModel();
    PlayCardOutputBoundary presenter = new PlayCardPresenter(
            viewManagerModel,
            turnViewModel,
            winViewModel,
            playThreeViewModel);

    @Test
    void loadThreeView() {
        presenter.loadThreeView('H');
        assertEquals(viewManagerModel.getActiveView(), "Three View");
        assertEquals(playThreeViewModel.getThreeSuit(), 'H');
    }

    @Test
    void winMessage() {
        presenter.winMessage("Player 1");
        assertEquals(viewManagerModel.getActiveView(), "Win View");
        assertEquals(winViewModel.getWinner(), "Player 1");
    }
}