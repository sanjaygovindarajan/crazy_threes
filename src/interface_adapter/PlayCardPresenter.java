package interface_adapter;

import entity.Card;
import entity.DeckDisposed;
import use_case.player_actions.PlayCardOutputBoundary;
import use_case.player_actions.PlayCardOutputData;

public class PlayCardPresenter implements PlayCardOutputBoundary {
    @Override
    public void presentPlayCard(PlayCardOutputData outputData) {
        System.out.println(outputData.getMessage());

        if (outputData.getDeckDisposed() != null) {
            System.out.println("Top card: " + cardToString(outputData.getDeckDisposed().getCard()));
        }

        if (outputData.getPlayedCard() != null) {
            System.out.println("Played card: " + cardToString(outputData.getPlayedCard()));

            if (outputData.getNextPlayer() != null) {
                System.out.println("Next player: " + outputData.getNextPlayer().getName());
                System.out.println("Their cards:");
                for (Card card : outputData.getNextPlayer().viewHand().getCardList()) {
                    System.out.println(cardToString(card));
                }
            }
        }

    }
    private String cardToString (Card card) {
        return card.toString();
    }
}
