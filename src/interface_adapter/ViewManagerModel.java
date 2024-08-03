package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Class for managing which view is actively shown.
 */
public class ViewManagerModel {
    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Gets the name of the current active view.
     * @return The name of the current active view.
     */
    public String getActiveView() {
            return activeViewName;
        }

    /**
     * Sets the active view and updates the application.
     * @param activeView The name of the new active view.
     */
    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
        firePropertyChanged();
    }

    /**
     * Updates the application if the view name has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}


