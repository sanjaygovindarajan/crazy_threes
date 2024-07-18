package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card testCard = new Card(5, 'h');

    @Test
    void getCurrentSuit() {
        assertEquals('h', testCard.getCurrentSuit());
    }

    @Test
    void getDisplaySuit() {
        assertEquals('h', testCard.getDisplaySuit());
    }

    @Test
    void getCardNum() {
        assertEquals(5, testCard.getCardNum());
    }
}