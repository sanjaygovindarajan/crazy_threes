package entity;

public class Player {
    private final Hand cards;
    private final String name;
    private final int numWins;
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

    public Hand viewHand() {
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
        cards.playCard(i);
    }

    public void drawCard(Deck d) {
        cards.addCard(d.dealCard());
    }

    public boolean hasWin() {
            return cards.cardList.isEmpty();
    }

    /**
     * Converts a Player object to a String
     * @return The player in string format
     */
    public String toString(){
        return String.join( ";",name,cards.toString());
    }


}
