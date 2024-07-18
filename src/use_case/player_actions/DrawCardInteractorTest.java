package use_case.player_actions;

import entity.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DrawCardInteractorTest {

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

        DrawCardInteractor interactor = new DrawCardInteractor();

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
