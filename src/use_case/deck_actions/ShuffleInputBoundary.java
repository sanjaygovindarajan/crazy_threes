package use_case.deck_actions;

import entity.GameInterface;

public interface ShuffleInputBoundary {
    /**
     * Moves all but the top card on the discard pile to the deck.
     * Then, shuffles the deck.
     */
    void shuffle();

    /**
     * Sets the game for the use case interactor.
     * @param game The current game
     */
    void setGame(GameInterface game);
}