package entity;
import java.util.*;

public abstract class CardCollection implements CardCollectionInterface {
    private List<Card> cardList;
    public CardCollection() {
    }

    public CardCollection(List<Card> cardList){
    }
    /**
     * Adds a new card to the end of the list
     * @param card The card to add to the list
     */
    public void addCard(Card card){
        this.cardList.add(card);
    }
    /**
     * Gets the list of cards.
     * @return The list of cards
     */
    public List<Card> getCardList(){
        return this.cardList;
    }
}
