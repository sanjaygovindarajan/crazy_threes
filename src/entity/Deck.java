package entity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck extends CardCollection {
    LinkedList<Card> cardList;

    public Deck(){
        this.cardList = new LinkedList<Card>();
    }

    public Deck(List<Card> cardList){
        this.cardList = (LinkedList<Card>) cardList;
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
