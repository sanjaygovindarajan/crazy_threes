package entity;
import java.util.*;

public abstract class CardCollection implements CardCollectionInterface {
    protected List<Card> cardList;
    public CardCollection() {
    }

    public CardCollection(List<Card> cardList){
        this.cardList = cardList;
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

    /**
     * Converts the CardCollection object to a String
     * @return The CardCollection in string format (that is, the Card objects in string format separated by a comma)
     */
    public String toString(){
        List<String> cardListStr = new ArrayList<>(cardList.size());
        for(Card card : cardList){
            cardListStr.add(card.toString());
        }
        return String.join(",",cardListStr);
    }
}
