package interface_adapter.play_card;

import interface_adapter.start_game.StartGameOutputBoundary;

public interface PlayCardOutputBoundary extends StartGameOutputBoundary {
    /**
     * Displays a win message if a player has won.
     * @param player The name of the player who won
     */
    void winMessage(String player);

    void loadInvalidCardView();

    void loadMissingCardView();
}
