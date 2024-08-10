package use_case.player_actions.play_card;

import entity.GameInterface;
import interface_adapter.play_card.PlayCardInputData;
import interface_adapter.play_card.PlayThreeInputData;
import interface_adapter.start_game.StartGameOutputBoundary;

public interface PlayCardInputBoundary {

    /**
     * Allows for a card to be played in the game.
     * @param inputData The index of the card to be played
     */
    void playCard(PlayCardInputData inputData);

    /**
     * Sets the current game being played.
     * @param game The game currently being played.
     */
    void setGame(GameInterface game);

    /**
     * Allows for a three to be played in the game and for the suit of the disposal deck to be set.
     * @param inputData The suit and new suit of the card
     */
    void playThree(PlayThreeInputData inputData);

    StartGameOutputBoundary getPresenter();
}