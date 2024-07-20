package entity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
     */
    public Card dealCard(){
        Card top;
        top = cardList.getFirst();
        cardList.removeFirst();
        return top;
    }

    /**
     * Shuffles/reorders the deck.
     */
    public void shuffle(){
        Collections.shuffle(cardList);
    }
}
