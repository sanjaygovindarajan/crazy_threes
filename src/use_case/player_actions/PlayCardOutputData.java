package use_case.player_actions;

import entity.Card;
import entity.Player;
import entity.DeckDisposed;

public class PlayCardOutputData {
    private final String message;
    private final boolean success;
    private final DeckDisposed dispose; // The card that was drawn, if any
    private final Card playedCard; // The card that was played, if any
    private final Player nextPlayer; // The next player whose hand is to be shown

    public PlayCardOutputData(String message, boolean success, DeckDisposed dispose, Card playedCard, Player nextPlayer) {
        this.message = message;
        this.success = success;
        this.dispose = dispose;
        this.playedCard = playedCard;
        this.nextPlayer = nextPlayer;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public DeckDisposed getDeckDisposed() {
        return dispose;
    }

    public Card getPlayedCard() {
        return playedCard;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }
}
