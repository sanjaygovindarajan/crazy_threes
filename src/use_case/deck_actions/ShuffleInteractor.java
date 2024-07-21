package use_case.deck_actions;

import entity.*;
import interface_adapter.ShuffleOutputBoundary;
import interface_adapter.ShufflePresenter;

import java.util.List;

public class ShuffleInteractor implements ShuffleInputBoundary {
    private GameInterface game;
    private final ShuffleOutputBoundary presenter;

    /**
     * Creates a new use case interactor for shuffling the deck.
     * @param presenter The presenter for shuffling the deck.
     */
    public ShuffleInteractor(ShufflePresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Replaces the deck with the discard pile and shuffles it.
     */
    @Override
    public void shuffle(){
        List<Card> cardList = game.getDiscard().getCardList();
        Card topCard = game.getDiscard().getCard();
        cardList.remove(game.getDiscard().getCard());
        Deck deck = new Deck(cardList);
        deck.shuffle();
        game.setDeck(deck);

        game.setDiscard(new DeckDisposed());
        game.getDiscard().addCard(topCard);
        presenter.loadSuccessful();
    }

    /**
     * Setter method for the game associated with the interactor.
     * @param game The game being played.
     */
    public void setGame(GameInterface game) {
        this.game = game;
    }
}
