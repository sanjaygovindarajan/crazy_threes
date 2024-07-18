package use_case.player_actions;

import entity.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DrawCardInteractorTest {

    @Test
    public void testHandleDrawCard() throws MissingCardException {
        // Setup
        List<Card> initialDeck = new ArrayList<>();
        for (char suit : new char[]{'S', 'C', 'H', 'D'}) {
            for (int num : new int[]{2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}) {
                initialDeck.add(new Card(num, suit));
            }
        }
        Deck deck = new Deck(initialDeck);
        List<Player> players = new ArrayList<>();
        players.add(new Player("Alice"));
        Game game = new Game(Player players);

        DrawCardInteractor interactor = new DrawCardInteractor();

        // Test
        Player player = game.getCurrentPlayer();
        interactor.handleDrawCard(game, player);

        // Verify
        Card topCard = game.getDiscard().getCard();
        boolean hasPlayableCard = false;
        for (Card card : player.getHand().viewCards()) {
            if (game.isValidPlay(card)) {
                hasPlayableCard = true;
                break;
            }
        }
        assertTrue(hasPlayableCard, "Player should have drawn a playable card");

        // Ensure a card was played
        assertTrue(game.getDiscard().getCard() != topCard, "A card should have been played");
    }
}
