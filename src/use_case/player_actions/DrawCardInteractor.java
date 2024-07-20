package use_case.player_actions;

import entity.*;

public class DrawCardInteractor implements DrawCardInputBoundary {
Game game;
OutputBoundary outputBoundary;

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
            outputBoundary.presentDrawCard(new DrawCardOutputData("Player drew a card", true));
        }


        // Play a card
        for (int i = 0; i < player.viewHand().getCardList().size(); i++) {

            if (game.isValidPlay(player.viewHand().getCardList().get(i))) {

//                game.playCard(player, i);
                outputBoundary.presentDrawCard(new DrawCardOutputData("Player played a card", true));

                break;

            }
        }
    }
}


