package use_case;

import entity.*;
import entity.exceptions.MissingCardException;
import interface_adapter.TurnViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.draw_card.DrawCardOutputBoundary;
import interface_adapter.draw_card.DrawCardPresenter;
import interface_adapter.start_game.StartGamePresenter;
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
        for (char suit : new char[]{'S', 'C', 'H', 'D'}) {
            for (int num : new int[]{2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}) {
                initialDeck.add(new Card(num, suit));
            }
        }
        deck = new Deck(initialDeck);
        emptyDeck = new Deck();
        List<Player> players = new ArrayList<>();
        players.add(new Player("TestName"));
        DeckDisposed discard = new DeckDisposed();
        discard.addCard(new Card(2, 'S'));
        game = new Game(deck, players, 0,discard);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        TurnViewModel turnViewModel = new TurnViewModel();
        drawCardOutputBoundary = new DrawCardPresenter(viewManagerModel, turnViewModel);
        // Setup the interactor with the necessary dependencies
        interactor = new DrawCardInteractor(drawCardOutputBoundary);
        interactor.setGame(game);
    }

    @Test
    public void testHandleDrawCard() throws MissingCardException {
        // Test

        Player player = game.getCurrentPlayer();
        interactor.handleDrawCard();

        //Ensure player has one more card than before
        assertEquals(player.viewHand().getCardList().size(), 1);

        //Ensure deck size has decreased by 1
        assertEquals(deck.getCardList().size(), 47);

        //Ensure the top card is no longer in the deck
        assertNotEquals(player.viewHand().getCardList().getFirst(), deck.getCardList().getFirst());

        Card card = player.viewHand().getCardList().getFirst();
        interactor.handleDrawCard();

        assertEquals(card, player.viewHand().getCardList().getFirst());
        player.playCard(game, 0);
        while (deck.getCardList().size() > 0) {
            interactor.handleDrawCard();
            player.playCard(game, 0);
        }
        interactor.handleDrawCard();
        assertEquals(deck.getCardList().size(), 0);



    }

    @Test
    public void testGetPresenter(){
        assertEquals(drawCardOutputBoundary, interactor.getPresenter());
    }


}
