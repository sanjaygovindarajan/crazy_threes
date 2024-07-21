package interface_adapter;

import view.TemporaryShuffleView;
import view.TemporaryThreeView;

public interface StartGameOutputBoundary {
    void loadSuccessView(StartGameOutputData data);
    void loadInvalidCardView();
    void loadThreeView(char suit);
    void loadMissingCardView();
    void setThreeView(TemporaryThreeView threeView);
    void winMessage(String player);

    void loadUnableToDrawCard();

    void loadShuffleView();

    void setShuffle(TemporaryShuffleView shuffleView);
}
