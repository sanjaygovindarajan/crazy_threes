package interface_adapter;

import java.beans.PropertyChangeListener;

/**
 * Not used in Phase 1
 */
public abstract class ViewModel {
    private String viewName;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    public ViewModel(){}

    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();

    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}
