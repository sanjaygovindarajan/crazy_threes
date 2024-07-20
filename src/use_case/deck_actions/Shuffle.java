package use_case.deck_actions;

import entity.Deck;

public class Shuffle {
    private final Deck deck;

    public Shuffle(Deck deck) {
        this.deck = deck;
    }

    public void shuffle(){
        deck.shuffle();
        deck.dealCard();
    }
}
