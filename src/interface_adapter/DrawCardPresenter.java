package interface_adapter;

import use_case.player_actions.DrawCardOutputData;
import entity.Card;
import entity.Player;


/**
 * Formats and presents the result of a draw card operation.
 * Implements the DrawCardOutputBoundary interface to handle the presentation of the
 * draw card output data.
 */
public class DrawCardPresenter implements DrawCardOutputBoundary {
    /**
     * Presents the result of the draw card operation.
     * Formats the output data and prints it to the console.
     *
     * @param outputData the output data containing the result of the draw card operation
     */
    @Override
    public void presentDrawCard(DrawCardOutputData outputData) {
        // Format the output data for the view
        System.out.println(outputData.getMessage());

        if (outputData.getDrawnCard() != null) {
            System.out.println("Drawn card: " + cardToString(outputData.getDrawnCard()));
        }

        if (outputData.getPlayedCard() != null) {
            System.out.println("Played card: " + cardToString(outputData.getPlayedCard()));
        } else if (outputData.getDrawnCard() != null) {
            System.out.println("Drawn card could not be played.");
        }

        if (outputData.getNextPlayer() != null) {
            System.out.println("Next player: " + outputData.getNextPlayer().getName());
            System.out.println("Their cards:");
            for (Card card : outputData.getNextPlayer().viewHand().getCardList()) {
                System.out.println(cardToString(card));
            }
        }
    }

    private String cardToString(Card card) {
        String num = card.getNumberAsString();
        String suit = card.getSuitAsString();
        return num + " of " + suit;
    }
}