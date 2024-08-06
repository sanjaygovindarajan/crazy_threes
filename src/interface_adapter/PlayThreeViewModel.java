package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayThreeViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String suit;


    public void firePropertyChanged(String oldSuit, String newSuit) {
        support.firePropertyChange("New Suit", oldSuit, newSuit);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setWinner(String suit) {
        String oldSuit = this.suit;
        this.suit = suit;
        firePropertyChanged(oldSuit, suit);
    }

    public String getWinner() {
        return suit;
    }
}
