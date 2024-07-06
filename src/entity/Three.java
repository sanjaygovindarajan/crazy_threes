package entity;

public class Three extends Card implements ThreeInterface {
    private char newSuit;

    public Three(char cardSuit) {
        super(3, cardSuit);
    }

    public void setNewSuit(char newSuit) {
        this.newSuit = newSuit;
    }

    public char getCurrentSuit(){
        return this.newSuit;
    }
}
