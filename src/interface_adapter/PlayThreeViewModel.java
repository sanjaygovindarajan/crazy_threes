package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayThreeViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String suit;
    private char discardSuit;
    private char discardNum;

    public void setDiscardNum(char num){
        discardNum = num;
    }

    public void setDiscardSuit(char suit){
        discardSuit = suit;
    }

    public char getDiscardSuit(){
        return this.discardSuit;
    }

    public char getDiscardNum(){
        return this.discardNum;
    }

    public void firePropertyChanged(String oldSuit, String newSuit) {
        support.firePropertyChange("New Suit", oldSuit, newSuit);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setSuit(String suit) {
        String oldSuit = this.suit;
        this.suit = suit;
        firePropertyChanged(oldSuit, suit);
    }

    public String getSuit() {
        return suit;
    }
}
