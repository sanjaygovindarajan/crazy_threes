package interface_adapter;

import view.TemporaryThreeView;

public interface StartGameOutputBoundary {
    void loadSuccessView(StartGameOutputData data);
    void loadInvalidCardView();
    void loadThreeView(char suit);
    void loadMissingCardView();
    void setThreeView(TemporaryThreeView threeView);
}
