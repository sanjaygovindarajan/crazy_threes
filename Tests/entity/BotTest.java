package entity;

import entity.exceptions.MissingCardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BotTest {
    Player testPlayer1;
    Bot testBot2;
    Hand hand1;
    Hand hand2;
    ArrayList<Card> cards2;

    @BeforeEach
    public void setUp() {
        hand1 = new Hand();
        cards2 = new ArrayList<>();
        Card sevenHearts = new Card(7, 'H');
        cards2.add(sevenHearts);
        hand2 = new Hand(cards2);
        testPlayer1 = new Player("Tester1", hand1);
        testBot2 = new Bot("Tester2", hand2);
    }

    @Test
    void isBot() {
        boolean f = false;
        boolean t = true;
        assertEquals(f, testPlayer1.isBot());
        assertEquals(t, testBot2.isBot());
    }

    @Test
    void playCard() {
        DeckDisposed testDeck = new DeckDisposed();
        Game testGame = new Game(new Deck(), new ArrayList<>(), 9, testDeck);
        testDeck.addCard(new Card(7, 'H'));
        testBot2.playCard(testGame, 0);
    }
}
