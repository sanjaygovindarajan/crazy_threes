package use_case.deck_actions;

import entity.Deck;

public class Shuffle implements ShuffleInputBoundary {
    private final Deck deck;

    public Shuffle(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void shuffle(){
        deck.shuffle();
        deck.dealCard();
    }
}
