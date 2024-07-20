package use_case.player_actions;

import entity.Game;
import entity.InvalidCardException;
import entity.Player;

public class PlayCardInteractor implements PlayCardInputBoundary {
    Game game;

    @Override
    public void playCard(int number, char suit) throws InvalidCardException {
        Player player = game.getCurrentPlayer();
        boolean threeCase = number == 3;
        if (!threeCase) {
            game.playCard(player, number);
        }
    }
    public void playThree(char suit, char newSuit) throws InvalidCardException {
        Player player = game.getCurrentPlayer();
            game.playThree(suit, newSuit);
        }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }
}

// load request suit view
