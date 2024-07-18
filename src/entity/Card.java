package entity;

public class Card implements CardInterface {
    private final int cardNum;
    private final char cardSuit;

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

    /**
     * Returns the String representation of a Card.
     * @return The card suit concatenated with the Card number.
     */
    @Override
    public String toString(){
        return  cardSuit + Integer.toString(cardNum);
    }

    /**
     * Does nothing, overridden when the card is a three
     * @param newSuit Does nothing
     */
    @Override
    public void setNewSuit(char newSuit) {
    }
}
