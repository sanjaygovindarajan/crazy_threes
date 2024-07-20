package use_case.player_actions.draw_card;

import entity.*;

public class DrawCardInteractor implements DrawCardInputBoundary {
Game game;

    public void handleDrawCard() throws MissingCardException {
        boolean hasPlayableCard = false;
        Player player = game.getCurrentPlayer();

        // Check if the player has a playable card
        for (Card card : player.viewHand().getCardList()) {
            if (game.isValidPlay(card)) {
                hasPlayableCard = true;
                break;
            }
        }

        // If no playable card, draw until a playable card is found
        if (!hasPlayableCard) {
            player.drawCard(game.getDeck());
        }

        // Play a card
        for (int i = 0; i < player.viewHand().getCardList().size(); i++) {

            if (game.isValidPlay(player.viewHand().getCardList().get(i))) {

//                game.playCard(player, i);

                break;

            }
        }
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }
}


