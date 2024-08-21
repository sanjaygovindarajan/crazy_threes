package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DeckDisposedTest {
    DeckDisposed discard;

    /**
     * Tests the DeckDisposed initializer of DeckDisposed.
     * Initializes a disposal deck with 1 card.
     */
    @Test
    void addCard() {
        LinkedList<Card> cards = new LinkedList<>();
        Card card = new Card(5, 'H');
        cards.add(card);
        DeckDisposed deck = new DeckDisposed(cards);
        assertEquals(deck.cardList.size(), 1);
    }

    /**
     * Tests the addCard method of CardCollection.
     * Checks that cards were properly added to disposal deck.
     */
    @BeforeEach
    void setUp() {
        this.discard = new DeckDisposed();
        discard.addCard(new Card(13, 'H'));
        discard.addCard(new Card(5, 'S'));
    }

    /**
     * Tests the getSuit method of DeckDisposed.
     * Checks suit of the top card of the disposal deck.
     */
    @Test
    void getSuit() {
        assertEquals('S', discard.getSuit());
    }

    /**
     * Tests the getCard method of DeckDisposed.
     * Checks Card of the top of the disposal deck.
     */
    @Test
    void getCard() {
        assertEquals('S',discard.getCard().getDisplaySuit());
    }

    /**
     * Tests the getNum method of DeckDisposed.
     * Checks number of the top card of the disposal deck.
     */
    @Test
    void getNum() {
        assertEquals(5,discard.getNum());
    }

    /**
     * Tests the toString method of CardCollection.
     * Checks string output of the disposal deck.
     */
    @Test
    void getToStringCard() {
        assertEquals("H13,S5", discard.toString());
    }
}