package use_case;
import entity.*;
import entity.exceptions.InvalidCardException;
import interface_adapter.PlayThreeViewModel;
import interface_adapter.TurnViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.WinViewModel;
import interface_adapter.play_card.PlayCardOutputBoundary;
import interface_adapter.play_card.PlayCardPresenter;
import interface_adapter.start_game.StartGamePresenter;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import use_case.player_actions.play_card.PlayCardInputBoundary;
import use_case.player_actions.play_card.PlayCardInteractor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlayCardInteractorTest {

    /**
     * Tests the handleDrawCard method of the PlayCardInteractor.
     * This test check that a player can play a card and a three, that those cards are removed from their hand, that
     * they are being put in the disposal deck, and that the suit properly changes when a three is played.
     * @throws InvalidCardException if there are no cards left in the deck to draw
     */
    @Test
    public void testPlayCard() throws InvalidCardException {
        List<Card> initialhand = new ArrayList<>();
        for (char suit : new char[]{'S'}) {
            for (int num : new int[]{3, 2, 1}) {
                if (num == 3) {
                    initialhand.add(new Three('S'));
                } else {
                    initialhand.add(new Card(num, suit));
                }
            }
        }
        List<Card> initialDeck = new ArrayList<>();
        Deck deck = new Deck(initialDeck);
        List<Player> players = new ArrayList<>();
        Hand hand = new Hand(initialhand);
        players.add(new Player("TestName", hand));
        DeckDisposed discard = new DeckDisposed();
        discard.addCard(new Card(2,'S'));
        Game game = new Game(deck, players, 0, discard);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        TurnViewModel turnViewModel = new TurnViewModel();
        WinViewModel winViewModel = new WinViewModel();
        PlayThreeViewModel playThreeViewModel = new PlayThreeViewModel();
        PlayCardOutputBoundary playCardOutputBoundary = new PlayCardPresenter(viewManagerModel, turnViewModel, winViewModel, playThreeViewModel);

        PlayCardInputBoundary interactor = new PlayCardInteractor(playCardOutputBoundary);
        interactor.setGame(game);


        Player player = game.getCurrentPlayer();
        int beforeNum = player.viewHand().getCardList().size();
        Card card = player.viewHand().getCardList().getLast();
        interactor.playCard(card.getCardNum());

        //Ensure player has one less card than before
        assertEquals(player.viewHand().getCardList().size(), beforeNum - 1);

        //Ensure disposal deck size has increased by 1
        assertEquals(discard.getCardList().size(), 2);

        //Ensure the card played is no longer in the player's hand.
        assertNotEquals(player.viewHand().getCardList().getFirst(), discard.getCardList().getLast());

        Card newCard = player.viewHand().getCardList().getFirst();
        interactor.playThree(newCard.getCurrentSuit(), 'D');

        //Ensure player has two fewer cards than before
        assertEquals(player.viewHand().getCardList().size(), beforeNum - 2);

        //Ensure disposal deck size has increased by 2
        assertEquals(discard.getCardList().size(), 3);

        //Ensure the card played is no longer in the player's hand.
        assertNotEquals(player.viewHand().getCardList().getFirst(), discard.getCardList().getLast());

        //Ensure disposal deck suit was properly changed.
        assertEquals(discard.getSuit(), 'D');
    }
}
