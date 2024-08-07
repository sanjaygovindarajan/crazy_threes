package entity;

import entity.exceptions.InvalidCardException;
import entity.exceptions.MissingCardException;

import java.util.List;

public class Bot extends Player{

    public Bot(String name) {
        super(name);
    }

    public Bot(String name, Hand cards) {
        super(name, cards);
    }

    public void chooseCard(DeckDisposed d, GameInterface game) {
        Card topCard = d.getCard();
        int i = 0;
        Card idealCard = null;
        for (Card c: this.cards.getCardList()) {
            if (c.getCurrentSuit() == topCard.getCurrentSuit() || c.getCardNum() == topCard.getCardNum() || c.getCardNum() == 3) {
                idealCard = c;
                break;
            }
            i++;
        }
        if(idealCard == null){
            try {
                drawCard(game.getDeck());
                chooseCard(d, game);
            } catch(MissingCardException _){
                List<Card> cardList = d.getCardList();
                cardList.remove(d.getCard());
                Deck deck = new Deck(cardList);
                deck.shuffle();
                game.setDeck(deck);

                game.setDiscard(new DeckDisposed());
                game.getDiscard().addCard(topCard);
                try {
                    drawCard(game.getDeck());
                    chooseCard(game.getDiscard(), game);
                } catch(MissingCardException _){
                    throw new RuntimeException();
                }
            }
        } else if(idealCard.getCardNum() == 3) {
            try {
                game.playThree(i, idealCard.getCurrentSuit());
            } catch(InvalidCardException _){
                throw new RuntimeException();
            }
        } else {
            try {
                game.playCard(this, i);
            } catch(InvalidCardException _){
                throw new RuntimeException();
            }
        }
    }

    @Override
    public boolean isBot() {
        return true;
    }
}
