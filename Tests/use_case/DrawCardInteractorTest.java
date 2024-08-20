package use_case;

import entity.*;
import entity.exceptions.MissingCardException;
import interface_adapter.draw_card.DrawCardOutputBoundary;
import interface_adapter.start_game.StartGameOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import use_case.player_actions.draw_card.DrawCardInteractor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the DrawCardInteractor class.
 * This test class verifies the correct functionality of the handleDrawCard method,
 * ensuring that cards are drawn and played as expected.
 */
public class DrawCardInteractorTest {

    /**
     * Tests the handleDrawCard method of the DrawCardInteractor.
     * This test check that if the player does not have a playable card, a card is drawn from
     * the deck. It also verifies that a playable card is eventually present and that a card
     * is played. It checks if the interactor correctly interacts with the game state.
     *
     * @throws MissingCardException if there are no cards left in the deck to draw
     */
    Game game;
    Deck deck;
    DrawCardInputBoundary interactor;
    DrawCardOutputBoundary drawCardOutputBoundary;
    Deck emptyDeck;

    @BeforeEach
    void setUp() {
        List<Card> initialDeck = new ArrayList<>();
        initialDeck.add(new Card(2, 'S'));
        deck = new Deck(initialDeck);
        emptyDeck = new Deck();
        List<Player> players = new ArrayList<>();
        players.add(new Player("TestName"));
        DeckDisposed discard = new DeckDisposed();
        discard.addCard(new Card(2, 'S'));
        game = new Game(deck, players, 0,discard);
        drawCardOutputBoundary = new MockPresenter();
        // Setup the interactor with the necessary dependencies
        interactor = new DrawCardInteractor(drawCardOutputBoundary);
        interactor.setGame(game);
    }

    @Test
    public void testHandleDrawCard(){
        interactor.handleDrawCard();
    }

    @Test
    public void testShuffleView(){
        interactor.handleDrawCard();
        game.getDiscard().addCard(new Card(4,'H'));
        interactor.handleDrawCard();
    }

    @Test
    public void testUnableToDrawCard(){
        deck.addCard(new Card(2, 'S'));
        interactor.handleDrawCard();
        interactor.handleDrawCard();
    }

    @Test
    public void testGetPresenter(){
        assertEquals(drawCardOutputBoundary, interactor.getPresenter());
    }

    class MockPresenter implements DrawCardOutputBoundary{

        @Override
        public void loadUnableToDrawCard() {
            assertEquals(game.getCurrentPlayer().viewHand().getCardList().size(), 1);
            assertTrue(game.hasPlayableCard());

        }

        @Override
        public void loadShuffleView() {
            assertEquals(game.getCurrentPlayer().viewHand().getCardList().size(), 1);
            assertTrue(deck.getCardList().isEmpty());
            System.out.println("Test");

        }

        @Override
        public void loadSuccessView(StartGameOutputData data) {
            assertEquals((data.getPlayerCards().length() + 1) / 3, 1);

        }
    }


}
