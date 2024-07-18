package use_case.player_actions;

import entity.DeckDisposed;
import entity.Game;
import entity.InvalidCardException;
import entity.Player;

public class PlayCardInteractor implements PlayCardInputBoundary {
    Game game;

    @Override
    public void playCard(int number, char suit) throws InvalidCardException {
        DeckDisposed disposed = game.getDiscard();
        Player player = game.getCurrentPlayer();
        char currSuit = disposed.getSuit();
        int currNum = disposed.getNum();
        boolean threeCase = number == 3;
        boolean yesSuit = currSuit == suit;
        boolean yesNum = currNum == number;
        if (!threeCase & (yesSuit || yesNum)) {
            game.playCard(player, number);
        } else if (threeCase) {
            game.playCard(player, number);
        }
    }
}
