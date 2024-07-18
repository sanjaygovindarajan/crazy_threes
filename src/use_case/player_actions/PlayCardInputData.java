package use_case.player_actions;

public class PlayCardInputData {
    private final int number;
    private final char suit;
    public PlayCardInputData(int number, char suit) {
        this.number = number;
        this.suit = suit;
    }

    public int getCardNum() {
        return number;
    }

    public char getSuit() {
        return suit;
    }
}
