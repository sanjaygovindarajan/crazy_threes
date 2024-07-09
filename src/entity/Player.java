package entity;

public class Player {
    private Hand cards;
    private String name;
    private int numWins;
    public Player(String name) {
        this.name = name;
        this.cards = new Hand();
        this.numWins = 0;
    } //constructor

    public Player(String name, Hand cards){
        this.name = name;
        this.cards = cards;
        this.numWins = 0;
    }

    public Hand getHand() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public boolean hasCard(Card card) {
        for (Card c: cards.cardList) {
            if (c.equals(card)) {
                return true;
            }
        }
        return false;
    }

    public void playCard(Game game, int i) {
        if (cards.cardList.get(i).getCardNum() == game.getDiscard().getNum()
        && cards.cardList.get(i).getCurrentSuit() == game.getDiscard().getSuit()) {
            cards.playCard(i);
        }
    }

    public void drawCard(Deck d) {
        cards.addCard(d.dealCard());
    }

    public boolean hasWin() {
            return cards.cardList.isEmpty();
    }


}
