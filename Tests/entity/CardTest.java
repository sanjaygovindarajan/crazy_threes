package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card testCard = new Card(5, 'H');

    @Test
    void getCurrentSuit() {
        assertEquals('H', testCard.getCurrentSuit());
    }

    @Test
    void getDisplaySuit() {
        assertEquals('H', testCard.getDisplaySuit());
    }

    @Test
    void getCardNum() {
        assertEquals(5, testCard.getCardNum());
    }
}