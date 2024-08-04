package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Manages the state and logic for the Win View, including the winner's name.
 * Notifies listeners when the winner changes.
 */
public class WinViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String winner;

    /**
     * Notifies listeners that the winner has changed.
     * @param oldWinner The previous winner's name.
     * @param newWinner The new winner's name.
     */
    public void firePropertyChanged(String oldWinner, String newWinner) {
        support.firePropertyChange("winning player", oldWinner, newWinner);
    }

    /**
     * Adds a property change listener.
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    /**
     * Sets the winner's name and notifies listeners of the change.
     * @param winner The new winner's name.
     */
    public void setWinner(String winner) {
        String oldWinner = this.winner; // Store the old value
        this.winner = winner;
        firePropertyChanged(oldWinner, winner);
    }

    /**
     * Returns the current winner's name.
     * @return The winner's name.
     */
    public String getWinner() {
        return winner;
    }
}