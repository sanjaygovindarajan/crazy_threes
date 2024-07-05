package entity;

public class Three extends Card {
    private int cardNum;
    private char cardSuit;
    char newSuit;

    public Three(int cardNum, char cardSuit) {
        super(3, cardSuit);
    }

    public void setNewSuit(char newSuit) {
        this.newSuit = newSuit;
    }

    public char getCurrentSuit(){
        return this.cardSuit;
    }
}
