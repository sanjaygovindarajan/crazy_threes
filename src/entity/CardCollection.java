package entity;
import java.util.*;

public abstract class CardCollection implements CardCollectionInterface {
    List<Card> cardList;
    public CardCollection() {
    }

    public CardCollection(List<Card> cardList){
    }

    public void addCard(Card card){
        // Adds a card to the end of the list
        this.cardList.add(card);
    }

    public List<Card> getCardList(){
        return this.cardList;
    }
}
