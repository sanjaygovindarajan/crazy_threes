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

    public Card dealCard(){
        Card top;
        top = cardList.getFirst();
        cardList.removeFirst();
        return top;
    }

    // For the shuffle use case, remove all cards from DeckDisposed except first, add them to deck, then use the
    // shuffle method
    public void shuffle(){
        Collections.shuffle(cardList);
    }
}
