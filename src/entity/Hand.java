package entity;

import java.util.LinkedList;
import java.util.List;

public class Hand extends CardCollection {

    public Hand(){
        this.cardList = new LinkedList<Card>();
    }

    public Hand(List<Card> cardList){
        this.cardList = new LinkedList<>();
        this.cardList.addAll(cardList);
    }

    public Card playCard(int i) {
        Card playedCard = cardList.get(i);
        this.cardList.remove(i);
        return playedCard;
    }

    public Three playThree(int i) {
        Three playedCard = (Three) cardList.get(i);
        this.cardList.remove(i);
        return playedCard;
    }
}
