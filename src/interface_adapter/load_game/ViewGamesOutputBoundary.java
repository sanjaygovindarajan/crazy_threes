package interface_adapter.load_game;

public interface ViewGamesOutputBoundary {
    void loadFailView();

    void loadSuccessView(ViewGamesOutputData outputData);
}
