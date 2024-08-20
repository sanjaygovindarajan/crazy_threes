package use_case;
import entity.Game;
import org.junit.jupiter.api.Test;
import entity.Player;
import interface_adapter.start_game.StartGameOutputBoundary;
import interface_adapter.start_game.StartGameOutputData;
import use_case.game_actions.start_game.StartGameInputData;
import use_case.game_actions.start_game.StartGameInteractor;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Objects;

public class StartGameInteractorTest {
    String[] players = {"test1"};
    StartGameInputData inputData = new StartGameInputData(players);
    StartGameOutputBoundary presenter = new PresenterMock();
    StartGameInteractor interactor = new StartGameInteractor(presenter);


    @Test
    public void testExecute() {
        Player player1 = new Player("test1");
        interactor.execute(inputData);
        assert interactor.getGame().getPlayers().getFirst().getName().equals(player1.getName());
    }

    @Test
    public void testPresent() {
        Game game = new Game(List.of(players));
        interactor.setGame(game);
        interactor.present();
        assert interactor.getGame() == game;
    }

    class PresenterMock implements StartGameOutputBoundary {


        @Override
        public void loadSuccessView(StartGameOutputData outputData) {
            assertNotNull(outputData);
            assert(outputData.getPlayerName().equals(players[0]));
        }
    }
}
