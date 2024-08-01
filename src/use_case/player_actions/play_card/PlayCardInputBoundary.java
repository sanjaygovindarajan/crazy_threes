package use_case.player_actions.play_card;

import entity.GameInterface;
import interface_adapter.start_game.StartGameOutputBoundary;

public interface PlayCardInputBoundary {

    /**
     * Allows for a card to be played in the game.
     * @param number the number of the card to be played.
     * @param suit the suit of the card to be played.
     */
    void playCard(int number, char suit);

    /**
     * Sets the current game being played.
     * @param game The game currently being played.
     */
    void setGame(GameInterface game);

    /**
     * Allows for a three to be played in the game and for the suit of the disposal deck to be set.
     * @param suit the suit of the card being played.
     * @param newSuit the suit the disposal deck will begin accepting.
     */
    void playThree(char suit, char newSuit);

    StartGameOutputBoundary getPresenter();

    void switchView();
}