package interface_adapter.start_game;

/**
 * Interface for the StartGamePresenter.
 */
public interface StartGameOutputBoundary {
    /**
     * Loads the cards from the game, the player name, and the top card of the discard pile.
     * Switches the view to the next player's turn.
     * @param data The output data
     */
    void loadSuccessView(StartGameOutputData data);
}
