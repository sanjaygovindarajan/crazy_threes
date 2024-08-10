package interface_adapter.start_game;

import interface_adapter.TurnViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StartGamePresenterTest {
StartGameOutputBoundary presenter;
StartGameOutputData outputData;
ViewManagerModel viewManagerModel = new ViewManagerModel();
TurnViewModel turnViewModel = new TurnViewModel();
    @BeforeEach
    void setUp(){
        outputData = new StartGameOutputData(
                "t1,t2,t3",
                "test string 2",
                "t4",
                'H'
        );
        presenter = new StartGamePresenter(viewManagerModel, turnViewModel);

    }
    @Test
    void loadSuccessView() {
        presenter.loadSuccessView(outputData);
        assertEquals(viewManagerModel.getActiveView(), "Turn View");
        assertEquals(turnViewModel.getPlayerName(), "test string 2");
        assertEquals(turnViewModel.getCardSuits(), List.of(new Character[]{'t', 't', 't'}));
        assertEquals(turnViewModel.getDiscardNum(), '4');
        assertEquals(turnViewModel.getDiscardSuit(), 't');

    }
}