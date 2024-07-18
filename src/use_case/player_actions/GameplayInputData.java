package use_case.player_actions;

import entity.Card;
import entity.Game;

public class GameplayInputData {
    private final Game name;
    private final Card card;
    public GameplayInputData(Game name, Card card) {
        this.name = name;
        this.card = card;
    }

    public Game getPlayerName() {
        return name;
    }
    public Card getPlayCard() {
        return card;
    }
}
