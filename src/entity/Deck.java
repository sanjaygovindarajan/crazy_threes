package entity;

import entity.exceptions.MissingCardException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck extends CardCollection {

    public Deck(){
        super();
        this.cardList = new LinkedList<>();
    }

    public Deck(List<Card> cardList){
        this.cardList = new LinkedList<>();
        this.cardList.addAll(cardList);

    }

    /**
     * Removes card from top of deck to be given to player.
     * @return A Card object from top of deck.
     * @Throws MissingCardException of there are no cards in the deck.
     */
    public Card dealCard() throws MissingCardException {
        Card top;
        try {
            top = cardList.getFirst();
            cardList.removeFirst();
            return top;
        } catch(NoSuchElementException e){
            throw new MissingCardException();
        }
    }

    /**
     * Shuffles/reorders the deck.
     */
    public void shuffle(){
        Collections.shuffle(cardList);
    }
}
