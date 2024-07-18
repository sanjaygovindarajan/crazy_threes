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

    /**
     * Returns the current Hand of the Player
     * @return the current Hand of the Player
     */
    public Hand viewHand() {

        return cards;
    }

    /**
     * Returns the name of the Player
     * @return the name of the Player
     */
    public String getName() {

        return name;
    }

    /**
     * Returns whether the Player currently has the Card or not
     * @param card The Card we want to know about
     * @return whether the Player has the Card
     */
    public boolean hasCard(Card card) {

        for (Card c: cards.cardList) {
            if (c.equals(card)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Plays the Card in the position i in the Hand of the Player.
     * @param game The Game we want to play in
     * @param i the position of the card in the Hand of the Player
     */
    public void playCard(Game game, int i) {

        if (cards.cardList.get(i).getCardNum() == game.getDiscard().getNum()
        && cards.cardList.get(i).getCurrentSuit() == game.getDiscard().getSuit()) {
            cards.playCard(i);
        }
    }

    /**
     * Draws a Card from the deck.
     * @param d The deck we want to draw from
     */
    public void drawCard(Deck d) {

        cards.addCard(d.dealCard());
    }

    /**
     * Returns whether the Player has won the Game
     * @return True if the player has won the game and False if they haven't
     */
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


    public Hand getHand() {
        return cards;
    }

    public void drawUntilPlayable(Deck deck, Card topCard) {
    }
}
