package entity;

import java.util.LinkedList;
import java.util.List;

public class Hand extends CardCollection {
    /**
     * Creates an empty hand
     */
    public Hand(){
        this.cardList = new LinkedList<Card>();
    }

    /**
     * Creates a hand from a list of cards
     * @param cardList The list of cards
     */
    public Hand(List<Card> cardList){
        this.cardList = new LinkedList<>();
        this.cardList.addAll(cardList);
    }

    /**
     * Plays a card
     * @param i The index of the card
     * @return The card
     */
    public Card playCard(int i) {
        Card playedCard = cardList.get(i);
        this.cardList.remove(i);
        return playedCard;
    }

    /**
     * Plays a three
     * @param i The index of the three
     * @return The three
     */
    public Three playThree(int i) {
        Three playedCard = (Three) cardList.get(i);
        this.cardList.remove(i);
        return playedCard;
    }
}
