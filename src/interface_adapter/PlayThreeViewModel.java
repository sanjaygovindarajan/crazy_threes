package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class PlayThreeViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String suit;
    private char discardSuit;
    private char discardNum;
    private char threeSuit;
    private List<Character> cardNums;

    public void setSuit(String suit) {
        String oldSuit = this.suit;
        this.suit = suit;
        support.firePropertyChange("suit", oldSuit, suit);
    }

    public void setThreeSuit(char suit){
        this.threeSuit = suit;
    }

    public char getThreeSuit() {
        return threeSuit;
    }

    public void setSuit(char suit) {
        char oldSuit = this.discardSuit;
        this.discardSuit = suit;
        support.firePropertyChange("discardSuit", oldSuit, suit);
    }

    public void setDiscardNum(char num) {
        char oldNum = this.discardNum;
        this.discardNum = num;
        support.firePropertyChange("discardNum", oldNum, num);
    }

    public String getSuit() {
        return suit;
    }

    public char getDiscardSuit() {
        return discardSuit;
    }

    public char getDiscardNum() {
        return discardNum;
    }

    public List<Character> getCardNums() {
        return cardNums;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}