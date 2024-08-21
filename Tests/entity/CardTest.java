package entity;

import org.junit.jupiter.api.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    CardInterface testCard;

    @BeforeEach
    void setUp() {
        testCard = new Card(5, 'H');
    }

    /**
     * Tests the getCurrentSuit method of Card.
     * Checks the current suit.
     */
    @Test
    void getCurrentSuit() {
        assertEquals('H', testCard.getCurrentSuit());
    }

    /**
     * Tests the getDisplaySuit method of Card.
     * Checks the display suit.
     */
    @Test
    void getDisplaySuit() {
        assertEquals('H', testCard.getDisplaySuit());
    }

    /**
     * Tests the getCardNum method of Card.
     * Checks the card number.
     */
    @Test
    void getCardNum() {
        assertEquals(5, testCard.getCardNum());
    }

    /**
     * Tests the getToString method of Card.
     * Checks string output of a card.
     */
    @Test
    void getToStringCard() {
        assertEquals("H5", testCard.toString());
    }

}