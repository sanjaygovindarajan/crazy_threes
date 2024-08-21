package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;
    @BeforeEach
    void setUp() {
        game = new Game(List.of(new String[]{"Player 1"}));
    }

    /**
     * Tests the setDeck method of Game.
     * Checks that the deck was set.
     */
    @Test
    void setDeck() {
        Deck deck = new Deck();
        game.setDeck(deck);
        assertEquals(game.getDeck(), deck);
    }

    /**
     * Tests the setDiscard method of Game.
     * Checks that the DeckDisposed was set.
     */
    @Test
    void setDiscard() {
        DeckDisposed discard = new DeckDisposed();
        game.setDiscard(discard);
        assertEquals(game.getDiscard(), discard);
    }

    /**
     * Tests the hasPlayableCard method of Game.
     * Checks that it is true that a card is playable when one is set.
     */
    @Test
    void hasPlayableCard() {
        game.getDiscard().addCard(new Card(2, 'S'));
        game.getCurrentPlayer().viewHand().addCard(new Card(4, 'S'));
        assertTrue(game.hasPlayableCard());
    }

    /**
     * Tests the getPlayers method of Game.
     * Checks that correct player was returned first.
     */
    @Test
    void getPlayers() {
        assertEquals(game.getPlayers().getFirst().getName(), "Player 1");
    }

    /**
     * Tests the getTurn method of Game.
     * Checks that the first turn is 0.
     */
    @Test
    void getTurn() {
        assertEquals(game.getTurn(), 0);
    }
}