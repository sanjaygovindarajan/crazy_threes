package interface_adapter.play_card;

import entity.GameInterface;
import interface_adapter.start_game.StartGameOutputBoundary;
import org.junit.jupiter.api.Test;
import use_case.player_actions.play_card.PlayCardInputBoundary;
import use_case.player_actions.play_card.PlayCardInteractor;

import static org.junit.jupiter.api.Assertions.*;

class PlayCardControllerTest {
    PlayCardController controller = new PlayCardController(new MockInteractor());

    @Test
    void playCard() {
        controller.playCard(2);
    }

    @Test
    void playThree() {
        controller.playThree('S', "Hearts");
    }

    class MockInteractor implements PlayCardInputBoundary{

        @Override
        public void playCard(PlayCardInputData inputData) {
            assertEquals(inputData.getIndex(), 2);
        }

        @Override
        public void setGame(GameInterface game) {

        }

        @Override
        public void playThree(PlayThreeInputData inputData) {
            assertEquals(inputData.getSuit(), 'S');
            assertEquals(inputData.getNewSuit(), 'H');

        }

        @Override
        public StartGameOutputBoundary getPresenter() {
            return null;
        }
    }
}