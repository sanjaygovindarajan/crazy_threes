package interface_adapter;

import entity.InvalidCardException;
import use_case.player_actions.PlayCardInputData;
import use_case.player_actions.PlayCardInputBoundary;

public class PlayCardController {
    final PlayCardInputBoundary playCardInteractor;


    public PlayCardController(PlayCardInputBoundary playCardInteractor) {
        this.playCardInteractor = playCardInteractor;
    }

    public void playCard(int number, char suit) throws InvalidCardException {
        PlayCardInputData newData = new PlayCardInputData(number, suit);

        playCardInteractor.playCard(newData.getCardNum(), newData.getSuit());
    }
}
