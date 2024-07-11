package entity;

import java.util.LinkedList;
import java.util.List;

public class DeckDisposed extends CardCollection {
    LinkedList<Card> cardList;

    public DeckDisposed(){
        this.cardList = new LinkedList<Card>();
    }

    public DeckDisposed(List<Card> cardList){
        this.cardList = (LinkedList<Card>) cardList;
    }

    public char getSuit(){
        return cardList.getFirst().getCurrentSuit();
    }

    public Card getCard(){
        return cardList.getFirst();
    }

    public int getNum(){
        return cardList.getFirst().getCardNum();
    }
}
