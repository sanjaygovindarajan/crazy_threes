package use_case;
import entity.Game;
import interface_adapter.load_game.LoadGameOutputBoundary;
import org.junit.jupiter.api.Test;
import entity.Player;
import interface_adapter.TurnViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.start_game.StartGameOutputBoundary;
import interface_adapter.start_game.StartGameOutputData;
import use_case.game_actions.start_game.StartGameInputData;
import use_case.game_actions.start_game.StartGameInteractor;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StartGameInteractorTest {
    String[] players = {"test1"};
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    TurnViewModel viewModel = new TurnViewModel();
    StartGameInputData inputData = new StartGameInputData(players);
    StartGameOutputBoundary presenter = new PresenterMock();
    StartGameInteractor interactor = new StartGameInteractor(presenter);
    List<Player> playerList = new ArrayList<>();
    StartGameOutputData outputData = null;


    @Test
    public void testExecute() {
        Player player1 = new Player("test1");
        interactor.execute(inputData);
        assert interactor.getGame().getPlayers().get(0).getName().equals(player1.getName());
    }

    @Test
    public void testPresent() {
        Game game = new Game(List.of(players));
        interactor.setGame(game);
        interactor.present();
        assert interactor.getGame() == game;
        assertNotNull(outputData);
        assert Objects.equals(game.getDiscard().getCard().toString(), outputData.getCard());
    }

    class PresenterMock implements LoadGameOutputBoundary {


        @Override
        public void loadSuccessView(StartGameOutputData outputData) {
            StartGameInteractorTest.this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String error) {

        }
    }
}
