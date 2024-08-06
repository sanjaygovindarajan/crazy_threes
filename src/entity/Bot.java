package entity;

public class Bot extends Player{
    String name;
    Hand cards;

    public Bot(String name) {
        super(name);
    }

    public Bot(String name, Hand cards) {
        super(name, cards);
    }

    public void chooseCard(DeckDisposed d, Game game, int i) {
        Card topCard = d.getCard();
        for (Card c: cards.cardList) {
            if (c.getCurrentSuit() == topCard.getCurrentSuit() && c.getCardNum() == topCard.getCardNum()) {
                playCard(game, i);
            }
        }
    }
}
