package interface_adapter.load_game;

public interface ViewGamesOutputBoundary {
    /**
     * Print an error message.
     */
    void loadFailView();

    /**
     * Present all the games in the database to loadGameView.
     */
    void loadSuccessView(ViewGamesOutputData outputData);
}
