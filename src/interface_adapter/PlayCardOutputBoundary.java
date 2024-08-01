package interface_adapter;

import interface_adapter.start_game.StartGameOutputBoundary;

public interface PlayCardOutputBoundary extends StartGameOutputBoundary {
    /**
     * Displays a win message if a player has won.
     * @param player The name of the player who won
     */
    void winMessage(String player);

    /**
     * Switches to the play card view
     */
    void switchView();

    /**
     * Switches to the three view
     * @param suit The current suit
     */
    void loadThreeView(char suit);
}
