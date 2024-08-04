package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for managing the state and logic related to the Win View.
 */
public class WinViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String winner;

    public void firePropertyChanged(String oldWinner, String newWinner) {
        support.firePropertyChange("winning player", oldWinner, newWinner);
    }

    /**
     * Adds a property change listener.
     *
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setWinner(String winner) {
        String oldWinner = this.winner; // Store the old value
        this.winner = winner;
        firePropertyChanged(oldWinner, winner);

    }
    public String getWinner() {
        return winner;
    }
}