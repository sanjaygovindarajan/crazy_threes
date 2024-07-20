package interface_adapter;

import entity.InvalidCardException;
import use_case.player_actions.PlayCardInputData;
import use_case.player_actions.PlayCardInputBoundary;

public class PlayCardController {
    final PlayCardInputBoundary playCardInteractor;


    public PlayCardController(PlayCardInputBoundary playCardInteractor) {
        this.playCardInteractor = playCardInteractor;
    }

    public void playCard(String number, String suit) throws InvalidCardException {
        int newNumber = Integer.parseInt(number);
        char newSuit = suit.charAt(0);
        PlayCardInputData newData = new PlayCardInputData(newNumber, newSuit);

        playCardInteractor.playCard(newData.getCardNum(), newData.getSuit());
    }
}
