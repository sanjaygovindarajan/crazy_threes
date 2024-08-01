package interface_adapter.start_game;

import view.TemporaryShuffleView;
import view.TemporaryThreeView;

public interface StartGameOutputBoundary {
    void loadSuccessView(StartGameOutputData data);
    void loadInvalidCardView();
    void loadMissingCardView();
    void setThreeView(TemporaryThreeView threeView);
    void setShuffle(TemporaryShuffleView shuffleView);
}
