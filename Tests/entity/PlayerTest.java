package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player testPlayer1;
    Player testPlayer2;
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
        testPlayer2 = new Player("Tester2", hand2);
    }

    @Test
    void viewHand() {
        assert hand1.equals(testPlayer1.viewHand());
        assert hand2.equals(testPlayer2.viewHand());
    }

    @Test
    void getName() {
        assertEquals("Tester1", testPlayer1.getName());
        assertEquals("Tester2", testPlayer2.getName());
    }

    @Test
    void hasCard() {
        Card sevenHearts = new Card(7, 'H');
        assert testPlayer2.hasCard(sevenHearts);
    }

    @Test
    void playCard() {
        DeckDisposed testDeck = new DeckDisposed();
        Game testGame = new Game(new Deck(), new ArrayList<>(), 9, testDeck);
        testDeck.addCard(new Card(7, 'H'));
        testPlayer2.playCard(testGame, 0);
    }

    @Test
    void drawCard() throws MissingCardException {
        LinkedList<Card> testCards = new LinkedList<>();
        Card sevenHearts = new Card(7, 'H');
        testCards.add(sevenHearts);
        Deck testDeck = new Deck(testCards);
        testPlayer1.drawCard(testDeck);
        testPlayer1.hasCard(new Card(7, 'H'));
    }

    @Test
    void hasWin() {
        assert testPlayer1.hasWin();
    }
}