package interface_adapter;

import interface_adapter.load_game.LoadGameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class TurnViewModel {
    private List<String> cardSuits;
    private List<String> cardNums;
    private String discardSuit;
    private String discardNum;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setCardSuits(List<String> suits){
        cardSuits = suits;
    }
    public void setCardNums(List<String> nums){
        cardNums = nums;
    }

    public void setDiscardSuit(String suit){
        discardSuit = suit;
    }

    public void setDiscardNum(String num){
        discardNum = num;
    }

    public List<String> getCardNums(){
        return this.cardNums;
    }

    public List<String> getCardSuits(){
        return this.cardSuits;
    }
    public String getDiscardSuit(){
        return this.discardSuit;
    }
    public String getDiscardNum(){
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
