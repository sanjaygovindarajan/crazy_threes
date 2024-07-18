package use_case.player_actions;

public interface PlayCardOutputBoundary {
    void outputSuccessView(String message);
    void outputFailView(String error);
}
