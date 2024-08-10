package interface_adapter.play_card;

import use_case.player_actions.play_card.PlayCardInputBoundary;

public class PlayCardController {
    final PlayCardInputBoundary playCardInteractor;


    public PlayCardController(PlayCardInputBoundary playCardInteractor) {
        this.playCardInteractor = playCardInteractor;
    }

    /**
     * Allows for a card to be played in the game.
     * @param index The index of the card to be played
     */
    public void playCard(int index){

        playCardInteractor.playCard(new PlayCardInputData(index));
    }

    /**
     * Allows for a three to be played in the game and for the suit of the disposal deck to be set.
     * @param suit the suit of the card being played (user input).
     * @param newSuit the suit the disposal deck will begin accepting (user input).
     */
    public void playThree(char suit, String newSuit) {
        playCardInteractor.playThree(new PlayThreeInputData(suit, newSuit.toUpperCase().charAt(0)));
    }

    public PlayCardInputBoundary getInteractor() {
        return this.playCardInteractor;
    }
}
