package entity;

import java.util.ArrayList;
import java.util.List;

public class Hand extends CardCollection {
    private List<Card> cardList;

    public Hand(ArrayList<Card> cardList){
    this.cardList = cardList;
    }
    public Hand(List<Card> cardList){
    }

    public Card playCard(List<Card> cardList, int i){
        Card playedCard = cardList.get(i);
        cardList.remove(i);
        return playedCard;
    }

    public List<Card> viewCards(){
        return cardList;
    }

}
