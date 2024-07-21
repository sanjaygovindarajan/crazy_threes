package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreeTest {

    /**
     * Verifies that the suit of the card can be changed and retrieved correctly.
     */
    @Test
    void setNewSuit() {
        Three three = new Three('s');
        assertEquals('s', three.getCurrentSuit());

        three.setNewSuit('h');
        assertEquals('h', three.getCurrentSuit());
    }

    /**
     * Verifies that the initial suit of the card is set correctly and can be retrieved.
     */
    @Test
    void getCurrentSuit() {
        Three three = new Three('c');
        assertEquals('c', three.getCurrentSuit());

        three.setNewSuit('d');
        assertEquals('d', three.getCurrentSuit());
    }
}