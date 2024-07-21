/* package use_case.player_actions.draw_card;

import entity.*;
import interface_adapter.DrawCardOutputBoundary;
import use_case.player_actions.DrawCardOutputData;

/**
 * Handles the logic for drawing a card in the game.
 * This interactor checks if the current player has a playable card and, if not, draws a card
 * from the deck until a playable card is found.
 * If the drawn card is playable, it will be played. The interactor then communicates the results
 * to the output boundary.
 */

/* public class DrawCardInteractor implements DrawCardInputBoundary {
    /**
     * Constructs a new DrawCardInteractor with the specified game and output boundary.
     *
     * @param game the game instance to interact with
     * @param outputBoundary the boundary used to present results
     * @param playCardInteractor the interactor used to play cards
     */
/* Game game;
DrawCardOutputBoundary outputBoundary;
    /**
     * Handles the process of drawing a card for the current player. If the player does not
     * have a playable card, the method draws cards from the deck until a playable card is found.
     * It then prepares a response model and passes it to the output boundary for presentation.
     *
     * @throws MissingCardException if there are no cards left in the deck to draw
     */
/*     @Override
    public void handleDrawCard() throws MissingCardException {
        Player player = game.getCurrentPlayer();
        Card drawnCard = player.drawCard(game.getDeck());
        Card playedCard = null;
        boolean cardPlayedSuccessfully = false;

        // Check if the drawn card is playable
        if (game.isValidPlay(drawnCard)) {
            try {
                playCardInteractor.playCard(drawnCard.getNumber(), drawnCard.getSuit());
                playedCard = drawnCard; // The card drawn was played
                cardPlayedSuccessfully = true;
            } catch (InvalidCardException e) {
                cardPlayedSuccessfully = false;
            }
        }

        // Prepare the result message and details
        String message = "Player drew a card: " + drawnCard.getNumber() + drawnCard.getSuit();
        if (cardPlayedSuccessfully) {
            message += " and played it.";
        } else {
            message += " but could not play it.";
        }

        // Notify the presenter with the details
        outputBoundary.presentDrawCard(new DrawCardOutputData(
                message,
                cardPlayedSuccessfully,
                drawnCard,
                playedCard,
                game.getNextPlayer()
        ));
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }
}

*/
