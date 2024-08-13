package interface_adapter.shuffle;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShufflePresenterTest {
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    ShuffleOutputBoundary shufflePresenter = new ShufflePresenter(viewManagerModel);

    @Test
    void loadSuccessful() {
        shufflePresenter.loadSuccessful();
        assertEquals(viewManagerModel.getActiveView(), "Turn View");
    }
}