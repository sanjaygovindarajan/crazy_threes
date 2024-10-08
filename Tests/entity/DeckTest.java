package entity;

import entity.exceptions.MissingCardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck deck;
    @BeforeEach
    void setUp() {
        deck = new Deck();
        for (int i = 1; i < 10; i++) {
            deck.addCard(new Card(i, 'H'));
        }
    }

    /**
     * Tests the dealCard method of Deck.
     * Checks that Card was dealt from deck.
     */
    @Test
    void dealCard() throws MissingCardException {
        Card test = deck.cardList.getFirst();
        assertEquals(test, deck.dealCard());
    }

    /**
     * Tests the shuffle method of Deck.
     * Checks that deck was shuffled.
     */
    @Test
    void shuffle() {
        Deck unshuffled = new Deck();
        for (int i = 1; i < 10; i++) {
            unshuffled.addCard(new Card(i, 'H'));
        }
        deck.shuffle();
        assertNotEquals(unshuffled, deck);
    }
}