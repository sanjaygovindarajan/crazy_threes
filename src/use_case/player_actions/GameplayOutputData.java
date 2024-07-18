package use_case.player_actions;

import entity.Card;
import entity.Game;

public class GameplayOutputData {
    private final Game game;
    private final boolean useCaseFailed;
    private final Card card;


    public GameplayOutputData(Game game, Card card, boolean useCaseFailed) {
        this.game = game;
        this.useCaseFailed = useCaseFailed;
        this.card = card;
    }

    public Game getGame() {
        return game;
    }
    public Card getCard() {return card; }

    public boolean getUseCaseFailed() {
        return useCaseFailed;
    }
}
