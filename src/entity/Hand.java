package entity;

import java.util.ArrayList;
import java.util.List;

public class Hand extends CardCollection implements HandInterface {

    public Hand(List<Card> cardList){
    this.cardList = new ArrayList<Card>(cardList);
    }

    public Hand(){
        this.cardList = new ArrayList<Card>();
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
