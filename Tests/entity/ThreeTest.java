package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreeTest {

    /**
     * Verifies that the suit of the card can be changed and retrieved correctly.
     */
    @Test
    void setNewSuit() {
        Three three = new Three('S');
        assertEquals('S', three.getCurrentSuit());

        three.setNewSuit('H');
        assertEquals('H', three.getCurrentSuit());
    }

    /**
     * Verifies that the initial suit of the card is set correctly and can be retrieved.
     */
    @Test
    void getCurrentSuit() {
        Three three = new Three('C');
        assertEquals('C', three.getCurrentSuit());

        three.setNewSuit('D');
        assertEquals('D', three.getCurrentSuit());
    }
}