package src.entity;
import java.util.*;

abstract class CardCollection {
    List<Card> cardList;
    public CardCollection() {
    }

    public CardCollection(List<Card> cardList){
    }

    public void addCard(Card card){
        // Adds a card to the end of the list
        this.cardList.add(card);
    }
}
