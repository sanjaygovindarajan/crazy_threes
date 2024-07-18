package use_case.player_actions;

import entity.Game;
import entity.Player;
import entity.MissingCardException; //once u acc pull
import entity.Card;

public class DrawCardInteractor implements DrawCardInputBoundary {

    @Override
    public void handleDrawCard(Game game, Player player) throws MissingCardException {
        Card topCard = game.getDiscard().getCard();
        boolean hasPlayableCard = false;

        // Check if the player has a playable card
        for (Card card : player.getHand().viewCards()) {
            if (game.isValidPlay(card)) {
                hasPlayableCard = true;
                break;
            }
        }

        // If no playable card, draw until a playable card is found
        if (!hasPlayableCard) {
            player.drawUntilPlayable(game.getDeck(), topCard);
        }

        // Play a card
        for (int i = 0; i < player.getHand().viewCards().size(); i++) {
            if (game.isValidPlay(player.getHand().viewCards().get(i))) {
                game.playCard(player, i);
                break;
            }
        }
    }
}


