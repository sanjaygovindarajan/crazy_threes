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

    @Test
    void setDeck() {
        Deck deck = new Deck();
        game.setDeck(deck);
        assertEquals(game.getDeck(), deck);
    }

    @Test
    void setDiscard() {
        DeckDisposed discard = new DeckDisposed();
        game.setDiscard(discard);
        assertEquals(game.getDiscard(), discard);
    }

    @Test
    void hasPlayableCard() {
        game.getDiscard().addCard(new Card(2, 'S'));
        game.getCurrentPlayer().viewHand().addCard(new Card(4, 'S'));
        assertTrue(game.hasPlayableCard());
    }


    @Test
    void getPlayers() {
        assertEquals(game.getPlayers().getFirst().getName(), "Player 1");
    }

    @Test
    void getTurn() {
        assertEquals(game.getTurn(), 0);
    }
}