package entity;

import java.util.ArrayList;
import java.util.List;

public class Hand extends CardCollection {

    public Hand(){
        this.cardList = new ArrayList<Card>(cardList);
    }

    public Card playCard(int i){
        Card playedCard = cardList.get(i);
        cardList.remove(i);
        return playedCard;
    }

    public ArrayList<Card> viewCards(){
        return cardList;
    }

}
