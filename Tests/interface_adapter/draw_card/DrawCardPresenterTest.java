package interface_adapter.draw_card;

import interface_adapter.TurnViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrawCardPresenterTest {
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    TurnViewModel turnViewModel = new TurnViewModel();
    DrawCardOutputBoundary presenter = new DrawCardPresenter(viewManagerModel, turnViewModel);

    @Test
    void loadShuffleView() {
        presenter.loadShuffleView();
        assertEquals(viewManagerModel.getActiveView(), "Shuffle View");
    }
}