package entity;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    CardInterface testCard;

    @BeforeEach
    void setUp() {
        testCard = new Card(5, 'h');
    }
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