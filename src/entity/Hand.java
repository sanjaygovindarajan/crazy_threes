package entity;

import java.util.List;

public class Hand extends CardCollection {
    private List<Card> hand;

    public Hand(List<Card> hand){
    }

    public Card playCard(List<Card> hand, int i){
        Card playedCard = hand.get(i);
        hand.remove(i);
        return playedCard;
    }

    public List<Card> viewCards(){
        return hand;
    }

}
