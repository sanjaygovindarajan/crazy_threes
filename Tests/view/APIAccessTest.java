package view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class APIAccessTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Tests whether APIAccess is able to successfully load an image without error
     */
    @Test
    void getCard() {
        APIAccess.getCard('H', '3');
    }
}