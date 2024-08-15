package interface_adapter.play_card;

public class PlayThreeInputData {
    private final char suit;
    private final char newSuit;

    public PlayThreeInputData(char suit, char newSuit) {
        this.suit = suit;
        this.newSuit = newSuit;
    }

    public char getSuit() {
        return suit;
    }

    public char getNewSuit() {
        return newSuit;
    }
}
