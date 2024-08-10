package interface_adapter.load_game;

import interface_adapter.LoadGameViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewGamesPresenterTest {
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    LoadGameViewModel loadGameViewModel = new LoadGameViewModel();
    ViewGamesOutputBoundary presenter = new ViewGamesPresenter(loadGameViewModel, viewManagerModel);

    @Test
    void loadSuccessView() {
        ViewGamesOutputData outputData = new ViewGamesOutputData(
                List.of(new String[]{"game1", "game2"})
        );
        presenter.loadSuccessView(outputData);
        assertEquals(viewManagerModel.getActiveView(), "Load Game");
        assertEquals(loadGameViewModel.getGames(), outputData.getGames());
    }
}