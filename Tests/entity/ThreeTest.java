package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreeTest {

    @Test
    void setNewSuit() {
        Three three = new Three('s');
        assertEquals('s', three.getCurrentSuit());

        three.setNewSuit('h'); // Changing suit to Hearts
        assertEquals('h', three.getCurrentSuit());
    }

    @Test
    void getCurrentSuit() {
        Three three = new Three('c');
        assertEquals('c', three.getCurrentSuit());

        three.setNewSuit('d');
        assertEquals('d', three.getCurrentSuit());
    }
}