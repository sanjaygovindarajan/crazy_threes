package use_case;
import entity.*;
import interface_adapter.play_card.PlayCardInputData;
import interface_adapter.play_card.PlayCardOutputBoundary;
import interface_adapter.play_card.PlayThreeInputData;
import interface_adapter.start_game.StartGameOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import use_case.player_actions.play_card.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayCardInteractorTest {
    Game game;
    PlayCardInteractor interactor;
    DeckDisposed discard;
    Player player;
    Bot bot;
    int status = 0;
    PlayCardOutputBoundary playCardOutputBoundary;
    List<Player> players;
    @BeforeEach
    public void setUp(){
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
        List<Card> botInitialHand = new ArrayList<>();
        botInitialHand.add(new Card(1,'S'));
        botInitialHand.add(new Card(1,'S'));
        botInitialHand.add(new Card(1,'S'));
        Deck deck = new Deck(initialDeck);
        players = new ArrayList<>();
        Hand hand = new Hand(initialhand);
        Hand botHand = new Hand(botInitialHand);
        players.add(new Player("TestName", hand));
        bot = new Bot("bot1", botHand);
        discard = new DeckDisposed();
        discard.addCard(new Card(2,'H'));
        game = new Game(deck, players, 0, discard);
        player = players.getFirst();
        playCardOutputBoundary = new MockPresenter();

        interactor = new PlayCardInteractor(playCardOutputBoundary);
        interactor.setGame(game);
    }

    /**
     * Tests the handleDrawCard method of the PlayCardInteractor.
     * This test check that a player can play a card and a three, that those cards are removed from their hand, that
     * they are being put in the disposal deck, and that the suit properly changes when a three is played.
     */
    @Test
    public void testPlayCard() {


        Player player = game.getCurrentPlayer();
        int beforeNum = player.viewHand().getCardList().size();
        Card card = player.viewHand().getCardList().getLast();
        interactor.playCard(new PlayCardInputData(card.getCardNum()));

        //Ensure player has one less card than before
        assertEquals(player.viewHand().getCardList().size(), beforeNum - 1);

        //Ensure disposal deck size has increased by 1
        assertEquals(discard.getCardList().size(), 2);

        //Ensure the card played is no longer in the player's hand.
        assertNotEquals(player.viewHand().getCardList().getFirst(), discard.getCardList().getLast());
    }

    @Test
    public void testPlayThree() {
        interactor.playThree(new PlayThreeInputData('S', 'D'));

    }
    @Test
    public void testMissingCard(){
        interactor.playThree(new PlayThreeInputData('H','H'));
        assertEquals(status, 2);

    }
    @Test
    public void testInvalidCard(){
        interactor.playCard(new PlayCardInputData(2));
        assertEquals(status, 2);

    }

    @Test
    public void testWin(){
        players.add(bot);
        interactor.playCard(new PlayCardInputData(1));
        interactor.playCard(new PlayCardInputData(1));
        interactor.playThree(new PlayThreeInputData('S', 'D'));
        System.out.println(status);
        assertEquals(status, 1);
    }

    @Test
    public void testGetPresenter(){
        assertEquals(playCardOutputBoundary,interactor.getPresenter());

    }

    @Test
    public void testBotFinishGame() {

    }
    class MockPresenter implements PlayCardOutputBoundary{

        @Override
        public void winMessage(String player) {
            assertEquals(player, "TestName");
            status = 1;

        }

        @Override
        public void loadInvalidCardView() {
            status = 2;
        }

        @Override
        public void loadThreeView(char suit) {

            assert(List.of('C','S','H','D').contains(suit));
            status = 4;
        }

        @Override
        public void loadSuccessView(StartGameOutputData data) {
            if(data.getCard().charAt(1) == '3'){
                assertEquals(data.getCurrentSuit(), 'D');
            } else {
                assertEquals(data.getCurrentSuit(), 'S');
            }
            status = 5;
        }
    }

}
