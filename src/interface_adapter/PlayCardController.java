package interface_adapter;

import use_case.player_actions.play_card.PlayCardInputData;
import use_case.player_actions.play_card.PlayCardInputBoundary;

import java.util.Objects;

public class PlayCardController {
    final PlayCardInputBoundary playCardInteractor;


    public PlayCardController(PlayCardInputBoundary playCardInteractor) {
        this.playCardInteractor = playCardInteractor;
    }

    public void playCard(String number, String suit){
        int newNumber = 0;
        if (Objects.equals(number, "ace")) {
            newNumber = 14;
        } else if (Objects.equals(number, "jack")) {
            newNumber = 11;
        } else if (Objects.equals(number, "queen")) {
            newNumber = 12;
        } else if (Objects.equals(number, "king")) {
            newNumber = 13;
        } else {
            newNumber = Integer.parseInt(number);
        }
        char newSuit = suit.toUpperCase().charAt(0);
        PlayCardInputData newData = new PlayCardInputData(newNumber, newSuit);

        playCardInteractor.playCard(newData.getCardNum(), newData.getSuit());
    }

    public void playThree(char suit, String newSuit) {
        playCardInteractor.playThree(suit, newSuit.toUpperCase().charAt(0));
    }
}
