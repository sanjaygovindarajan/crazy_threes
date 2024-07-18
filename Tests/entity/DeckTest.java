package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck deck;
    @BeforeEach
    void setUp() {
        deck = new Deck();
        for (int i = 1; i < 10; i++) {
            deck.addCard(new Card(i, 'h'));
        }
    }

    @Test
    void dealCard() {
        Card test = deck.cardList.getFirst();
        assertEquals(test, deck.dealCard());
    }

    @Test
    void shuffle() {
        Deck unshuffled = new Deck();
        for (int i = 1; i < 10; i++) {
            unshuffled.addCard(new Card(i, 'h'));
        }
        deck.shuffle();
        assertNotEquals(unshuffled, deck);
    }
}