package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class BotTest {
    Player testPlayer1;
    Bot testBot2;
    Hand hand1;
    Hand hand2;
    ArrayList<Card> cards2;
    Deck deck1;

    @BeforeEach
    public void setUp() {
        hand1 = new Hand();
        deck1 = new Deck();
        cards2 = new ArrayList<>();
        Card nineDiamonds = new Card(9, 'D');
        Card sevenHearts = new Card(7, 'H');
        cards2.add(sevenHearts);
        deck1.addCard(nineDiamonds);
        deck1.addCard(sevenHearts);
        hand2 = new Hand(cards2);
        testPlayer1 = new Player("Tester1", hand1);
        testBot2 = new Bot("Tester2", hand2);
    }

    /**
     * Tests the isBot method of Bot.
     * Checks whether a Player is a Bot.
     */
    @Test
    void isBot() {
        boolean f = false;
        boolean t = true;
        assertEquals(f, testPlayer1.isBot());
        assertEquals(t, testBot2.isBot());
    }

    /**
     * Tests the chooseCard method of Bot.
     * Checks whether a playable card is played.
     */
    @Test
    void chooseCardTest() {
        DeckDisposed testDeck = new DeckDisposed();
        Game testGame = new Game(new Deck(), new ArrayList<>(), 9, testDeck);
        testDeck.addCard(new Card(7, 'H'));
        testBot2.chooseCard(testDeck, testGame);
    }

    /**
     * Tests the chooseCard method of Bot.
     * Checks whether a card is drawn if there is no playable card.
     */
    @Test
    void chooseCard() {
        DeckDisposed testDeck = new DeckDisposed();
        ArrayList<Player> players = new ArrayList<>();
        players.add(testPlayer1);
        Game testGame = new Game(deck1, players, 9, testDeck);
        testDeck.addCard(new Card(9, 'D'));
        testBot2.chooseCard(testDeck, testGame);
        int yes = 2;
        assertNotSame(deck1.getCardList().size(), yes);
    }

    /**
     * Tests the chooseCard method of Bot.
     * Checks whether a three can be played.
     */
    @Test
    void chooseCardThree() {
        DeckDisposed testDeck = new DeckDisposed();
        ArrayList<Player> players = new ArrayList<>();
        players.add(testPlayer1);
        Player testPlayer2 = new Player("Tester2", hand2);
        players.add(testPlayer2);
        Game testGame = new Game(deck1, players, 1, testDeck);
        Three three = new Three('C');
        deck1.addCard(three);
        testDeck.addCard(new Card(10, 'S'));
        testBot2.chooseCard(testDeck, testGame);
        assertEquals(testDeck.getCard().getCardNum(), 3);
    }
}
