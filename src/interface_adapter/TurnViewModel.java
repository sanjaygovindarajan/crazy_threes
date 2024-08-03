package interface_adapter;

import interface_adapter.load_game.LoadGameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class TurnViewModel {
    private List<Character> cardSuits;
    private List<Character> cardNums;
    private char discardSuit;
    private char discardNum;
    private String playerName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCardSuits(List<Character> suits){
        cardSuits = suits;
    }

    public void setCardNums(List<Character> nums){
        cardNums = nums;
    }

    public void setDiscardSuit(char suit){
        discardSuit = suit;
    }

    public void setDiscardNum(char num){
        discardNum = num;
    }

    public List<Character> getCardNums(){
        return this.cardNums;
    }
    public String getPlayerName(){
        return this.playerName;
    }
    public List<Character> getCardSuits(){
        return this.cardSuits;
    }
    public char getDiscardSuit(){
        return this.discardSuit;
    }
    public char getDiscardNum(){
        return this.discardNum;
    }

    public void firePropertyChanged() {

        support.firePropertyChange("state", null, this.cardNums);
        support.firePropertyChange("state", null, this.cardSuits);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
