package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for managing the state and logic related to the Win View.
 */
public class WinViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private boolean newGameRequested;

    /**
     * Adds a property change listener.
     *
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    
    /**
     * Handles the request to start a new game.
     */
    public void requestNewGame() {
        newGameRequested = true;
        support.firePropertyChange("newGame", false, true);
    }

    /**
     * Checks if a new game was requested.
     * @return True if a new game was requested, otherwise false.
     */
    public boolean isNewGameRequested() {
        return newGameRequested;
    }

    /**
     * Resets the state of the WinViewModel.
     */
    public void reset() {
        newGameRequested = false;
    }
}