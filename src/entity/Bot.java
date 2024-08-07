package entity;

import entity.exceptions.InvalidCardException;

public class Bot extends Player{
    String name;
    Hand cards;

    public Bot(String name) {
        super(name);
    }

    public Bot(String name, Hand cards) {
        super(name, cards);
    }

    public void chooseCard(DeckDisposed d, GameInterface game) throws InvalidCardException {
        Card topCard = d.getCard();
        int i = 0;
        for (Card c: cards.cardList) {
            if (c.getCurrentSuit() == topCard.getCurrentSuit() || c.getCardNum() == topCard.getCardNum()) {
                game.playCard(this, i);
            }
            i ++;
        }
    }

    @Override
    public boolean isBot() {
        return true;
    }
}
