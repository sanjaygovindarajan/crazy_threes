package interface_adapter;

import entity.Player;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for managing the state and logic related to the Name Input View.
 */
public class NameInputViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Player[] players;

    /**
     * Adds a property change listener.
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Sets the array of players and notifies listeners.
     * @param playerNames The array of player names.
     */
    public void setPlayerNames(String[] playerNames) {
        this.players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            this.players[i] = new Player(playerNames[i]);
        }
        support.firePropertyChange("players", null, players);
    }

    /**
     * Gets the array of players.
     * @return The array of players.
     */
    public Player[] getPlayers() {
        return players;
    }
}
