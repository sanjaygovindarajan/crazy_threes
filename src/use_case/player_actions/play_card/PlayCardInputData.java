package use_case.player_actions.play_card;

public class PlayCardInputData {
    private final int number;
    private final char suit;
    public PlayCardInputData(int number, char suit) {
        this.number = number;
        this.suit = suit;
    }

    /**
     * Gets the number of the card.
     * @return The number of the card
     */
    public int getCardNum() {
        return number;
    }

    /**
     * Gets the suit of the card.
     * @return The suit of the card.
     */
    public char getSuit() {
        return suit;
    }
}
