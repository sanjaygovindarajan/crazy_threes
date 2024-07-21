package entity;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    CardInterface testCard;

    @BeforeEach
    void setUp() {
        testCard = new Card(5, 'H');
    }
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