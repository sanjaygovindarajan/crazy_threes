package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckDisposedTest {
    DeckDisposed discard;
    @BeforeEach
    void setUp() {
        this.discard = new DeckDisposed();
        discard.addCard(new Card(13, 'H'));
        discard.addCard(new Card(5, 'S'));
    }

    @Test
    void getSuit() {
        assertEquals('S', discard.getSuit());
    }

    @Test
    void getCard() {
        assertEquals('S',discard.getCard().getDisplaySuit());
    }

    @Test
    void getNum() {
        assertEquals(5,discard.getNum());
    }
}
