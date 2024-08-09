package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class LoadGameViewModel {
    private List<String> games;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void setGames(List<String> games) {
        this.games = games;
    }
    public List<String> getGames(){
        return this.games;
    }
    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
