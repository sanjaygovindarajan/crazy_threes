package use_case.player_actions.draw_card;

import entity.Game;
import entity.MissingCardException;

/**
 * Input boundary interface for handling draw card operations.
 * Defines the method required to perform the draw card action.
 */
public interface DrawCardInputBoundary {
    /**
     * Handles the logic for drawing a card for current player.
     * Checks if the player has a playable card; if not, draws cards from the deck until
     * a playable card is found. This method may throw an exception if there are no cards left
     * in the deck.
     *
     * @throws MissingCardException if there are no cards left in the deck to draw
     */
    void handleDrawCard() throws MissingCardException;

    void setGame(Game game);
}


