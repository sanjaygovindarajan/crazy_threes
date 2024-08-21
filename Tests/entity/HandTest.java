package entity;

import entity.exceptions.MissingCardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    private Hand hand;
    @BeforeEach
    void setUp() {
        hand = new Hand();
        Card card = new Card(5, 'H');
        hand.addCard(card);
        hand.addCard(new Card(3, 'C'));
        }

    /**
     * Tests the playCard method of Hand.
     * Verifies card was removed from hand.
     */
    @Test
    void playCard(){
        hand.playCard(0);
        assertEquals(hand.cardList.size(), 1);
    }

    /**
     * Tests the initializer method of Hand.
     * Verifies card was correctly added to new hand.
     */
    @Test
    void initHand(){
        LinkedList<Card> cards = new LinkedList<>();
        cards.add(new Card(5, 'H'));
        Hand hand = new Hand(cards);
        assertEquals(hand.cardList.size(), 1);
    }
}
