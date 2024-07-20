package entity;

import java.util.LinkedList;
import java.util.List;

public class DeckDisposed extends CardCollection {

    public DeckDisposed(){
        this.cardList = new LinkedList<>();
    }

    public DeckDisposed(List<Card> cardList){
        this.cardList = new LinkedList<>();
        this.cardList.addAll(cardList);
    }

    /**
     * Tells you what the current suit of the game is (or what the suit of the lat played card is).
     * @return A char spelling the name of the suit
     */
    public char getSuit(){
        return cardList.getLast().getCurrentSuit();
    }

    /**
     * Tells you what the most recently played card is.
     * @return A Card object that was most recently played.
     */
    public Card getCard(){
        return cardList.getLast();
    }

    /**
     * Tell you what the number of the most recently played card is.
     * @return An int that represents the number of the most recently played card.
     */
    public int getNum(){
        return cardList.getLast().getCardNum();
    }
}
