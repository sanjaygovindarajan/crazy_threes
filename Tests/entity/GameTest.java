package entity;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testStartGame(){
        GameInterface game = new Game(Arrays.asList("player1", "player2", "player3"));
        assertEquals(9, game.getPlayers().get(2).viewHand().getCardList().size());
        assertEquals(24, game.getDeck().getCardList().size());
    }

    @Test
    void testPlayCardSameNum() throws InvalidCardException {
        List<Player> players = Arrays.asList(new Player("1"), new Player("2"), new Player("3"));
        Deck deck = new Deck();
        players.getFirst().viewHand().addCard(new Card(9, 'S'));
        DeckDisposed discard = new DeckDisposed();
        discard.addCard(new Card(9,'C'));
        GameInterface game = new Game(deck, players, 0, discard);
        game.playCard(game.getCurrentPlayer(), 0);
        assertTrue(game.isGameOver());
    }

    @Test
    void testPlayCardSameNumNoWin() throws MissingCardException, InvalidCardException {
        List<Player> players = Arrays.asList(new Player("1"), new Player("2"), new Player("3"));
        Deck deck = new Deck();
        players.getFirst().viewHand().addCard(new Card(9, 'S'));
        players.getFirst().viewHand().addCard(new Card(8, 'S'));
        players.get(1).viewHand().addCard(new Card(9, 'S'));
        players.get(2).viewHand().addCard(new Card(9, 'S'));
        DeckDisposed discard = new DeckDisposed();
        discard.addCard(new Card(9,'C'));
        GameInterface game = new Game(deck, players, 0, discard);
        game.playCard(game.getCurrentPlayer(), 0);
        assertFalse(game.isGameOver());
        assertEquals(game.getTurn(), 1);
        assertEquals(game.getCurrentPlayer().getName(), "2");
        assertEquals(game.getPlayers().getFirst().viewHand().getCardList().size(), 1);
        assertEquals(game.getDiscard().getCardList().size(), 2);
        assertEquals(game.getDiscard().getSuit(), 'S');
    }

    @Test
    void testPlayThree() throws MissingCardException, InvalidCardException {
        List<Player> players = Arrays.asList(new Player("1"), new Player("2"), new Player("3"));
        Deck deck = new Deck();
        players.getFirst().viewHand().addCard(new Card(3, 'S'));
        players.getFirst().viewHand().addCard(new Card(8, 'S'));
        players.get(1).viewHand().addCard(new Card(9, 'S'));
        players.get(2).viewHand().addCard(new Card(9, 'S'));
        DeckDisposed discard = new DeckDisposed();
        discard.addCard(new Card(9,'C'));
        GameInterface game = new Game(deck, players, 0, discard);
        game.playCard(game.getCurrentPlayer(), 0);
        assertFalse(game.isGameOver());
        assertEquals(game.getTurn(), 1);
        assertEquals(game.getCurrentPlayer().getName(), "2");
        assertEquals(game.getPlayers().getFirst().viewHand().getCardList().size(), 1);
        assertEquals(game.getDiscard().getCardList().size(), 2);
        assertEquals(game.getDiscard().getSuit(), 'S');
    }

    @Test
    void testLoadGame(){
        List<Player> players = Arrays.asList(new Player("1"), new Player("2"), new Player("3"));
        Deck deck = new Deck();
        DeckDisposed discard = new DeckDisposed();
        GameInterface game = new Game(deck, players, 0, discard);
        assertEquals(game.getPlayers().size(), 3);
        assertEquals(game.getPlayers().getFirst().getName(), "1");
        assertEquals(game.getCurrentPlayer().getName(), "1");
    }

}
