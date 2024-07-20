package use_case.player_actions.draw_card;

import entity.*;

/**
 * Handles the logic for drawing a card in the game.
 * This interactor checks if the current player has a playable card and, if not, draws a card
 * from the deck until a playable card is found. It then interacts with the game state to
 * play the card.
 */

public class DrawCardInteractor implements DrawCardInputBoundary {
    /**
     * Constructs a new DrawCardInteractor with the specified game and output boundary.
     *
     * @param game the game instance to interact with
     * @param outputBoundary the boundary used to present results
     */
Game game;
OutputBoundary outputBoundary;

    /**
     * Handles the process of drawing a card for the current player. If the player does not
     * have a playable card, the method draws cards from the deck until a playable card is found.
     * It then prepares a response model and passes it to the output boundary for presentation.
     *
     * @throws MissingCardException if there are no cards left in the deck to draw
     */
    @Override
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

    @Override
    public void setGame(Game game) {
        this.game = game;
    }
}


