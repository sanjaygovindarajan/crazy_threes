package entity;

public class Card {

    private int cardNum;
    private char cardSuit;

    public Card(int cardNum, char cardSuit) {
        this.cardNum = cardNum;
        this.cardSuit = cardSuit;
    }
    public char getCurrentSuit() {
        return cardSuit;
    }

    public char getDisplaySuit() {
        return cardSuit;
    }

    public int getCardNum() {
        return cardNum;
    }
}
