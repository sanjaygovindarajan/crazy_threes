package use_case.deck_actions;

import entity.*;
import interface_adapter.shuffle.ShuffleOutputBoundary;
import interface_adapter.shuffle.ShufflePresenter;

import java.util.List;

public class ShuffleInteractor implements ShuffleInputBoundary {
    private GameInterface game;
    private final ShuffleOutputBoundary presenter;

    public ShuffleInteractor(ShufflePresenter presenter) {
        this.presenter = presenter;
    }

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

    public void setGame(GameInterface game) {
        this.game = game;
    }
}
