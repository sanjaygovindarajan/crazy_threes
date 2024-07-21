package use_case.player_actions.draw_card;

import entity.GameInterface;
import interface_adapter.start_game.StartGameOutputBoundary;

/**
 * Input boundary interface for handling draw card operations.
 * Defines the method required to perform the draw card action.
 */
public interface DrawCardInputBoundary {
    /**
     * Draws a single card if the player is allowed to do so and if there is a card in the deck.
     * If there is no card in the deck, prompts the player to shuffle the deck.
     * If the player is not allowed to draw a card, prompts the player to choose a different action.
     * If a card is drawn successfully, prompts the player to choose the next action.
     */
    void handleDrawCard();

    /**
     * Sets the game that the interactor modifies.
     * @param game The game
     */
    void setGame(GameInterface game);

    /**
     * Gets the presenter for the draw card user story.
     * @return The presenter
     */
    StartGameOutputBoundary getPresenter();
}


