package use_case.deck_actions;

import entity.Deck;
import entity.Game;

public class ShuffleInteractor implements ShuffleInputBoundary {
    private final Deck deck;

    public ShuffleInteractor(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void shuffle(){
        deck.shuffle();
        deck.dealCard();
    }

    public void setGame(Game game) {

    }
}
