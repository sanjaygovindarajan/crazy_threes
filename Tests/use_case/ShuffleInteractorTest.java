package use_case;

import entity.*;
import entity.exceptions.MissingCardException;
import interface_adapter.ViewManagerModel;
import interface_adapter.shuffle.ShuffleOutputBoundary;
import interface_adapter.shuffle.ShufflePresenter;
import org.junit.jupiter.api.Test;
import use_case.deck_actions.ShuffleInputBoundary;
import use_case.deck_actions.ShuffleInteractor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the DrawCardInteractor class.
 * This test class verifies the correct functionality of the handleDrawCard method,
 * ensuring that cards are drawn and played as expected.
 */
public class ShuffleInteractorTest {

    /**
     * Tests the handleDrawCard method of the DrawCardInteractor.
     * This test check that if the player does not have a playable card, a card is drawn from
     * the deck. It also verifies that a playable card is eventually present and that a card
     * is played. It checks if the interactor correctly interacts with the game state.
     *
     */
    @Test
    public void testShuffle() {
        // Setup
        List<Card> initialDeck = new ArrayList<>();
        DeckDisposed discard = new DeckDisposed();
        for (char suit : new char[]{'S', 'C', 'H', 'D'}) {
            for (int num : new int[]{2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}) {
                discard.addCard(new Card(num, suit));
            }
        }
        Deck deck = new Deck(initialDeck);
        List<Player> players = new ArrayList<>();
        players.add(new Player("TestName"));
        Game game = new Game(deck, players, 0,discard);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ShuffleOutputBoundary shuffleOutputBoundary = new ShufflePresenter(viewManagerModel);
        ShuffleInputBoundary interactor = new ShuffleInteractor(shuffleOutputBoundary);
        interactor.setGame(game);
        List<Card> cardList = discard.getCardList();
        interactor.shuffle();
        assert game.getDeck().getCardList().size() == 47;
        assert game.getDeck().getCardList() != cardList;

    }
}