/* package use_case.player_actions.draw_card;

import entity.*;
import org.junit.jupiter.api.Test;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import use_case.player_actions.draw_card.DrawCardInteractor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the DrawCardInteractor class.
 * This test class verifies the correct functionality of the handleDrawCard method,
 * ensuring that cards are drawn and played as expected.
 */
/* public class DrawCardInteractorTest {

    /**
     * Tests the handleDrawCard method of the DrawCardInteractor.
     * This test check that if the player does not have a playable card, a card is drawn from
     * the deck. It also verifies that a playable card is eventually present and that a card
     * is played. It checks if the interactor correctly interacts with the game state.
     *
     * @throws MissingCardException if there are no cards left in the deck to draw
     */
/*     @Test
    public void testHandleDrawCard() throws MissingCardException {
        // Setup
        List<Card> initialDeck = new ArrayList<>();
        for (char suit : new char[]{'S', 'C', 'H', 'D'}) {
            for (int num : new int[]{2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}) {
                initialDeck.add(new Card(num, suit));
            }
        }
        Deck deck = new Deck(initialDeck);
        List<String> playerNames = new ArrayList<>();
        playerNames.add(new Player("TestName").getName());
        Game game = new Game(playerNames);

//        // Setup the interactor with the necessary dependencies
//        DrawCardInteractor interactor = new DrawCardInteractor(game, new DrawCardOutputBoundary() {
//            @Override
//            public void presentDrawCard(DrawCardOutputData outputData) {
//                // Mock implementation for testing
//            }
//        });
        // idk if the above 6 lines are better than the 1 below, tbd, learning if the above is more efficient/nec

        DrawCardInputBoundary interactor = new DrawCardInteractor();

        // Test
        Player player = game.getCurrentPlayer();
        interactor.handleDrawCard();

        // Verify
        Card topCard = game.getDiscard().getCard();
        boolean hasPlayableCard = false;
        for (Card card : player.viewHand().getCardList()) {
            if (game.isValidPlay(card)) {
                hasPlayableCard = true;
                break;
            }
        }
        if (!hasPlayableCard) {
            throw new AssertionError("Player should have drawn a playable card");
        }

        // Ensure a card was played
        if (game.getDiscard().getCard() == topCard) {
            throw new AssertionError("A card should have been played");
        }
    }
}
*/